import CesarNoKey,Cesar_encrypt
import utilities
"""A demo with a shorter text to decrypt"""
if __name__ == '__main__':
    Original_text = "hellosir"
    text = utilities.sanitizeToAlpha(Original_text)   
    text = utilities.removeDiacritics(text)
    ciphered_text=Cesar_encrypt.cesar(Original_text,15)
    print("Original text:\n", text)
    CesarNoKey.DecypherCesarNoKey(ciphered_text)
