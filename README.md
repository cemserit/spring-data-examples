# spring-cassandra-example
Spring Data for Apache Cassandra example
## Requirements
```
JAVA 8
Cassandra 2.x (recommended v3.x)
```
## Install (for ubuntu (16.04))
### Install Cassandra
#### Local
```
echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
sudo apt-get update
sudo apt-get install cassandra
```
for more details [cassandra.apache.org](http://cassandra.apache.org) <br>
#### Docker (recommended)
##### Install docker
```
sudo apt-get update
sudo apt-get install \
     apt-transport-https \
     ca-certificates \
     curl \
     software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
sudo apt-get update
sudo apt-get install docker-ce
```
#### Cassandra for docker
```
sudo docker pull cassandra:3.11.2
```
##### Run
```
sudo docker run --name dev-cassandra -p 7000:7000 -p 7001:7001 -p 9160:9160 -p 9042:9042 -d cassandra:3.11.2
```
## How to RUN Example
```
./gradlew bootRun
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