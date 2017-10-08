package com.app.msg.process.rest;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import com.app.msg.process.domain.Adjustment;
import com.app.msg.process.service.IMessageService;

/**
 * WebService class regarding the Sales and Adjustments.
 * 
 * @author ricardopalvesjr
 *
 */
@Component
public class SalesResource implements ISalesResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesResource.class);

	@Autowired
	private IMessageService messageService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.rest.ISalesResource#record(java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public Response record(String type, Integer units) throws JmsException, Exception {

		String entity = String.format("Sale{product=%s, units=%s}", type, units);
		LOGGER.debug(entity);

		messageService.recordSale(type, units);

		return Response.ok().entity(entity).build();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.rest.ISalesResource#recordMultiple(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Response recordMultiple(String type, Integer units, Integer occur) throws JmsException, Exception {

		String entity = String.format("Sale{product=%s, units=%s, occurrences=%s}", type, units, occur);
		LOGGER.debug(entity);

		for (int i = 0; i < occur; i++) {
			messageService.recordSale(type, units);
		}

		return Response.ok().entity(entity).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.msg.process.rest.ISalesResource#recordSalesAdjustment(java.lang.
	 * String, java.lang.Integer, com.app.msg.process.domain.Adjustment)
	 */
	@Override
	public Response recordSalesAdjustment(String type, Integer units, Adjustment adjustment)
			throws JmsException, Exception {

		String entity = String.format("Sale{product=%s, units=%s}, Adjustment{operation=%s, value=%s}", type, units,
				adjustment.getOperation(), adjustment.getValue());
		LOGGER.debug(entity);

		messageService.recordSaleAdjustment(type, units, adjustment.getOperation(), adjustment.getValue());

		return Response.ok().entity(entity).build();
	}

}
