# spring-cassandra-example
Spring Data for Redis example
## Install
### Install Cassandra
* Local OR Docker install (select only one)
#### Local
[https://redis.io/download](https://redis.io/download) <br>
#### Redis for docker
```
docker run -d --name redis -p 6379:6379 redis:4.0.1
```
## Examples
### Save person
```
curl -X POST \
  http://localhost:8080/persons \
  -H 'Content-Type: application/json' \
  -d '{
	"uuid": "8781d82d-08bc-4148-b00f-4ad7750b4934",
	"email": "cemserit@gmail.com",
	"name": "Cem Serit",
	"age": 27
}'
```
### Update person
```
curl -X PUT \
  http://localhost:8080/persons/8781d82d-08bc-4148-b00f-4ad7750b4934 \
  -H 'Content-Type: application/json' \
  -d '{
	"uuid": "8781d82d-08bc-4148-b00f-4ad7750b4934",
	"email": "cemserit@gmail.com",
	"name": "Cem",
	"age": 27
}'
```
### Get all persons
```
curl -X GET \
  http://localhost:8080/persons 
```
### Get person (find by uuid)
```
curl -X GET \
  http://localhost:8080/persons/8781d82d-08bc-4148-b00f-4ad7750b4934 
```
### Get person (find by age)
```
curl -X GET \
  http://localhost:8080/persons/custom_param?age=27
```
### Get person (find by email and age)
```
curl -X GET \
  http://localhost:8080/persons/custom_params?email=cemserit@gmail.com&age=27
```
### Delete person
```
curl -X DELETE \
  http://localhost:8080/persons/8781d82d-08bc-4148-b00f-4ad7750b4934
```
### Delete all person
```
curl -X DELETE \
  http://localhost:8080/persons
```