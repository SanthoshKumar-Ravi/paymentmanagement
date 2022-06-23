# Payment Management
This Project contains three services.
1) Studentregistry
2) payment
3) receipts

### StudentRegistry:
This service is used to add the students in the registry.

### Payment:
This service is used to pay the fess for a student.

### Receipts:
This service is used to fetch the receipts details.

## Prerequiste tools to run the project:
* Programming language -  Java 8
* Middleware -  Activemq server (Docker link - https://hub.docker.com/r/rmohr/activemq/)
* Build Tool -  gradle

### Application description:
The application mainly starts with studentregistry service. It will has a rest API to add student into the database and give the response with studentUniqueId(Important filed through out the application). once the student added it send a message to payment service via activemq. The basic details for a student is already available in payment database. if the user try to pay the fees by providing the enough information then the details will be stored for the corresponding student unique id. We can take the receipts by invoking receipts service.This project has cache mechanism where it will cache the receipts to avoid DB load. 



| Microservice name     | Swagger URL      | H2 Database URL  |
| :---:          |   :---:          | :---:  |
| Studentregistry        |  http://localhost:8084/swagger-ui.html        |  http://localhost:8084/h2-console/login.jsp  |
| payment         | http://localhost:8083/swagger-ui.html         | http://localhost:8083/h2-console/login.jsp   |
| receipts         | http://localhost:8085/swagger-ui.html         | http://localhost:8085/h2-console/login.jsp   |

h2 database username and passowrd are present in the yml file.

![image](https://user-images.githubusercontent.com/18610774/175254228-cf56147c-65e6-40e9-b63c-d9cffb928811.png)

