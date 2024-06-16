# Proyecto para SmartJob - Creacion de Usuario - creat-user-smartjob

Este proyecto tiene como finalidad registrar usuarios con sus características principales y de contacto, bajo ciertos parámetros de cumplimiento como la entrada y salida en formato JSON, 
Mediante un servicio REST y bajo el marco de trabajo de Spring con BD relacional.

## Tabla de Contenidos

- [Instalación](#instalación)
- [Uso](#uso)
- [Documentación API](#documentacion)
- [Características](#características)

## Instalación

Sigue estos pasos para instalar y ejecutar el proyecto en tu máquina local.

### Prerrequisitos

- Java JDK 17 o superior instalado (puedes descargarlo desde [adoptopenjdk.net](https://adoptopenjdk.net/))
- Maven instalado (puedes descargarlo desde [maven.apache.org](https://maven.apache.org/download.cgi))


1. **Clonar el Repositorio**

   ```bash
   git clone https://github.com/FelipeGarridoTapia/create-user-smartjob.git
   cd create-user-smartjob

1. **Compilar el Proyecto**

    Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias:

   ```bash
    mvn clean install

## Uso 

 Puedes ejecutar el proyecto usando Maven:

    mvn spring-boot:run
	 

## Documentación API
Para acceder a la documentación de la API utilizando Swagger:

    http://localhost:8080/swagger-ui.html

![image](https://github.com/FelipeGarridoTapia/create-user-smartjob/assets/30244789/3c42fe35-fcf7-44e1-a090-1288bc1cbca9)


O puede ser tambien mediante Postman:

![image](https://github.com/paytonx10/create-user-nissum/assets/75043426/cd3910ea-69a3-47f9-a641-5baf71880ac6)

## Características
El servicio dispone de dos endpoints que sirven para registrar un usuario y para obtener todos los usuarios creados:

- #### Registro de Usuarios

        v1/register-user

El formato json de entrada debe ser de la siguiente manera (ejemplo).

	{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.rg",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]}

La respuesta exitosa debe contener la misma informacion ademas de informacion adicional del resgitro.

	{
    "message": "successfully saved",
    "description": {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.rg",
        "password": "hunter2",
        "phones": [
            {
                "number": "1234567",
                "cityCode": "1",
                "countryCode": "57"
            }
        ],
        "id": "610cbe59-5d76-475d-adab-0b28dc76dab5",
        "created": "2024-06-12",
        "modified": "2024-06-12",
        "lastLogin": "2024-06-12",
        "token": "dc4f5267-da8d-4639-a5e0-1406f613291a",
        "isActive": true
    }}

Se puede dar que el sistema arroje algunas validaciones como por ejemplo:

####   Email Inválido

		{"message": "400 BAD_REQUEST","description": "Invalid email format"}

![image](https://github.com/paytonx10/create-user-nissum/assets/75043426/16003004-fdf8-4272-b52a-21abc049e968)

#### Registro (email) ya existente

		{"message": "400 BAD_REQUEST","description": "Email already exists"}

![image](https://github.com/paytonx10/create-user-nissum/assets/75043426/d2f8a202-e361-4641-bdfe-6ce6048bbebe)

- #### Obtención de usuarios

	  v1/users

Mediante este endpoint se obtienen todos los registros creados en la bd temporal

![image](https://github.com/FelipeGarridoTapia/create-user-smartjob/assets/30244789/e3abd78e-1a20-46c6-af78-a9770f317333)


- Otra característica es que para este proyecto no se necesita correr un script de BD ya que posee la librería de H2 para el manejo de bd temporal, es decir solo estará creada cuando el proyecto este ejecutándose localmente, una vez reiniciado todos los datos se eliminarán.

Levantamiento de BD - init service

	Hibernate: create table phone (id uuid not null, city_code varchar(255), country_code varchar(255), number varchar(255), user_id uuid, primary key (id))
	Hibernate: create table users (id uuid not null, created timestamp(6), email varchar(255), is_active boolean, last_login timestamp(6), 
 	modified timestamp(6), name varchar(255), password varchar(255), token uuid, primary key (id))
	Hibernate: alter table if exists phone add constraint FKik7a2etdorybvoolvchfcvgkx foreign key (user_id) references users


Ejemplo de bd con registro creado:

![image](https://github.com/paytonx10/create-user-nissum/assets/75043426/a236bb3d-c83a-4721-b277-4e507a281166)

