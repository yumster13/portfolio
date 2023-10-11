import utilities

from statistics import mode
import itertools
import time
import EncryptDecrypt,GCD
import concurrent.futures

prob_of_alpha={
    'a':0.080,
    'b':0.015,
    'c':0.030,
    'd':0.040,
    'e':0.130,
    'f':0.020,
    'g':0.015,
    'h':0.060,
    'i':0.065,
    'j':0.005,
    'k':0.005,
    'l':0.035,
    'm':0.030,
    'n':0.070,
    'o':0.080,
    'p':0.020,
    'q':0.002,
    'r':0.065,
    's':0.060,
    't':0.090,
    'u':0.030,
    'v':0.010,
    'w':0.015,
    'x':0.005,
    'y':0.020,
    'z':0.002
}

sortedFrequencies = probabilities = GCD.sortFrequency(prob_of_alpha)
"""Increment the key size to the length of the ciphered text"""
def incrementKey(key,size_difference):
    for x in range(size_difference):
        key+=key[x]
    return key
"""Checks if all the elements are letters in the text -> used for the decryption"""
def OnlyLetters(text:str):
    for character in text:
        if not character.isalpha():
            return False
    return True

"""Generate all the possible keys"""
def CalculatePossibleKeys(crypted:bytes,keys,length):
    PossibeKeys = []
    for key in keys:
        newKey = incrementKey(key,length).encode("utf-8")
        Original_Text = EncryptDecrypt.Decrypt2(crypted,newKey)
        if(Original_Text != False):
            Original_Text = Original_Text.decode("utf-8")
            tab = GCD.SubstringText(1,Original_Text)
            tabRepetitions = GCD.countRepetitions(tab)
            tabRepetitions = GCD.calculateFrequency(tabRepetitions,tab)
            tabRepetitions = sort_dict(tabRepetitions,sortedFrequencies)
            if(is_within_3_percent(tabRepetitions,sortedFrequencies) == True):
                print("Possible key:",key)
                PossibeKeys.append(key)
    return PossibeKeys

def sort_dict(dict1, dict2):
    sorted_dict = {}
    for key in dict2:
        if key in dict1:
            sorted_dict[key] = dict1[key]
    return sorted_dict
"""Check the value is within 3 percent of the real value in the english alphabet frequencies"""
def is_within_3_percent(dict1, dict2):
    for key in dict1:
        if key not in dict2:
            return False
        if abs(dict1[key] - dict2[key]) > 0.03:
            return False
    return True
"""Genereates all the possible keys with a given length with all the letters in the alphabet
    The key can only contain letters of the alphabet"""
def generate_keys(start_key, end_key, key_length):
    letters = 'abcdefghijklmnopqrstuvwxyz'
    combinations = itertools.product(letters, repeat=key_length)
    keys = []
    for combination in combinations:
        key = ''.join(combination)
        if key >= start_key and key <= end_key:
            keys.append(key)
    return keys

ciphered_text = ""
plain_text=""
"""Calculate the possible key length"""
def CalculateKey(ciphered_text):
    gcd = GCD.MoyenneGCD(ciphered_text)
    gcd = int(round(gcd, 0))
    print("size of key:", gcd)
    return gcd
"""Added all the keys to the Allkeys variable """
def add_keys_to_AllKeys(AllKeys, keys):
    if len(keys) > 0:
        AllKeys.append(keys)
"""Print all the possible keys after the decryption"""
def ShowKeys(gcd,plain_text,ciphered_text):
    keys1 = generate_keys(incrementKey("a",gcd),incrementKey("f",gcd),gcd)
    keys2 = generate_keys(incrementKey("g",gcd),incrementKey("l",gcd),gcd)
    keys3 = generate_keys(incrementKey("m",gcd),incrementKey("s",gcd),gcd)
    keys4 = generate_keys(incrementKey("t",gcd),incrementKey("z",gcd),gcd)
    AllKeys = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
        seconds = time.time()
        future1 = executor.submit(lambda: add_keys_to_AllKeys(AllKeys, CalculatePossibleKeys(ciphered_text, keys1, len(plain_text))))
        future2 = executor.submit(lambda: add_keys_to_AllKeys(AllKeys, CalculatePossibleKeys(ciphered_text, keys2, len(plain_text))))
        future3 = executor.submit(lambda: add_keys_to_AllKeys(AllKeys, CalculatePossibleKeys(ciphered_text, keys3, len(plain_text))))
        future4 = executor.submit(lambda: add_keys_to_AllKeys(AllKeys, CalculatePossibleKeys(ciphered_text, keys4, len(plain_text))))
        seconds_after = time.time()
    seconds_after = time.time()
    print("numbers of seconds to find the keys size:",gcd,"seconds:",seconds_after-seconds)
    return AllKeys
