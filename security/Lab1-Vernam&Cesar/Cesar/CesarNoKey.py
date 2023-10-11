""" The probabilities table for the English language"""
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

"""Divides the text in smaller strings"""
"""subsitring_length -> the length of the substring you want"""
def SubstringText(substring_length,text_length):
    tab = []
    for start in range(0, len(text_length), substring_length):
        end = start + substring_length
        substring = text_length[start:end]  
        tab.append(substring)
    return tab

"""A method to count the repetitions of characters in a table
Returns a dict -> dictionnary{"String","number of repetitions"}"""
def countRepetitions(tab):
    my_dict = {}
    for i in range(0,len(tab)-1):
        if tab[i] not in my_dict:
            my_dict[tab[i]] = 1
        else: 
            my_dict[tab[i]] +=1
    return my_dict

"""Calculates the frequency of eache substring in the given text"""
def calculateFrequency(my_dict,tab):
    for key in my_dict:
        my_dict[key] = my_dict[key] / len(tab)
    return my_dict

"""Sorts the frequency in a dict -> Greater value, ...., Smaller value"""
def sortFrequency(my_dict):
    my_dict = dict(sorted(my_dict.items(), key=lambda item: item[1], reverse=True))
    return my_dict

"""Calculates the different probabilities of each shift of the cesar cypher"""
def probabilites(freq_dict):
    phi_arr={}
    for i in range(26):
        phi_i=0
        for j in freq_dict:
            p_val=prob_of_alpha.get(chr(ord(j)-i))
            if(p_val!=None):
                phi_i+=freq_dict[j]*p_val
            phi_arr[i]=round(phi_i,4)
    phi_arr=sorted(phi_arr,key=phi_arr.get, reverse=True)
    return phi_arr

"""Allows us to decrypt the message"""
def decrypt(s,shift):
    cipher_text=""
    for i in range(len(s)):
        cipher_text += chr(((ord(s[i])-97)-shift)%26+97)
    
    print(cipher_text)
    return cipher_text

"""The main method of the Cesar NoKey decryption
Divides the text in substrings of size 1
Counts the repetitions of a such substring
Calculates the different Frequencies of each substring -> will be used later on for the probabilities
Sorts the frequencies 
Calculates the different frequencies of every possible shift. """
def DecypherCesarNoKey(ciphered_text):
    tab = SubstringText(1,ciphered_text)
    tabRepetitions = {}
    tabRepetitions = countRepetitions(tab)
    tabRepetitions = calculateFrequency(tabRepetitions,tab)
    tabRepetitions = sortFrequency(tabRepetitions) 
    phi_arr = probabilites(tabRepetitions)
    print("Deciphering:\n")    
    for i in range(10):
    	print("Encoded word in Caeser cipher is: ",decrypt(tab,phi_arr[i]))     
