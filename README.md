# logging-feign-native

feign native logging enabled with 
1) application.yml
```
feign:
  client:
    config:
      default:
        loggerLevel: full
      stores:
        loggerLevel: full
```
2) logback-spring.xml

```
 <logger name="org.zalando.logbook" level="TRACE" />
 ```
3) pom.xml
```
<dependency>
			<groupId>org.zalando</groupId>
			<artifactId>logbook-spring-boot-starter</artifactId>
			<version>2.14.0</version>
		</dependency>
		<dependency>
			<groupId>org.zalando</groupId>
			<artifactId>logbook-openfeign</artifactId>
			<version>2.14.0</version>
		</dependency>
```
logs will look like
```
2022-04-11 20:22:02.132 TRACE 31352 --- [           main] org.zalando.logbook.Logbook              : Outgoing Request: ed105c0fcfeef249
Remote: localhost
GET http://localhost:8080/stores HTTP/1.1

2022-04-11 20:22:02.361 TRACE 31352 --- [           main] org.zalando.logbook.Logbook              : Incoming Response: ed105c0fcfeef249
Duration: 233 ms
HTTP/1.1 200 OK
connection: keep-alive
content-type: application/json
date: Mon, 11 Apr 2022 17:22:02 GMT
keep-alive: timeout=60
transfer-encoding: chunked

[{"address":"random adressda026140-a952-4904-aba0-6d3b51f46b12"},{"address":"random adress4f12de15-9b89-412f-b121-8be62a9d0cac"}]
```