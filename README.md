# EazyServices - EazyServices web application 

# Micro-service configuration
A property file is used to configure runtime application properties of the micro-services. In future, this properties file should be discarded and a configuration application such as Consule should be used to configure application properties. 
For clustering, each instance of micro-service runs on specific HTTP and AJP port. These ports are configured in Applications.properties file. Therefore for each instance of micro-service need to be built using different properties file. Here are steps to build war file for each instance using different properties file. 
1.	Install Apache Maven from https://maven.apache.org/install.html
2.	Replace a copy of application.properties from src\main\resources\DbScripts\application1.properties to src\main\resources\ application.properties
3.	Build project using following command >mvn clean install 
4.	This will build a jar and a war file. 
5.	Run micro-service using inbuilt web server. Optionally, an application server instances (such as wildfly) can be used to deploy micro-service war file. 

# Database installation and configuration
To configure MySQL database perform following steps
1.	Download and Install MySQL Community edition 5 from https://dev.mysql.com/downloads/mysql/5.6.html#downloads
2.	Create database schema and tables by executing src\main\resources\DbScripts\CreateTableScript.sql script using MySql console. 
3.	Insert default rows in tables by executing src\main\resources\DbScripts\MasterData.sql script using MySql console.


