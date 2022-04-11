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
 <logger name="com.lig.feignlogging.util.CustomLogger" level="TRACE" />
 ```
3) added com.lig.feignlogging.util.CustomLogger (refactored to single line feign.Logger)
4) added com.lig.feignlogging.config CustomLogger as bean in com.lig.feignlogging.config.FooConfiguration

now logs look like
```
2022-04-11 19:59:51.268 TRACE 29036 --- [           main] com.lig.feignlogging.util.CustomLogger   : [StoreClient#getStores] 
---> GET http://localhost:8080/stores HTTP/1.1
---> END HTTP (0-byte body)
2022-04-11 19:59:51.272 TRACE 29036 --- [           main] com.lig.feignlogging.util.CustomLogger   : [StoreClient#getStores] 
<--- HTTP/1.1 200 (3ms)
connection: keep-alive
content-type: application/json
date: Mon, 11 Apr 2022 16:59:51 GMT
keep-alive: timeout=60
transfer-encoding: chunked
[{"address":"random adressc7e94123-f519-4eb9-a438-e02793c0304c"},{"address":"random adress30640a76-edde-4339-bc25-0d59ef2ad590"}]
<--- END HTTP (129-byte body)
```