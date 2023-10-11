# Security
## Members
58412 Lecky-Thompson William
58744 Taha Ahmed

# Vernam & Cesar cypher

## Python not installed
### Execute these three commands to install python and pip
`apt install python3.11`</br>
`sudo apt install python-is-python3`</br>
`sudo apt install python3-pip`</br>
## Run the project demo
### To run the project demo execute this command
`python TestCodes.py`</br>
This program will run 2 different decryptions: Cesar first then Vernam.</br>
For Cesar, they will be 2 different demos: one with a long text to decrypt and one with a shorter text.</br>
For Vernam it will run 3 different key searches: a 2 length key, a 3 length key and a 4 length key.</br>

# Access Control

## Build and run
Build the jar files:
```shell
mvn package
```

It should have build `target/server.jar` and `target/client.jar`. You can then
run it

### Run on windows
```shell
Start-Process java -ArgumentList "-jar", "target/server.jar" -WindowStyle Hidden
```
And
```shell
java -jar target/client.jar
```
### Run on Linux
```shell
java -jar target/server.jar &
```
And
```shell
java -jar target/client.jar
```