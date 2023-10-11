"""The main method used to encrypt the message"""
def vernam(plain_text,key:str):
    if(len(plain_text)!= len(key)):
        size_difference= (len(plain_text)-len(key))
        key = incrementKey(key,size_difference)
        key = key.encode("utf-8")
    crypted = Encrypt(plain_text,key)
    return crypted,key

def Encrypt(plain_text:bytes,key:bytes):
    return bytes([plain_text[i] ^ key[i] for i in range(len(plain_text))])

def Decrypt(plain_text:bytes,key:bytes):
    result_bytes = bytes([a ^ b for a, b in zip(plain_text, key)])
    return result_bytes
"""Checks that the decrypted element is in the alphabet otherwise it exits the method """
def Decrypt2(plain_text: bytes, key: bytes):
    result_bytes = []
    for a, b in zip(plain_text, key):
        xored_byte = a ^ b
        if 97 <= xored_byte <= 122:
            result_bytes.append(xored_byte)
        else:
            return False

    return bytes(result_bytes)

def incrementKey(key,size_difference):
    for x in range(size_difference):
        key+=key[x]
    return key
