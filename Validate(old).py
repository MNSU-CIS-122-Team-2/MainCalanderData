
import os
import hashlib
import binascii
import sys
from base64 import b64encode
import pyodbc

#gather arguments from input and it only takes the first 2
arglist = sys.argv

#assign arguments to defined strings
LoginName = str(arglist[1])
Pwd = str(arglist[2])

#define connection to the db
db = pyodbc.connect('Driver={ODBC Driver 17 for SQL Server};'
               'Server=database-1.cczlwkbidopl.us-east-2.rds.amazonaws.com;'
              'Database=CALANDER;'
                    'UID=CISGroup2;'
                    'PWD=Calander122;'
               'Port=1433;')
#assume connection is good, gather info
data = db.cursor()

queryc = "SELECT COUNT(*) FROM PERSON where LOGIN_NAME = '" + LoginName + "'"
data.execute(queryc)

#if username not found in database, returns error and exits program
if data.fetchone()[0] <= 0:
    print("User not found!")
    data.close()
    exit()
    

#find a record mathing LoginName passed from the argument
query = "SELECT * FROM PERSON where LOGIN_NAME = '" + LoginName + "'"
data.execute(query)

#iterate the rows returned (should only return 1), we have to make sure that LOGIN_NAME will always be unique
for row in data:
    row

#close connection
data.close()

#assuming that we found a match. Assigning stored data to variables to be processed
#to compare later
StoredHashedPassword = row[4]
StoredSalt = row[5];

#convert string to hex bytes to be used to generated the hashed password from the Pwd passed by user
bsalt = bytes.fromhex(StoredSalt)

#created a new hashed based on the input passed (Pwd) and the stored salt (StoredSalt)
ProcessedHashed = hashlib.pbkdf2_hmac('sha256',Pwd.encode('utf-8'),bsalt, 10000)

#if hashed value from db matches the processed hashed, then it is a valid login
if ProcessedHashed.hex() == StoredHashedPassword:
    print("Valid")
else:
    print ("Invalid")



