import math
from statistics import mode
from collections import OrderedDict
from operator import itemgetter

def sortFrequency(my_dict):
    my_dict = dict(sorted(my_dict.items(), key=lambda item: item[1], reverse=True))
    return my_dict

def sort_dict(dict1, dict2):
    sorted_dict = {}
    for key in dict2:
        if key in dict1:
            sorted_dict[key] = dict1[key]
    return sorted_dict

"""Calculate the prime numbers"""
def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True
"""Remove the prime numbers"""
def RemovePrime(numbers):
    non_primes = [x for x in numbers if not is_prime(x)]
    return non_primes
"""Calculate the GCD of all the numbers given in the parameters"""
def calculateGCD(numbers):
    if(len(numbers) != 0):
        result = numbers[0]
        for number in numbers[1:]:
            if math.gcd(result, number) != 1:
                result = math.gcd(result,number)
        return result
    return 0

def calculateFrequency(my_dict,tab):
    for key in my_dict:
        my_dict[key] = my_dict[key] / len(tab)
    return sortFrequency(my_dict)
"""Find the shift size with the repeteted String in the ciphered text"""
def findShiftSize(sizeCut,repetitionArray : OrderedDict, cipheredText : str):
    shiftLengthDict = {}
    for substr in repetitionArray:
        count = repetitionArray[substr]
        shiftLengthArray = []
        if(count > 1):
            startIndex = cipheredText.find(substr, 0, len(cipheredText))
            startIndex+=sizeCut-1
            for i in range(0, count-1):
                findIndex = cipheredText.find(substr, startIndex, len(cipheredText))
                shiftLength = findIndex-startIndex
                startIndex+=shiftLength+(sizeCut-1)
                shiftLengthArray.append(shiftLength)
            shiftLengthDict[substr] = mode(shiftLengthArray)
    return shiftLengthDict

def SubstringText(substring_length,text_length):
    tab = []
    for start in range(0, len(text_length), substring_length):
        end = start + substring_length
        substring = text_length[start:end]  
        tab.append(substring)
    return tab

def countRepetitions(tab):
    my_dict = {}
    for i in range(0,len(tab)):
        if tab[i] not in my_dict:
            my_dict[tab[i]] = 1
        else: 
            my_dict[tab[i]] +=1
    my_dict = OrderedDict(sorted(my_dict.items(), key=itemgetter(1), reverse=True)) 
    return my_dict
"""The main method to calculate the GCD -> the possible length of the key"""
def GCDvalue(sizeCut, textToAnalyse):
    tab = SubstringText(sizeCut,textToAnalyse)
    tabRepetitions = {}
    spaces = {}
    tabRepetitions = countRepetitions(tab)
    spaces = findShiftSize(sizeCut,tabRepetitions, textToAnalyse)
    tab = spaces.values()
    tab = RemovePrime(tab)
    gcd = calculateGCD(tab)
    return gcd
"""The average of the GCD calculated -> which will be the length of the key"""
def MoyenneGCD(ciphered_text):
    sizeCuts = []
    for i in range(2,int(len(ciphered_text)/2)):
        sizeCuts.append(i)
    gcd = 0
    dividor = 0
    for i in range(0,len(sizeCuts)):
        if(1<=GCDvalue(sizeCuts[i],ciphered_text)<20): 
            dividor+=1
            gcd+=GCDvalue(sizeCuts[i],ciphered_text)
    return gcd/dividor

