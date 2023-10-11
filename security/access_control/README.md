# SECG4 - Access Control
## Group Members
58412 Lecky-Thompson William
58744 Taha Ahmed

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
