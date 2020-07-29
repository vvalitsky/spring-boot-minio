# Example of using Minio + Spring Boot

###### Official documentation about Spring -> https://spring.io
###### Official documentation about Minio -> https://min.io
###### Official documentation about Docker -> https://www.docker.com

Before you start, you should run the following commands
This will load the minio image and will start the container

```bash

docker pull minio/minio

docker run -p 9000:9000 \
  -e "MINIO_ACCESS_KEY=minio" \
  -e "MINIO_SECRET_KEY=minio123" \
  minio/minio server /data

```
##### The running process
![MINIO_SH_START](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/MINIO_SH_START.png)

##### The minio has a user interface, and it can be accessed by typing MINIO_ACCESS_KEY=minio MINIO_SECRET_KEY=minio123
![MINIO](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/MINIO.png)

##### For starting Spring Boot
```bash

./gradlew clean build bootRun

```

##### The application has a swagger ui, can be accessed through browser window (http://localhost:5150/swagger-ui.html)
![SWAGGER](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER.png)

##### Post method
![SWAGGER_POST_1](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER_POST_1.png)

##### Post method execute
![SWAGGER_POST_2](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER_POST_2.png)

##### Post method execute response
![SWAGGER_POST_RESPONSE](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER_POST_RESPONSE.png)

##### Uploaded file in Minio
![MINIO_UPLOADED_FILE](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/MINIO_UPLOADED_FILE.png)

##### Get file method
![SWAGGER_GET](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER_GET.png)

##### Delete file method
![SWAGGER_DELETE](https://raw.githubusercontent.com/vvalitsky/spring-boot-minio/master/screenshots/SWAGGER_DELETE.png)

