#============================================================================
# Name        : equations_web_server.py
# Author      : Stephen Mingolelli, Tinkernut
# Description :  Web server for the equations game using Flask. It refers
# to a 'static' and 'templates' directory, which act as the source material
# for any scripts, images, text, etc. that will appear on a page.
#============================================================================

from flask import Flask, render_template

server = Flask(__name__)

@server.route("/")

def main():
    return render_template('index.html') #Index refers to the 'main page'

if __name__ == "__main__":
    server.run(debug = True, host = "0.0.0.0", port = 80)
