/***********************************
* Server Code
* Author, Stephen Mingolelli, Mia
************************************/
let players = [];
let current_turn = 0;
let timeOut;
let _turn = 0;
const MAX_WAITING = 5000;


function next_turn(){
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
