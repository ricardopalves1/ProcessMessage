package com.app.msg.process.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

import com.app.msg.process.rest.SalesResource;

/**
 * Jersey configuration class.
 * 
 * @author ricardopalvesjr
 *
 */
@Configuration
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	/**
	 * Constructor method.
	 */
	public JerseyConfig() {
		this.register(SalesResource.class);
		this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}

}
