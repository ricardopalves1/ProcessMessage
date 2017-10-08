package com.app.msg.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.config.JmsListenerEndpointRegistry;

import com.app.msg.process.pool.BacklogWorker;

/**
 * ProcessMessage main application class.
 * 
 * @author ricardopalvesjr
 *
 */
@ComponentScan("com.app.msg.process")
@SpringBootApplication
public class MainApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	/**
	 * Main method.
	 * 
	 * @param args
	 *            String[] Arrays of arguments.
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder().sources(MainApplication.class)
				.run(args);

		MessageSource messageSource = context.getBean(MessageSource.class);
		LOGGER.info(messageSource.getMessage("info.process.start", null, null));

		String maxMsgNumber = System.getProperty("max.msg.number", "50");
		LOGGER.info(messageSource.getMessage("info.max.msg.number", new Object[] { maxMsgNumber }, null));

		JmsListenerEndpointRegistry endpointRegistry = context.getBean(JmsListenerEndpointRegistry.class);
		endpointRegistry.start();

		BacklogWorker backlogWorker = (BacklogWorker) context.getBean(BacklogWorker.class);
		backlogWorker.setMaxMsgNumber(Integer.parseInt(maxMsgNumber));
		backlogWorker.start();
	}
}
