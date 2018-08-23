# spring-data-example
## Documentation (README)
* [Spring Data for Apache Cassandra](spring-data-cassandra/README.md)
* [Spring Data for Redis](spring-data-redis/README.md)
* [Spring Data for H2](spring-data-h2/README.md)
## How to run
### Download
```
git clone https://github.com/cemserit/spring-data-examples.git
```
### Run Cassandra 
```
./gradlew :spring-data-cassandra:bootRun
or
./gradlew build && java -jar spring-data-cassandra/build/libs/spring-data-cassandra-0.0.1-boot.jar
```
### Run Redis 
```
./gradlew :spring-data-redis:bootRun
or
./gradlew build && java -jar spring-data-redis/build/libs/spring-data-redis-0.0.1-boot.jar
```
### Run H2 
```
./gradlew :spring-data-h2:bootRun
or
./gradlew build && java -jar spring-data-h2/build/libs/spring-data-h2-0.0.1-boot.jar
```
### Swagger UI
```
http://localhost:8080/swagger-ui.html
```