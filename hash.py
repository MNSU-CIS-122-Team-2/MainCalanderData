
import os
import hashlib
import binascii
import sys
from base64 import b64encode
import pyodbc  #must be installed in Python


#gather arguments from input and it only takes the first 2
arglist = sys.argv
LoginName = str(arglist[1])
myNewPassword = str(arglist[2])


saltString = os.urandom(16)      #generate random salt (makes hash unique) in bytes
saltedString = saltString.hex();     #convert to hexadecimal to store in database
bsaltedString = bytes.fromhex(saltedString);   #convert to bytes for algorithm for hash
                  

#creates hash based on random salt and argument
myKey = hashlib.pbkdf2_hmac('sha256', myNewPassword.encode('utf-8'),bsaltedString, 10000)

myHashPassword = myKey.hex()  #converts hash byte value to string for database


#Connect to the database
db = pyodbc.connect('Driver={ODBC Driver 17 for SQL Server};'
               'Server=database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com;'
              'Database=CALANDER;'
                    'UID=CISGroup2;'
                    'PWD=Calander122;'
               'Port=1433;')

#assume connection is good, gather info
data = db.cursor()

#check to see if user exits in database
queryc = "SELECT COUNT(*) FROM PERSON where LOGIN_NAME = '" + LoginName + "'"
data.execute(queryc)

#if username not found in database, returns error and exits program
if data.fetchone()[0] <= 0:
    print("User not found!")
    data.close()
    exit()

#if username exists update Hash and Salt in database
data.execute("UPDATE CALANDER.dbo.PERSON SET H_PASS = '" + myHashPassword + "', SALT = '" + saltedString + "'" "where LOGIN_NAME = '" + LoginName + "'")
data.commit()
data.close() 

print("Hash and Salt inserted successfully!")
