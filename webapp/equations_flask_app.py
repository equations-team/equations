#============================================================================
# Name        : equations_web_server.py
# Author      : Stephen Mingolelli, Mussaed, Ruslan Spivak
# Description :  Web server for the equations game using Flask. It refers
# to a 'static' and 'templates' directory, which act as the source material
# for any scripts, images, text, etc. that will appear on a page.
#============================================================================

from flask import Flask, render_template, request, redirect, url_for, session
from flask import Response
from flask_mysqldb import MySQL
from pusher import pusher
import MySQLdb.cursors
import re
import io
import socket
import sys

    # Possible actions
    ACT = {'cn':'Challenge Now',
           'ci':'Challenge Impossible',
           'rf':'Red to Forbidden',
           'gf':'Green to Forbidden',
           'bf':'Blue to Forbidden',
           'kf':'Black to Forbidden',
           'rr':'Red to Required',
           'gr':'Green to Required',
           'br':'Blue to Required',
           'kr':'Black to Required',
           'rp':'Red to Required',
           'gp':'Green to Permitted',
           'bp':'Blue to Permitted',
           'kp':'Black to Permitted',}

           # Report that a player made a move, whether it be challenge or
           # move a cube from resources to the mat.
           #def report_choice(username):
              #print(opponent['username'], 'chose to %cn.'
               #print(opponent['username'], 'chose to %ci.'
               #print(opponent['username'], 'chose to move %rf.'
               #print(opponent['username'], 'chose to move %gf.'
               #print(opponent['username'], 'chose to move %bf.'
               #print(opponent['username'], 'chose to move %kf.'
               #print(opponent['username'], 'chose to move %rr.'
               #print(opponent['username'], 'chose to move %gr.'
               #print(opponent['username'], 'chose to move %br.'
               #print(opponent['username'], 'chose to move %kr.'
               #print(opponent['username'], 'chose to move %rp.'
               #print(opponent['username'], 'chose to move %gp.'
               #print(opponent['username'], 'chose to move %bp.'
               #print(opponent['username'], 'chose to move %kp.'


app = Flask('flaskapp')

app.secret_key = 'your secret key'

# Set up Pusher Channel
pusher = pusher_client = pusher.Pusher(
    app_id='875336',
    key='780b979b9a53b5501288',
    secret='926a0bfa9aafb63235d3',
    cluster='us3',
    ssl=True
    )

name = ''

# Enter your database connection details below
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'mussaed'
app.config['MYSQL_PASSWORD'] = 'mussaed0566060299'
app.config['MYSQL_DB'] = 'pythonlogin'

# Intialize MySQL
mysql = MySQL(app)

# http://localhost:5000/pythonlogin/ - this will be the login page, we need to use both GET and POST requests
@app.route('/', methods=['GET', 'POST'])
def login():
    # Output message if something goes wrong...
    msg = ''
    # Check if "username" and "password" POST requests exist (user submitted form)
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form:
        # Create variables for easy access
        username = request.form['username']
        password = request.form['password']
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s AND password = %s', (username, password))
        # Fetch one record and return result
        account = cursor.fetchone()
        # If account exists in accounts table in out database
        if account:
            # Create session data, we can access this data in other routes
            session['loggedin'] = True
            session['id'] = account['id']
            session['username'] = account['username']
            # Redirect to home page
            return redirect(url_for('home'))
        else:
            # Account doesnt exist or username/password incorrect
            msg = 'Incorrect username/password!'
    # Show the login form with message (if any)
    return render_template('index.html', msg=msg)

# http://localhost:5000/python/logout - this will be the logout page
@app.route('/pythonlogin/logout')
def logout():
    # Remove session data, this will log the user out
   session.pop('loggedin', None)
   session.pop('id', None)
   session.pop('username', None)
   # Redirect to login page
   return redirect(url_for('login'))

# http://localhost:5000/pythinlogin/register - this will be the registration page, we need to use both GET and POST requests
@app.route('/pythonlogin/register', methods=['GET', 'POST'])
def register():
    # Output message if something goes wrong...
    msg = ''
    # Check if "username", "password" and "email" POST requests exist (user submitted form)
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form and 'email' in request.form:
        # Create variables for easy access
        username = request.form['username']
        password = request.form['password']
        email = request.form['email']
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s', [username])
        account = cursor.fetchone()
        # If account exists show error and validation checks
        if account:
            msg = 'Account already exists!'
        elif not re.match(r'[^@]+@[^@]+\.[^@]+', email):
            msg = 'Invalid email address!'
        elif not re.match(r'[A-Za-z0-9]+', username):
            msg = 'Username must contain only characters and numbers!'
        elif not username or not password or not email:
            msg = 'Please fill out the form!'
        else:
            # Account doesnt exists and the form data is valid, now insert new account into accounts table
            cursor.execute('INSERT INTO accounts VALUES (NULL, %s, %s, %s)', (username, password, email))
            mysql.connection.commit()
            msg = 'You have successfully registered!'
    elif request.method == 'POST':
        # Form is empty... (no POST data)
        msg = 'Please fill out the form!'
    # Show registration form with message (if any)
    return render_template('register.html', msg=msg)

# http://localhost:5000/pythinlogin/changepassword - this will be the registration page, we need to use both GET and POST requests
@app.route('/pythonlogin/change_password', methods=['GET', 'POST'])
def change_password():
    # Output message if something goes wrong...
    msg = ''
    # Check if "username" and "password" POST requests exist (user submitted form)
    if request.method == 'POST':
        # Create variables for easy access
        username = request.form['username']
        oldpassword = request.form['oldpassword']
        newpassword = request.form['newpassword']
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s AND password = %s', (username, oldpassword))
        # Fetch one record and return result
        account = cursor.fetchone()
        # If account exists in accounts table in out database
        if account:
            #return redirect(url_for('home'))
            if account['password'] != oldpassword :
                msg = "Old password is not correct"
            else:
                cursor.execute('UPDATE accounts SET password=%s WHERE username=%s', (newpassword, username))
                mysql.connection.commit()
                msg = 'You have successfully changed password!'
        else:
            # Account doesnt exist or username/password incorrect
            msg = 'Incorrect username or old password'
    # Show the login form with message (if any)
    return render_template('changepassword.html', msg=msg)

# http://localhost:5000/pythinlogin/home - this will be the home page, only accessible for loggedin users
@app.route('/pythonlogin/home')
def home():
    # Check if user is loggedin
    if 'loggedin' in session:
        # User is loggedin show them the home page
        return render_template('home.html', username=session['username'])
    # User is not loggedin redirect to login page
    return redirect(url_for('login'))

# http://localhost:5000/pythinlogin/profile - this will be the profile page, only accessible for loggedin users
@app.route('/pythonlogin/profile')
def profile():
    # Check if user is loggedin
    if 'loggedin' in session:
        # We need all the account info for the user so we can display it on the profile page
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE id = %s', [session['id']])
        account = cursor.fetchone()
        # Show the profile page with account info
        return render_template('changepassword.html', account=account)
    # User is not loggedin redirect to login page
    return redirect(url_for('login'))

app.route('/play')
def play():
    global name
    name = request.args.get('username')
    #return render_template('gamepath.html')

@app.route("/pusher/auth", methods = ['POST'])
def pusher_authentication():
    auth = pusher_authenticate(
        channel = request.form['channel name']
        socket_id=request.form['socket_id']
        )
    return json.dumps(auth)

def main():
    return render_template('index.html') # Index refers to the 'main page'

if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit('Provide a WSGI application object as module:callable')
    app_path = sys.argv[1]
    module, application = app_path.split(':')
    module = __import__(module)
    application = getattr(module, application)
    httpd = make_server(SERVER_ADDRESS, application)
    print(f'WSGIServer: Serving HTTP on port {PORT} ...\n')
    httpd.serve_forever()
    app.run(debug = True, host = "0.0.0.0", port = 80)

server = app.wsgi_app
