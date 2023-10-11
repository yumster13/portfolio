""" An algorithm to encrypt into a cesar crypted text"""
def cesar(s,shift):
    cipher_text=""
    for i in range(len(s)):
        cipher_text += chr(((ord(s[i])-97)+shift)%26+97)
    
    print(cipher_text)
    return cipher_text

cesar("hellosir",13)
