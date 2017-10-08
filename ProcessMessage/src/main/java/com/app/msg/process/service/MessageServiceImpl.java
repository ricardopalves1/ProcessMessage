package com.app.msg.process.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jms.JmsException;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.msg.process.domain.Sale;
import com.app.msg.process.utils.SaleFactory;

/**
 * Message service class.
 * 
 * @author ricardopalvesjr
 *
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class MessageServiceImpl implements IMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private SaleFactory salesFactory;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JmsListenerEndpointRegistry endpointRegistry;

	/*
	 * (non-Javadoc)
	 * @see com.app.msg.process.service.IMessageService#recordSale(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void recordSale(String type, Integer units) throws JmsException, Exception {
		salesFactory.setType(type);
		salesFactory.setUnits(units);
		salesFactory.setOperation("");
		salesFactory.setValue(0);
		Sale sale = salesFactory.getObject();

		if (!endpointRegistry.isRunning()) {
			LOGGER.info(messageSource.getMessage("info.process.stop", null, null));

		} else {
			LOGGER.debug(messageSource.getMessage("dbug.send.queue.message", new Object[] { sale }, null));
			jmsTemplate.convertAndSend(sale);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.app.msg.process.service.IMessageService#recordSaleAdjustment(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void recordSaleAdjustment(String type, Integer units, String operation, Integer value)
			throws JmsException, Exception {
		salesFactory.setType(type);
		salesFactory.setUnits(units);
		salesFactory.setOperation(operation);
		salesFactory.setValue(value);
		Sale sale = salesFactory.getObject();

		if (!endpointRegistry.isRunning()) {
			LOGGER.info(messageSource.getMessage("info.process.stop", null, null));

		} else {
			LOGGER.debug(messageSource.getMessage("dbug.send.queue.message", new Object[] { sale }, null));
			jmsTemplate.convertAndSend(sale);
		}
	}

}
