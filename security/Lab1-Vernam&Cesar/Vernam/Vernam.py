import utilities
import EncryptDecrypt
"""The main method to encrypt and decrypt with the key"""
def VernamKey():
    plain_text=input("Enter the message: ") 
    plain_text = utilities.removeDiacritics(plain_text)
    plain_text = utilities.sanitizeToAlpha(plain_text)
    cypher=input("Enter the cypher: ")
    plain_text = plain_text.encode("utf-8")
    crypted,cypher = EncryptDecrypt.vernam(plain_text,cypher)
    decrypted = EncryptDecrypt.Decrypt(crypted,cypher)
    decrypted = decrypted.decode("utf-8")
    print("Decrypted text:",decrypted)

VernamKey()




