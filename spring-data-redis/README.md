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
	"email": "cemserit@gmail.com",
	"name": "Cem Serit",
	"age": 27
}'
```
### Update person
```
curl -X PUT \
  http://localhost:8080/persons/cemserit@gmail.com \
  -H 'Content-Type: application/json' \
  -d '{
	"email": "cemserit@gmail.com",
	"name": "Cem Serit",
	"age": 28
}'
```
### Get all persons
```
curl -X GET \
  http://localhost:8080/persons 
```
### Get all person email list
```
curl -X GET \
  http://localhost:8080/persons/emails
```
### Get person email list by age (find by age)
```
curl -X GET \
  http://localhost:8080/persons/age/28
```
### Delete person
```
curl -X DELETE \
  http://localhost:8080/persons/cemserit@gmail.com
```