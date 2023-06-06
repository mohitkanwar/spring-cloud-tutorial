# Spring Cloud Tutorial

This tutorial aims to create a spring cloud architecture that comprises of the following components
1. Gateway
2. Discovery
3. Hello Microservice
4. World Microservice
5. Hello World Microservice
6. Cloud configuration
7. Circuit Breaking
8. Rate Limiting
9. Redis Caching
10. Security implementation



## Steps to setup Gateway
1. Spring initializer
2. Add the following dependencies :
   1. Gateway
   ```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
   ```
   2. Discovery Client
      ```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
      ```
3. Provide name gateway in the application.yml
4. Provide server port number in the application.yml
5. Provide the following properties to auto-configure
```yaml
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
```
## Steps to setup Discovery Service
   1. Spring Initializer
   2. Add the following dependencies
      ```xml
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
         </dependency>
   3. Provide name discovery in application.yml
   4. Provide port number 8761
   5. Enable eureka server
      ```java
      @EnableEurekaServer
        ```

## Steps to create a hello world service
1. Spring initializer
2. Add the following dependencies
    ```xml
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
   ```
   
```xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```
3. Create a Rest Controller

## Config as a microservice
Why to create configuration as a microservice?
- Externalized
- environment specific
- consistent
- version history
- real-time management

Creating config for microservices involve two steps
1. Setting a config Server
2. Setting a config client

### Setting a config server
1. Create a new microservice, and add the follwing dependencies
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
```
2. Create a git repo to host configuration for microservices
   1. application-name.yml
3. Additional property 
   1. spring.cloud.config.server.git.uri=${HOME}/projects/proof-of-code/config-server-git-repo
4. @EnableConfigServer annotation to enable the server

The properties can be checked on the url  
http://localhost:8888/hello-world/default

### Setting a config client
1. Create a microservice
2. Add the following dependencies

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
 ```
3. Define the following properties
```yaml

spring:
  application:
    name: hello-world
  cloud:
    config:
      uri: http://localhost:8888
server:
  port: 1102

management:
  endpoints:
    web:
      exposure:
        include: "*"
    env:
      post:
        enabled: true
    restart:
      enabled: true
endpoints:
  sensitive: true
  actuator:
    enabled: true
```

4. use properties in code
5. Add `@RefreshScope` on the classes using properties that should be refreshed
6. Change the properties in the git location and commit
7. The config server picks changes immedietely
8. The config client picks changes when the actuator endpoint is hit
   1. ``curl --location --request OPTIONS 'http://localhost:1102/actuator/refresh'``
