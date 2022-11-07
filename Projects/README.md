# Melita

### Clean build whole project from command line
```
mvn clean install
```

### Check that it is running local Swagger API
### Order Service
http://localhost:9090/swagger-ui/index.html

###  Fulfilment service
http://localhost:9091/swagger-ui/index.html

###  RabitMQ docker file (docker-compose.yml) up- down 
```
docker-compose up
```

###  Agent service docker up
```
docker build -f Dockerfile -t agentservice .
docker run -p 9090:9090 -t agentservice
```

###  Fulfilment service docker up
```
docker build -f Dockerfile -t fulfilmentservice .
docker run -p 9091:9091 -t fulfilmentservice
```
## Project Structure

### Testing
The microservice skeleton project contains a service module/directory and this is a standard Springboot web project with 
JUnit 5 and JUnit 4 configured. To test the service the following command is used from the root project.

