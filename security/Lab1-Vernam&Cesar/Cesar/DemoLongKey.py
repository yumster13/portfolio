import CesarNoKey,Cesar_encrypt
import utilities
"""A demo with a longer text to decrypt"""
if __name__ == '__main__':
    Original_text ="thisisthetexttodecrypt"        				
    text = utilities.sanitizeToAlpha(Original_text)   
    text = utilities.removeDiacritics(text)
    ciphered_text=Cesar_encrypt.cesar(Original_text,4)
    print("Original text:\n", text)
    CesarNoKey.DecypherCesarNoKey(ciphered_text)
