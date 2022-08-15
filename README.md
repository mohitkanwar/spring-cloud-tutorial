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
   ```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
   ```
   2. Discovery Client
      ```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
      ```
3. Provide name gateway in the application.yml
4. Provide server port number in the application.yml
5. Provide the following properties to auto-configure
```aidl
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
      ```
         <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
         </dependency>
   3. Provide name discovery in application.yml
   4. Provide port number 8761
   5. Enable eureka server
      ```
      @EnableEurekaServer
        ```

## Steps to create a hello world service
1. Spring initializer
2. Add the following dependencies
    ```
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
   ```
   
```
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```
3. Create a Rest Controller
