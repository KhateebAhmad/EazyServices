package edu.nci.eazyserve;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApp {
	private static final Logger logger = LoggerFactory.getLogger(StartApp.class);
	public static final String AJP_CONNECTOR_VERSION = "AJP/1.3";
	public static final String SCHEMA_HTTP = "http";
	public static final String SCHEMA_HTTPS = "https";
	
	@Value("${tomcat.ajp.port}")
	private int ajpPort;

	@Value("${tomcat.ajp.remoteauthentication}")
	private String remoteAuthentication;

	@Value("${tomcat.ajp.enabled}")
	private boolean tomcatAjpEnabled;
	
	@Value("${tomcat.ajp.secure}")
	private boolean isSecure;
	
	@Value("${tomcat.jvmroute}")
	private String jvmRoute;

	public static void main(String[] args) {
		logger.debug("Staring spring boot configuration.");
		SpringApplication.run(StartApp.class, args);
	}
	
	//https://blog.swdev.ed.ac.uk/2015/06/24/adding-embedded-tomcat-ajp-support-to-a-spring-boot-application/
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		if (tomcatAjpEnabled) {
			Connector ajpConnector = new Connector(AJP_CONNECTOR_VERSION);
			ajpConnector.setProtocol(AJP_CONNECTOR_VERSION);
			ajpConnector.setPort(ajpPort);
			ajpConnector.setSecure(isSecure);
			ajpConnector.setAllowTrace(false);
			
			if(isSecure){
				ajpConnector.setScheme(SCHEMA_HTTPS);
			}else{
				ajpConnector.setScheme(SCHEMA_HTTP);
			}
			
			tomcat.addAdditionalTomcatConnectors(ajpConnector);
			
			// set the jvmRoute tomcat Engine property  
			System.setProperty("jvmRoute", jvmRoute);
		}

		return tomcat;
	}
}