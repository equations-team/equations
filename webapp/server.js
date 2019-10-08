/**************************************************
* Server Code
* Author, Stephen Mingolelli, Mia, Ethan Zimmerman
***************************************************/
let players = [];
let current_turn = 0;
let timeOut;
let _turn = 0;
const MAX_WAITING = 5000;

var validgame = function(req){
  // What must exist in order for the game to start
  if(!req.session.username) {return null; }
  if(!req.session.gameID) {return null; }

  return{
    gameID : req.session.gameID;
    name : req.session.username;
  };
};

var IO = null;

// When a player joins

var join = function(gameID){
  var sess = this.handshake.session;
  var debugInfo = {
    socketID : this.id,
    event : 'join',
    gameID : gameID,
    session: sess
  };

  // Add this player to the actual game
  var result = game.addPlayer(sess);
  if(!result){
    console.log('ERROR: Failed to Add Player', debugInfo);
    this.emit('error', {message: "Not able to join this game."});
    return;
  }

  // When joined they should be added to a room matching that game
  this.join(gameID);

  // Update everyone on the connection

  IO.sockets.in(gameID).emit('update', game);
};

// When a player makes a move

var move = function(data){
  var sess = this.handshake.session;
  var debugInfo = {
    socketID : this.id,
    event : 'move',
    move : data.move,
    session: sess
  };
  //var result = game.move(data.move)
  if(!result){
    console.log('Invalid move!', debugInfo);
    this.emit('error', {message: "Invalid move, try again."});
    return;
  }

  // Update this move to everyone else
  IO.sockets.in(data.gameID).emit('update', game);
  console.log(data.gameID+' '+sess.username+': '+data.move);

  // Skip a turn, unless we want the game to end.
  this.nextTurn;
};


// When a player quits/forfeits the game.

var withdraw = function(gameID){

  var sess = this.handshake.session;
  var debugInfo = {
    socketID: this.id,
    event :'withdraw',
    gameID : gameID,
    session: sess
  };

  // Begin withdrawal process
  var result = game.withdraw(sess);
  // Something goes wrong
  if(!result){
    console.log('Could not withdraw', debugInfo);
    this.emit('Error', {message: "Could not withdraw from the game."});
    return;
  }

  // Update this to the game and players

  IO.sockets.in(gameID).emit('update', game);
  console.log(gameID+' '+sess.username+'': Withdrew');

  // Skip a turn, unless we want the game to end.
  this.nextTurn;

};

// Removing a player from the game when they disconnect.

var remove = function(){
  var sess = this.handshake.session;
  var debugInfo = {
    socketID : this.id,
    event : 'disonnect',
    session : sess
  };

  // Remove this player

  var result = game.removePlayer(sess);
  if(!result){
    console.log(sess.username+' failed to leave'+sess.gameID);
    return;
  }

  // Make update to players

  console.log(sess.username+' disconnected' +sess.gameID);
  console.log('Socket '+this.id+' disconnected');

  // Skip a turn, unless we want the game to end.
  this.nextTurn;
}

// Attach events / functions to socket.io

exports.attach = function(io){
  IO = io;

  io.sockets.on('connection', function (socket){

    // Event handling
    socket.on('join',join);
    socket.on('move', move);
    socket.on('withdraw', withdraw);
    socket.on('remove', remove);

    console.log('Socket'+socket.id+' connected');
  })
}

function nextTurn(){
  _turn = current_turn++ % players.length;
  players[_turn].emit('your_turn');
  console.log("next turn triggered " , _turn);
  triggerTimeout();
}

function triggerTimeout(){
  timeOut = setTimeout(()=>{
    next_turn();
  },MAX_WAITING);
}

function resetTimeOut(){
  if(typeof timeOut === 'object'){
    console.log("timeout reset");
    clearTimeout(timeOut);
  }
}

 io.on('connection', function(socket){
  console.log('A player connected');

  players.push(socket);
  socket.on('pass_turn',function(){
     if(players[_turn] == socket){
        resetTimeOut();
        next_turn();
     }
  })

socket.on('disconnect', function(){
  console.log('A player disconnected');
  players.splice(players.indexOf(socket),1);
  _turn--;
  console.log("A number of players now ",players.length);
  });
});
