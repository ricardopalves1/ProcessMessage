package com.app.msg.process.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.app.msg.process.domain.Sale;
import com.app.msg.process.service.ISalesService;

/**
 * Backlog queue listener class.
 * 
 * @author ricardopalvesjr
 *
 */
@Component
public class BacklogListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(BacklogListener.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	MessageConverter messageConverter;

	@Autowired
	private ISalesService salesService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	@JmsListener(destination = "backlogQueue", containerFactory = "jmsListenerContainerFactory")
	public void onMessage(Message message) {

		try {
			Sale sales = (Sale) messageConverter.fromMessage(message);
			LOGGER.debug(messageSource.getMessage("dbug.message.received", new Object[] { sales }, null));

			salesService.addSale(sales);

		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
