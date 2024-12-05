# InmoBoscoPDAM

## APP compuesta por lado servidor y lado cliente para la gestión de una inmmobiliaria

---

¡Bienvenido a Inmobosco! Este proyecto tiene como objetivo desarrollar un sistema para la gestión eficiente de una inmobiliaria localizada en España, permitiendo administrar propiedades, clientes, transacciones y más.

## **Introducción**

API programado en Java con [Spring Boot](https://spring.io/projects/spring-boot), tienes dos perfiles: dev(base de datos en H2) y un perfil prod con base de datos desplegada en docker PostgresSQL , el cliente está desarrollado en versión movil y versión web.


## Tecnologías utilizadas

- **Spring:** Se ha utilizado el framework de desarrollo de aplicaciones Java Spring para crear la API de backend y gestionar la lógica del negocio y la interacción con la base de datos.
- **Flutter:** Se ha utilizado el SDK de Flutter para desarrollar la aplicación móvil multiplataforma que interactúa con la API y proporciona una interfaz de usuario atractiva y fácil de usar.
- **Angular:** Se ha utilizado el framework Angular para desarrollar la aplicación web que se conecta a la API y ofrece una interfaz de usuario completa para la gestión inmobiliaria.
- **H2 Database:** Se utiliza la base de datos H2 en el perfil de desarrollo.
- **PostgreSQL:** Se utiliza la base de datos PostgreSQL en el perfil de producción.


## Características

- Gestión de propiedades: podrás registrar y administrar información sobre las propiedades disponibles, como características, ubicación, precio, imágenes, etc.

- Gestión de clientes: podrás mantener un registro de los clientes interesados en la compra o alquiler de propiedades, incluyendo sus datos personales y preferencias.


- Gestión de transacciones: podrás gestionar las transacciones relacionadas con la compra o alquiler de propiedades, generando contratos, recibos y facturas.

- Búsqueda avanzada: podrás realizar búsquedas avanzadas de propiedades utilizando filtros como ubicación, precio, tamaño, etc.

- Informes y estadísticas: el sistema generará informes y estadísticas sobre las propiedades, clientes y transacciones, brindando una visión completa del negocio inmobiliario.

- Interfaz intuitiva: la interfaz de usuario ha sido diseñada de forma amigable y fácil de usar, permitiendo una navegación fluida y sencilla.
## Requisitos del sistema para desplegarlo en produccion

- Java 17 o superior.
- Docker
- Flutter
- Angular 14.2 (No aseguramos el correcto funcionamiento si se usa una versión inferior.)

## Requisitos del sistema para desplegarlo en desarrollo

- Flutter
- Angular 14.2 (No aseguramos el correcto funcionamiento si se usa una versión inferior.)
- Java 17 o superior.


El Repositorio cuenta con una coleccion de Postman para poder probar todas las peticiones.
## Instalación y configuración

1. Clona este repositorio en tu máquina local.

2. Ejecuta el comando `docker-compose up -d` en la directorio raiz de la carpeta API para iniciar la base de datos en Docker.

3. Ejecta el proyecto de spring con el comando `mvn spring-boot:run` en caso de que 
lo estes ejecutando desde la consola, ten en cuenta que el proyecto por defecto viene con el prefil de produccion por lo que si no tienes activada la base de datos realizando el paso anterior no arrancara.

4. Ejecuta el comando `npm install` en el directorio raiz de la carpeta web para instalar las dependencias necesarias.

5. Ejecuta el comando `flutter pub get` en el directorio inmobosco para obtener las dependencias requeridas.

6. Inicia el frontend web ejecutando el comando `ng serve` en el directorio `src/app/` .

7. Inicia el frontend móvil ejecutando el comando `flutter run` en el directorio raiz de la carpeta inmobosco, en caso de no disponer de emulador puedes usar el comando: `flutter run -d w` para abrir el frontend móvil.

8. Accede a la interfaz web desde tu navegador en `http://localhost:4200`.

10. Accede a la aplicación móvil desde un emulador o dispositivo físico.

11. Si tienes la API arrancada podrás ver la documentacion [pinchando aqui](http://localhost:8080/swagger-ui/index.html)

12. Con la url: http://localhost:5050/ o [Pinchando aquí](http://localhost:5050/) podrás acceder a un gestor de base de datos como pgAdmin para ver la información guardada en la base de datos, si el proyecto se encuentra en el perfil de desarollo podrás ver la documentacion siguiendo la siguiente url: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Usuarios

El proyecto cuenta con dos perfiles de usuario: admin (WORKER) y usuario (USER). A continuación se detallan los usuarios predefinidos para cada perfil:

### Perfil de Usuario (USER)

- **Usuario:** Martinex
- **Contraseña:** 12345678

Este usuario tiene permisos limitados y acceso restringido a ciertas funcionalidades de la aplicación.

Este usuario en caso de que suba alguna propiedad en nuevas funcionalidades pasará a tener además el rol de OWNER desde el cual podrá ver un poco el estado de su vivienda y los gastos de esta

### Perfil de Admin (WORKER)

- **Usuario:** ElBrujo
- **Contraseña:** admin

Este usuario tiene privilegios de administrador y acceso completo a todas las funcionalidades de la aplicación.


---

# Actualizaciones :

## V2 
##### 22/06/2023

- Se ha solucionado un error que existía al querer borrar usuarios que son propietarios de una vivienda.

- Implementacion de la pantalla detallada de las propiedades.

- Implementacion de la pantalla del perfil del usuarios con las funcionalidades de editar el usuario tanto la foto de perfil como la información.

- Implementacion de la funcionalidad de favoritos.

- Implementacion del filtrado de propiedades por ciudad y por tipo de propiedad.

- Servicio de mailing cuando un usuario es registrado por un admin.

- Servicio mailing para avisar a un usuario de suspensión de su cuenta.




---

## Propietario

- David García María


¡Gracias por utilizar Inmobosco! Si tienes alguna pregunta o sugerencia, no dudes en contactarnos.

