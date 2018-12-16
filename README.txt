[Prerequisites]
- Edit DB connection at "tours-spring-backend/src/main/resources/application.properties"

[Run]
- Go to root project folder
- Run "mvn spring-boot:run"

[Predefine 2 users]
- GET http://localhost:8080/users/save?username=admin&password=admin12&role=ROLE_ADMIN
- GET http://localhost:8080/users/save?username=john&password=john12&role=ROLE_USER

[Tests]
-Admin Role-
- POST http://localhost:8080/tours/refresh
Request body : { "filter": "london" }

-User Role-
- GET http://localhost:8080/tours
- GET http://localhost:8080/tours?filter=london