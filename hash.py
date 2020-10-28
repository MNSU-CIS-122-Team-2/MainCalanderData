import os
import hashlib
import binascii
import sys


myNewPassword = str(sys.argv)  #assigns argument passed to myNewPassword as a string
saltString = os.urandom(32)    #creates salt (makes hash unique)

#creates hash based on random salt and argument
myKey = hashlib.pbkdf2_hmac('sha256', myNewPassword.encode('utf-8'),saltString, 10000)

myHashPassword = binascii.hexlify(myKey)  #converts hash byte value to string

print(myHashPassword.decode('utf-8')) #returns hash as string
