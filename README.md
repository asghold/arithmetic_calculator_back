# Arithmetic Calculator

### Tools

* Java 17
* H2 Database or MySQL Database

### Configuration

1. Clone the application

    
        https://github.com/asghold/arithmetic_calculator_back.git
    

2. Create database:
   
   The options with which you can test the application are explained below, you have the option of using MySQL or H2 Database:

    * #### MySQL

        ##
            CREATE DATABASE  IF NOT EXISTS `arithmetic_calculator`
            USE `arithmetic_calculator`;

            CREATE TABLE `user` (
              `id` varchar(40) NOT NULL,
              `username` varchar(60) NOT NULL,
              `password` varchar(100) NOT NULL,
              `status` bit(1) DEFAULT NULL,
              PRIMARY KEY (`id`)
            );

            CREATE TABLE `role` (
              `id` int NOT NULL AUTO_INCREMENT,
              `name` varchar(45) NOT NULL,
              PRIMARY KEY (`id`)
            );

            CREATE TABLE `user_role` (
              `role_id` int NOT NULL,
              `user_id` varchar(40) NOT NULL,
              KEY `user_role_fk_idx` (`user_id`),
              KEY `user_role_fk2_idx` (`role_id`),
              CONSTRAINT `user_role_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
              CONSTRAINT `user_role_fk2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
            );

            CREATE TABLE `operation` (
              `id` int NOT NULL,
              `type` varchar(20) NOT NULL,
              `cost` decimal(10,2) NOT NULL,
              `valid` bit(1) NOT NULL,
              PRIMARY KEY (`id`)
            );

            CREATE TABLE `record` (
              `id` varchar(40) NOT NULL,
              `operation_id` int NOT NULL,
              `user_id` varchar(40) NOT NULL,
              `amount` decimal(10,2) DEFAULT NULL,
              `user_balance` decimal(10,2) DEFAULT NULL,
              `operation_response` varchar(250) DEFAULT NULL,
              `date` datetime DEFAULT NULL,
              PRIMARY KEY (`id`),
              KEY `record_user_fk_idx` (`user_id`),
              KEY `record_operation_fk_idx` (`operation_id`),
              CONSTRAINT `record_operation_fk` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`),
              CONSTRAINT `record_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
            );


            INSERT INTO `operation` VALUES (1,'Addition',0.25,1),(2,'Subtraction',0.25,1),(3,'Multiplication',0.50,1),(4,'Division',0.50,1),(5,'Square Root',0.75,1),(6,'Random String',1.25,1);
            INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

        Change MySQL username and password as per your MySQL installation

      * open ``` src/main/resources/application.properties``` file and change ```spring.datasource.username``` and ```spring.datasource.password``` properties as per your credentials:  

            #### MySQL Database Configuration
            spring.datasource.url=jdbc:mysql://localhost:3306/arithmetic_calculator
            spring.datasource.username={username}
            spring.datasource.password={password}

    * #### H2 Database
      * Just uncomment the H2 database configuration block and comment out the MySQL configuration in the file ``` src/main/resources/application.properties```:

            ### H2 Database configuration
            spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE
            spring.datasource.driverClassName=org.h2.Driver
            spring.datasource.username=sa
            spring.datasource.password=
            spring.jpa.show-sql=true
            spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
            spring.h2.console.enabled=true

            #### MySQL Database Configuration
            spring.datasource.url=jdbc:mysql://localhost:3306/arithmetic_calculator
            spring.datasource.username=
            spring.datasource.password=

3. Put your apikey in the ```app.external.generator.strings.apikey``` property of the file ``` src/main/resources/application.properties```:

        app.external.generator.strings.url=https://api.random.org/json-rpc/4/invoke
        app.external.generator.strings.jsonrpc=2.0
        app.external.generator.strings.method=generateStrings
        app.external.generator.strings.id=42
        app.external.generator.strings.char=abcdefghijklmnopqrstuvwxyz
        app.external.generator.strings.apikey={yourApiKey}

4. Run the app

    You can run the spring boot app by typing the following command:

        mvn spring-boot:run
    
    The server will start on port 8080.

    Optinal: You can also package the application in the form of a jar file and then run it like so:
    
        mvn package
        java -jar target/polls-0.0.1-SNAPSHOT.jar



