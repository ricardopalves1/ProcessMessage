package com.app.msg.process.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

/**
 * Main application configuration class.
 * 
 * @author ricardopalvesjr
 *
 */
@EnableJms
@Configuration
public class MainConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Bean
	public BrokerService broker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.addConnector(brokerUrl);
		broker.setUseJmx(true);
		broker.setPersistent(false);

		return broker;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		connectionFactory.setTrustAllPackages(true);

		return connectionFactory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new SimpleMessageConverter();
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		int cores = Runtime.getRuntime().availableProcessors();
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency(cores + "-" + cores);
		factory.setMessageConverter(messageConverter());
		factory.setAutoStartup(false);

		return factory;
	}

	@Bean
	public ActiveMQQueue defaultDestination() {
		return new ActiveMQQueue("backlogQueue");
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestination(defaultDestination());

		return jmsTemplate;
	}

}
