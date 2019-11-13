from flask import Flask, render_template, request, redirect, url_for, session
from flask_mysqldb import MySQL
import MySQLdb.cursors
import re
import hashlib
# import base64
app = Flask(__name__)

app.secret_key = 'your secret key'

# Enter your database connection details below
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'mussaed0566060299'
app.config['MYSQL_DB'] = 'pythonlogin'

# Intialize MySQL
mysql = MySQL(app)

# def encrypt_string(string):
#     enc = []
#     for i in range(len(string)):
#         enc_c = chr((ord(string[i])) % 256)
#         enc.append(enc_c)
#     return base64.urlsafe_b64encode("".join(enc))

# def decrypt_string(enc):
#     dec = []
#     enc = base64.urlsafe_b64decode(enc)
#     for i in range(len(enc)):
#         dec_c = chr((256 + ord(enc[i])) % 256)
#         dec.append(dec_c)
#     return "".join(dec)
salting = 'S0ftD3vTw0'

def encrypt_string(hash_string):
    sha_signature = \
        hashlib.sha256(hash_string.encode()).hexdigest()
    return sha_signature

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
        password = password + salting
        password = encrypt_string(password)
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
    # Get all questions
    cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
    cursor.execute('SELECT * FROM questions')
    # Fetch one record and return result
    questions = cursor.fetchall()
    msg = ''
    username = ''
    password = ''
    email = ''
    question = ''
    answer = ''
    # Check if "username", "password" and "email" POST requests exist (user submitted form)
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form and 'email' in request.form:
        # Create variables for easy access
        username = request.form['username']
        password = request.form['password']
        confirmpw = request.form['repassword']
        email = request.form['email']
        question = request.form['question']
        answer = request.form['answer']
        answer = answer + salting
        encrypt_answer = encrypt_string(answer)
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
        elif password != confirmpw:
            msg = 'Please input confirm password again!'
        else:
            # Account doesnt exists and the form data is valid, now insert new account into accounts table
            password = password + salting
            password = encrypt_string(password)
            cursor.execute('INSERT INTO accounts VALUES (NULL, %s, %s, %s, %s, %s, %s, %s, %s)', (username, password, email, question, encrypt_answer, 0, 0, 0))
            mysql.connection.commit()
            username = ''
            password = ''
            email = ''
            question = ''
            answer = ''
            msg = 'You have successfully registered!'
    elif request.method == 'POST':
        # Form is empty... (no POST data)
        msg = 'Please fill out the form!'        
    # Show registration form with message (if any)
    return render_template('register.html', msg=msg, username=username, password=password, email=email, questions=questions, quetion=question, answer=answer)

@app.route('/pythonlogin/forgot', methods=['GET', 'POST'])
def forgot():
    username = ''
    msg = ''
    if request.method == 'POST':
        username = request.form['username']
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s', [username])
        # Fetch one record and return result
        account = cursor.fetchone()
        # If account exists in accounts table in out database
        if account:
            # Get all questions
            cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
            cursor.execute('SELECT * FROM questions WHERE id = %s', [account["question"]])
            # Fetch one record and return result
            question = cursor.fetchone()
            return render_template('confirm_question.html', username=username, question=question)
        else:
            msg = 'Username does not exist'
    return render_template('forgot.html', msg=msg, username=username)

@app.route('/pythonlogin/confirm_question', methods=['POST'])
def confirm_question():
    username = ''
    question = ''
    answer = ''
    msg = ''
    if request.method == 'POST':
        username = request.form['username']
        question = request.form['question']
        answer = request.form['answer']
        answer = answer + salting
        answer = encrypt_string(answer)
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s And question = %s And answer = %s', (username, question, answer))
        # Fetch one record and return result
        account = cursor.fetchone()
        # If account exists in accounts table in out database
        if account:
            return render_template('reset_password.html', username=username)
        else:
            msg = 'Your answer is wrong'
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s', [username])
        # Fetch one record and return result
        account = cursor.fetchone()
        # Get all questions
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT questions.id, questions.question FROM questions INNER JOIN accounts ON questions.id = accounts.question WHERE questions.id = %s', [account["question"]])
        # Fetch one record and return result
        question = cursor.fetchone()
    return render_template('confirm_question.html', msg=msg, username=username, question=question)

# http://localhost:5000/pythinlogin/resetpassword - this will be the registration page, we need to use both GET and POST requests
@app.route('/pythonlogin/reset_password', methods=['POST'])
def reset_password():
    # Output message if something goes wrong...
    msg = ''
    username = ''
    newpassword = ''
    confirmpw = ''

    # Check if "username" and "password" POST requests exist (user submitted form)
    if request.method == 'POST':
        # Create variables for easy access
        username = request.form['username']
        newpassword = request.form['password']
        confirmpw   = request.form['repassword']
        # Check if account exists using MySQL
        cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cursor.execute('SELECT * FROM accounts WHERE username = %s', [username])
        # Fetch one record and return result
        account = cursor.fetchone()
        # If account exists in accounts table in out database
        if account:
            #return redirect(url_for('home'))
            if newpassword != confirmpw:
                msg = 'Please input confirm password again!'
            else:
                newpassword = newpassword + salting
                newpassword = encrypt_string(newpassword)
                cursor.execute('UPDATE accounts SET password=%s WHERE username=%s', (newpassword, username))
                mysql.connection.commit()
                # msg = 'You have successfully changed password!'
                return redirect(url_for('login'))
        else:
            # Account doesnt exist or username/password incorrect
            msg = 'Incorrect username'
    # Show the login form with message (if any)
    return render_template('reset_password.html', msg=msg, username=username)

# http://localhost:5000/pythinlogin/changepassword - this will be the registration page, we need to use both GET and POST requests
@app.route('/pythonlogin/change_password', methods=['GET', 'POST'])
def change_password():
    # Output message if something goes wrong...
    msg = ''
    username = ''
    oldpassword = ''
    newpassword = ''
    confirmpw = ''

    # Check if "username" and "password" POST requests exist (user submitted form)
    if request.method == 'POST':
        # Create variables for easy access
        username = request.form['username']
        oldpassword = request.form['oldpassword']
        oldpassword = oldpassword + salting
        oldpassword = encrypt_string(oldpassword)
        newpassword = request.form['newpassword']
        confirmpw   = request.form['repassword']
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
            elif newpassword != confirmpw:
                msg = 'Please input confirm password again!'
            else:
                newpassword = newpassword + salting 
                newpassword = encrypt_string(newpassword)
                cursor.execute('UPDATE accounts SET password=%s WHERE username=%s', (newpassword, username))
                mysql.connection.commit()
                # msg = 'You have successfully changed password!'
                return redirect(url_for('profile'))
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
        # password = decrypt_string(str(account['password']))
        # Show the profile page with account info
        return render_template('profile.html', account=account)
    # User is not loggedin redirect to login page
    return redirect(url_for('login'))

# http://localhost:5000/pythinlogin/delete - this will delete user
@app.route('/pythonlogin/delete', methods=['GET', 'POST'])
def delete():
    # Check if user is loggedin
    if 'loggedin' in session:
        if request.method == 'POST':
            user_id = request.form['user_id']
            cursor = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
            cursor.execute('DELETE FROM accounts WHERE id = %s', [user_id])
            mysql.connection.commit()

        # Remove session data, this will log the user out
        session.pop('loggedin', None)
        session.pop('id', None)
        session.pop('username', None)
        # Redirect to login page
        return redirect(url_for('login'))
    # User is not loggedin redirect to login page
    return redirect(url_for('login'))

if __name__ == "__main__":
    app.run(debug=True)