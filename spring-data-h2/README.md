# spring-h2-example
Spring Data for H2 example
## Install
H2 database runs at runtime and does not need installation
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
  http://localhost:8080/persons/dbbb332b-069b-4c9b-8ddd-e8d952d6263c \
  -H 'Content-Type: application/json' \
  -d '{
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
  http://localhost:8080/persons/dbbb332b-069b-4c9b-8ddd-e8d952d6263c
```
### Delete person
```
curl -X DELETE \
  http://localhost:8080/persons/8781d82d-08bc-4148-b00f-4ad7750b4934
```