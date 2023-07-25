
Se desarrolló un proyecto para la gestión de automoviles en la flota de una empresa por parte de los empleados.
Las tecnología usasdas son:
-   Java 11, Spring Bott 2.7, Maven 3.8, Spring Data, Spring Secutiry, Open ID 3, BD Postgres, Arquitectura Orientada a Servicios
Los api creados son:
  - Autenticación: http://localhost:8080/api/v1/authenticate (POST)
    Permite la validación de un usuario en Base de datos, previamente insertado para el objetivo del ejercio. Si el usuario 
    y password existen se genera un token con 8 horas máximo de habilitación, este tiempo es parametrizable
  - Creación de empleados: http://localhost:8080/api/employees (POST) permite crear un empleados con un ID autoincremental
    Nombre y Matricula del automovil. Los campos nombre y matricula son obligatorios
  - Consultar empleados: http://localhost:8080/api/employees (GET) consulta todos los empleados creados en la Base de Datos
  - Excluir un empleado: http://localhost:8080/api/employees/{id} (DELETE) elimina un usuario. Se valida si el ID ingresado existe en la Base de datos.
  - Creación de automóviles: http://localhost:8080/api/cars (POST) permite crear un automovil, los campos modelo y marca son obligatorios
  - Consultar todos los automóviles: http://localhost:8080/api/cars (GET)
  - Consultar todos los carros en uso:http://localhost:8080/api/cars/retirement (GET) Se consultan aquellos automoviles que 
    no estan en viajes o que al estarlo, la fecha de devolución no esté nula.
  - Eliminar un automóvil: http://localhost:8080/api/cars/{id} (DELETE)se valida previamente si existe el ID del automovil a eliminar.
  - Crear un viaje: http://localhost:8080/api/trips/{employee_id}/{car_id} (POST)Se crea un viaje agregando el ID del empleado, el ID de automovil
    La fecha de retiro se setea con la fecha actual. Al empleado se le asigna la Matricula de automovil seleccionado.
  - Devolver un automovil: http://localhost:8080/api/trips/{employee_id}/{car_id} se envian como paramteros el Id del empleado y del Automovil
    Se actualiza la fecha de devolución con la fecha actual.
  - Consultar los viajes realizados: http://localhost:8080/api/trips/delivery?month=7&year=2023 (GET), es envian como parámetros el mes y año.
    Se valida que el los viajes hayan sido concluidos si la fehca de dovolución no es nula.
Se  utilizó un controller Advice para la captura y  personalización de las excepciones


Se puede consultar el enlace de OpenID - Swagger para observar las estructuras Request y response de cada servicio.
http://localhost:8080/swagger-ui/index.html
