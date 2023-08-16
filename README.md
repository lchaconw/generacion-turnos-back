# Sistema de generación de turnos

## Descripción
Solución de agendamiento de turnos para que los clientes de varios comercios puedan reservar con anticipación un espacio de atención en un servicio específico que presta cada comercio.

## Requisitos

- Java 11 o superior.
- Maven.

## Instalación y ejecución

1. Clona este repositorio:
    ```bash
    git clone https://github.com/lchaconw/generacion-turnos-back
    cd generacion-turnos-back
    ```

2. Configura la base de datos:

   Asegúrate de tener MySQL corriendo y una base de datos creada para la aplicación.

    ```bash
    Actualiza el archivo `application.properties` (o `application.yml`) con los detalles de conexión a tu base de datos:

    ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
   spring.datasource.username=your-username
   spring.datasource.password=your-password
    ```

   No necesitas crear las tablas, la aplicación las creará por ti.

   o puedes crear las tablas manualmente con algunos datos con los scripts encontrados en la carpeta Scripts BD.

   Ejecutamos el script para crear el procedimiento almacenado que genera los turnos que se encuentra tambien en la
   carpeta Scripts BD y se llama procedimientoGenTurnos.sql

3. Usamos Maven para construir el proyecto:
    ```bash
    mvn clean install
    ```

4. Ejecuta el proyecto:
    ```bash
    mvn spring-boot:run
    ```

5. Alternativamente, puedes construir un archivo JAR:
    ```bash
    java -jar target/reservas-0.0.1-SNAPSHOT.jar
    ```

## Tests

Para ejecutar los tests de JUNIT 5, usa el siguiente comando:

```bash
mvn test
```
