Clone project to local machine.

Open project in preferred IDE and adjust settings to use JDK 17 and Maven 3.9.8

Run "maven clean install" and wait for run to finish

Run TasksApplication.java and wait for the message "Started TasksApplication in XX seconds" in terminal.
Leave running while using the application.

You can now access the api and H2 Console for use.

API: http://localhost:8080/swagger-ui/index.html

H2: http://localhost:8080/h2-console

Use below properties when logging in

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:taskdb

User Name: sa

Password: password
