# TapServe - TapServe web application 

# Micro-service configuration for clustering
A property file is used to configure runtime application properties of the micro-services. In future, this properties file should be discarded and a configuration application such as Consule should be used to configure application properties. 
For clustering, each instance of micro-service runs on specific HTTP and AJP port. These ports are configured in Applications.properties file. Therefore for each instance of micro-service need to be built using different properties file. Here are steps to build war file for each instance using different properties file. 
1.	Install Apache Maven from https://maven.apache.org/install.html
2.	Replace a copy of application.properties from src\main\resources\DbScripts\application1.properties to src\main\resources\ application.properties
3.	Build project using following command >mvn clean install 
4.	This will build a jar and a war file. 
5.	Run micro-service using inbuilt web server. Optionally, an application server instances (such as wildfly) can be used to deploy micro-service war file. 

# Apache web server installation and configuration
To configure Apache web server perform following steps
1.	Install Apache web server from http://httpd.apache.org/docs/2.4/install.html
2.	Copy all contents under src\main\webapp\css  folder from of TapServeClient project and paste it to %APACHE_HOME%\htdocs directory
3.	Configure apache by following https://tomcat.apache.org/tomcat-3.3-doc/mod_jk-howto.html 
4.	Make sure that the host and port (HTTP and AJP) of micro-service is match while configuring workers in Apache server. 

# Database installation and configuration
To configure MySQL database perform following steps
1.	Download and Install MySQL Community edition 5 from https://dev.mysql.com/downloads/mysql/5.6.html#downloads
2.	Create database schema and tables by executing src\main\resources\DbScripts\CreateTableScript.sql script using MySql console. 
3.	Insert default rows in tables by executing src\main\resources\DbScripts\MasterData.sql script using MySql console.


