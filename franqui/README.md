# Franquicias

Para mas informacion puede descargar manual en 
https://1drv.ms/w/c/cc99654aeb02a925/ESJEwUGnVeNHg1lYuOKP0isB1reGXB4MamRloOTJGLPGfQ?e=qbKkuO

Este proyecto es una API RESTful desarrollada para administrar franquicias, sucursales y productos.

Está implementado en Java 21 utilizando Spring Boot 3.4.4 y cuenta con los siguientes módulos y tecnologías:

Persistencia de Datos:
Utiliza JPA y la dependencia de MySQL (versión 9.2.0) para el almacenamiento persistente. Además,
se incluye H2 Database para propósitos de prueba y desarrollo el perfil se puede cambiar en el properties.
spring.profiles.active=desarrollo se cambia a spring.profiles.active=prod

Dependencias y Utilidades:
Incluye Spring Web para la creación de endpoints, Spring Devtools para facilitar el desarrollo y Lombok para simplificar el código eliminando código repetitivo,
mysql para la conexion a la base de datos, h2 para la base de pruebas y jpa para las operaciones 

Endpoints CRUD:
Se han implementado tres controladores que exponen endpoints GET, POST, PUT y DELETE para:

Franquicias

Sucursales

Productos

Docker:
Se incluye un archivo Dockerfile para empaquetar la aplicación en un contenedor Docker, facilitando su despliegue en entornos locales o en la nube.

Índice
Características del Proyecto

Requisitos Previos

Instalación y Configuración

Ejecución de la Aplicación

Endpoints Disponibles

Construcción y Ejecución con Docker

Tecnologías Utilizadas

Contribuciones

Licencia

Características del Proyecto
Administración de Franquicias:
Permite agregar, modificar y eliminar franquicias. Cada franquicia contiene un listado de sucursales.

Gestión de Sucursales:
Cada sucursal pertenece a una franquicia y contiene un listado de productos.

Control de Productos:
Se puede agregar, actualizar (por ejemplo, el stock y el nombre) y eliminar productos dentro de cada sucursal.

API RESTful:
Endpoints diseñados para realizar operaciones CRUD sobre franquicias, sucursales y productos.

Dockerización:
El proyecto está preparado para su empaquetado en un contenedor Docker, facilitando su despliegue y escalabilidad.

Persistencia de Datos:
Se utiliza MySQL para el almacenamiento de datos en producción y H2 para desarrollo o pruebas rápidas.


Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

Java 21

Maven (o la herramienta de construcción que utilices)

MySQL Server 9.2.0

Docker (opcional, para empaquetar y desplegar la aplicación en un contenedor)

Instalación y Configuración
Clonar el Repositorio:

bash
Copiar
git clone <https://github.com/Jesus-Suarez-Duarte/Franquicias.git>
cd franqui
Configurar la Base de Datos:
se debe crear en la base de datos mysql instalada un base de nombre dev_backend y ya la aplicacion carga las tablas y una data por defecto
esta data se encuentra en src/main/resources
properties
Copiar
spring.datasource.url=jdbc:mysql://localhost:3306/dev_backend?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin
Construir el Proyecto:

Ejecuta el siguiente comando para compilar y empaquetar la aplicación en un archivo JAR:

bash
Copiar
mvn clean package
Ejecución de la Aplicación
Una vez compilado, puedes ejecutar el JAR generado:

bash
Copiar
java -jar target/franqui-0.0.1-SNAPSHOT.jar.jar
La aplicación se levantará en el puerto configurado (por defecto, el 8080) y estará lista para recibir peticiones.

Endpoints Disponibles
La API cuenta con tres controladores, cada uno con operaciones CRUD. A continuación se muestra un ejemplo de endpoints implementados:

Franquicias
GET /api/franquicias: Listar todas las franquicias.

POST /api/franquicias: Crear una nueva franquicia.

PUT /api/franquicias/{id}: Actualizar el nombre u otros datos de una franquicia.

DELETE /api/franquicias/{id}: Eliminar una franquicia.

Sucursales
GET /api/sucursales: Listar todas las sucursales.

POST /api/sucursales: Agregar una nueva sucursal a una franquicia.

PUT /api/sucursales/{id}: Actualizar la información de una sucursal.

DELETE /api/sucursales/{id}: Eliminar una sucursal.

Productos
GET /api/productos: Listar todos los productos.

POST /api/productos: Agregar un nuevo producto a una sucursal.

PUT /api/productos/{id}: Actualizar el stock o el nombre de un producto.

DELETE /api/productos/{id}: Eliminar un producto.

Nota: Algunos endpoints adicionales pueden incluir lógica para buscar el producto con mayor stock en una sucursal para una franquicia en particular.

Construcción y Ejecución con Docker
Para construir y ejecutar la aplicación en un contenedor Docker, sigue estos pasos:

Construir la Imagen Docker:

el archivo dokfile se encuentra en la raiz del proyecto

bash
Copiar
docker build -t franquicias-api .
Ejecutar el Contenedor:

Levanta el contenedor mapeando el puerto correspondiente : 8080:

bash
Copiar
docker run -p 8080:8080 franquicias-api
De esta forma, la aplicación se ejecutará en un entorno aislado, facilitando el despliegue y la escalabilidad en entornos productivos.

Tecnologías Utilizadas
Lenguaje: Java 21

Framework: Spring Boot 3.4.4

Base de Datos: MySQL 9.2.0 y H2 Database

ORM: JPA

Utilidades: Lombok, Spring Devtools

Servidor Web: Spring Web

Contenerización: Docker

Contribuciones
Las contribuciones son bienvenidas. Si deseas colaborar, por favor sigue estos pasos:

Fork del repositorio

Crear una rama de característica: git checkout -b feature/nueva-funcionalidad

Realizar tus cambios y hacer commit: git commit -m 'Añadir nueva funcionalidad'

Enviar tus cambios al repositorio remoto: git push origin feature/nueva-funcionalidad

Abrir un Pull Request

Licencia
Este proyecto se distribuye bajo la Licencia MIT.
Si no cuentas con un archivo de licencia, puedes modificar o eliminar esta sección.