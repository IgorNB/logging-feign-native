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
 <logger name="com.lig.feignlogging.controller.outbound.StoreClient" level="TRACE" />
 ```

looks like this
```
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] <--- HTTP/1.1 200 (141ms)
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] connection: keep-alive
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] content-type: application/json
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] date: Mon, 11 Apr 2022 16:13:48 GMT
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] keep-alive: timeout=60
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] transfer-encoding: chunked
2022-04-11 19:13:48.840 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] 
2022-04-11 19:13:48.841 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] [{"address":"random adress53427d5d-4314-40dc-a90d-c6aa987245a6"},{"address":"random adress0b366730-b305-48ba-88f4-ce752d5e042c"}]
2022-04-11 19:13:48.841 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] <--- END HTTP (129-byte body)
2022-04-11 19:13:48.874 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] ---> GET http://localhost:8080/stores HTTP/1.1
2022-04-11 19:13:48.874 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] ---> END HTTP (0-byte body)
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] <--- HTTP/1.1 200 (3ms)
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] connection: keep-alive
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] content-type: application/json
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] date: Mon, 11 Apr 2022 16:13:48 GMT
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] keep-alive: timeout=60
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] transfer-encoding: chunked
2022-04-11 19:13:48.878 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] 
2022-04-11 19:13:48.879 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] [{"address":"random adress8ce0efca-4c1b-4512-8ead-f80b2cd96fe5"},{"address":"random adressea3d302c-253b-4af6-8059-4fe19fd015ea"}]
2022-04-11 19:13:48.879 DEBUG 28248 --- [           main] c.l.f.controller.outbound.StoreClient    : [StoreClient#getStores] <--- END HTTP (129-byte body)

```