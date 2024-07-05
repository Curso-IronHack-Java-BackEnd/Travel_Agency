# PROYECTO FIN DE BOOTCAMP: TRAVEL_AGENCY
by Miguel Angel Aparicio

## Descripción General

Este proyecto tiene como objetivo desarrollar un sistema integral de gestión para una agencia de viajes utilizando Spring Boot, un potente framework de Java. El sistema está diseñado para manejar las operaciones diarias de la agencia, mejorando la eficiencia y facilitando la gestión de clientes, agentes y gerentes.


## DIAGRAMA DE CLASES
![Diagrama de clases](https://raw.githubusercontent.com/Curso-IronHack-Java-BackEnd/Travel_Agency/main/Diagrama%20de%20clases.png?token=GHSAT0AAAAAACQT7AGFPTKBNMHUIZSAAX64ZUH57RQ)

## Configuración
**Aplicattion.properties:**

    spring.application.name=Travel_Agency  
    spring.datasource.url=jdbc:mysql://localhost:3306/travel_agency_db  
    spring.datasource.username=root  
    spring.datasource.password=password  
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
    spring.jpa.show-sql=true  
    spring.jpa.hibernate.ddl-auto=create

**BBDD:**
*Sistema de gestión:* MySQL
*URL:* jdbc:mysql://localhost:3306
*Schema:* travel_agency_db

**Dependencias Maven:**

 - Lombok
 - Spring Web
 - Spring Security
 - JDBC API
 - Spring Data JPA
 - MySQL Driver
 - Validation
 - CycloneDX SBOM support

## Tecnologías Utilizadas
-   **Backend:**
    -   Java 11
    -   Spring Boot
    -   Spring Data JPA
    -   Spring Security
-   **Base de Datos:**
    -   MySQL
-   **Herramientas de Gestión y Construcción:**
    -   Maven

## Estructura de Controladores y Rutas

### Controlador de Customers
    @RestController
    @RequestMapping("/customers")
    public class CustomerController {
        // CRUD Operations for Customers
    }
-   `GET /`: Obtener el Customer propio.
-   `PUT /`: Modificar el perfil propio.
-   `POST /`: Crear el propio Customer.
-   `GET /travels`: Obtener los Travels del Customer.
-   `GET /reservations`: Obtener las Reservations del Customer.

### Controlador de Agents

    @RestController
    @RequestMapping("/agents")
    public class AgentController {
        // CRUD Operations for Agents
    }
-   `GET /`: Obtener el Agent propio.
-   `PUT /`: Modificar el perfil propio.
-   `POST /`: Crear el propio Agent.
-   `GET /customers`: Obtener todos los Customers.
-   `GET /customers/{id}`: Obtener un customer por su Id.
- ` POST /customers:` : Crear un nuevo Customer.
-  `DELETE /customers/{id}` : Borrar un Customer por su Id.
-  `GET /reservations/{id}` : Obtener una reserva por su Id.
-  `GET /reservations/customers{id}` : Obtener una lista de Reservations por CustomerId.
-  `POST /reservations`: Crear una nueva Reservation.
-  `PUT /reservations/{id}`: Modificar una Reservations por su Id.
-  `DELETE /reservations/{id}`: Eliminar una Reservations por su Id.
-  `GET /travels`: Obtener todos los Travels.
-  `GET /travels/{id}`: Obtener un Travel por su Id.
-  `GET /travels/customers/{id}`: Obtener una lista de Travels por su CustomerId.
-  `POST /travels`: Crear un nuevo Travel.
-  `PUT /travels/{id}`: Modificar un Travel por su Id.
-  `GET /hotels`: Obtener todos los Hotels.
-  `GET /flights`: Obtener todos los Flights.
-  `GET /travels/{id}/flights`: Obtener todos los Flights de un travel por su Id.
-  `GET /travels/{id}/hotels`: Obtener todos los Hotels de un travel por su Id.
-  `POST /fulBill`: Crear una Bill de un travel por su Id.
-  `GET /travels/{id}`/bills: Obtener la Bill de un Travel por su Id y si no existe la crea.

### Controlador de Managers

    @RestController
    @RequestMapping("/managers")
    public class ManagerController {
        // CRUD Operations for Managers
    }
-   `GET /`: Obtener el Manager propio.
-   `PUT /`: Modificar el perfil propio.
-   `POST /`: Crear el propio Manager.
-  `DELETE /`: Elimina su propio perfil
-   `GET /all`: Obtener todos los Managers.
-   `GET /{id}`: Obtener un Manager por su Id.
-  `GET /agents`: Obtener todos los Agents.
-  `GET /agents/{id}`: Obtener un Agent por su Id.
-  `POST /agents`: Crear un nuevo Agent.
-  `PATCH /agents/{id}`: Modificar la comissionRate de un Agent por su Id.
-  `DELETE /agents/{id}`: Eliminar un Agent por su Id.
-  `GET /customers`: Obtener todos los Customers.
-  `GET /customers/{id}`: Obtener un Customer por su Id.
-  `GET /hotels`: Obtener todos los Hotels.
-  `GET /flights`: Obtener todos los Flights.
-  `GET /agents/specialization`: Obtener todos los Agents de una Specialization concreta.
-  `GET /travels/agents/{id}`: Obtener todos los travels de un Agent por su Id.
-  `GET /customers/agents/{id}`: Obtener todos los Customers de un Agent por su Id.

## Enlaces Adicionales
[Repositorio en GitHub](https://github.com/Curso-IronHack-Java-BackEnd/Travel_Agency.git)
[Presentacion del proyecto](https://prezi.com/p/edit/yjpi5ur0sq2x/)

## Trabajo futuro
Hay mucho en lo que se puede seguir mejorando la aplicación:

 - **Mejoras en la interfaz de usuario.**
	 - Desarrollar una interfaz de usuario más intuitiva y amigable (Front-end)
- **Añadir nuevas clases con nuevas funcionalidades.**
	- Se podrían añadir Tours, Guías u otras actividades extra que se contratarían con el Travel.
- **Nuevas Consultas más complejas.**
	- Obtener todos los Amenities de un Hotel
	- Obtener todos los Extras de un Travel
	- Poder crear Hotels, Flights, Amenities y Extras
	- Crear FlightBooking y HotelBooking y poder añadirles los extras

## Recursos

Lecciones del Bootcamp de IronHack
[Oracle Documentation](https://docs.oracle.com/javase/tutorial/index.html)
[Spring Data Documentation](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
[Java W3Schools](https://www.w3schools.com/java/default.asp)

## Miembros del equipo

Miguel Angel Aparicio Romero

