#============================================================================
# Name        : equations_web_server.py
# Author      : Stephen Mingolelli, Tinkernut
# Description :  Web server for the equations game using Flask.
#============================================================================

from flask import Flask, render_template

server = Flask(__name__)

@server.route("/")

def Main():
    return render_template('index.html')

if __name__ == "__main__":
    server.run(debug = True, host = "0.0.0.0", port = 80)
