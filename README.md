Setup

Needed steps to test:

    1. Install Java 17+ 
    2. Configure JAVA_HOME system variable
    3. Install PostgreSQL database
    4. Import database from "db_file.sql" database dump
    5. In application.properties replace placeholder password with your own for postgres
    6. Open project's folder in terminal
    7. Do command: .\mvnw.cmd clean install
    8. Do command: .\mvnw.cmd spring-boot:run

If everything works properly, now you should have a working app :)

You can access it on localhost:8080

Reminder - app is still under development especially front-end part which is heavily lacking,
especially in styling and design, I will work on fixing it as soon as possible.