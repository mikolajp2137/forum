Setup

Needed steps to test:

    1. Install Java 17+ 
    2. Configure JAVA_HOME system variable
    3. Install PostgreSQL database
    4. Create a database, it can be named whatever
    5. In application.properties replace:
        * placeholder password with your own for postgres
        * database name with the name of database you created
        * make sure spring.jpa.hibernate.ddl-auto= is set to update 
    6. Open project's folder in terminal
    7. Do command: .\mvnw.cmd clean install
    8. Do command: .\mvnw.cmd spring-boot:run

If everything works properly, now you should have a working app :)

WARNING:
Before you can start creating users, you need to make few inserts to the database. Follow these steps:

    1. Verify structure of 'users' table, reverse order in insert if necessary
    2. Do following inserts:
        INSERT INTO public.role VALUES (1, 'ROLE_USER');
        INSERT INTO public.role VALUES (2, 'ROLE_ADMIN');

WARNING:
Before you can start creating threads, you need to make few inserts to the database. Follow these steps:

    1. Verify structure of 'categories' table, reverse order in insert if necessary
    2. Do following inserts:
        INSERT INTO categories VALUES (1, 'General');
        INSERT INTO categories VALUES (2, 'Cooking');
        INSERT INTO categories VALUES (3, ':D');
        INSERT INTO categories VALUES (4, 'Linux');
        INSERT INTO categories VALUES (5, 'Hobby');
        INSERT INTO categories VALUES (6, 'Java');
    

You can access it on localhost:8080