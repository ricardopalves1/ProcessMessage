package com.app.msg.process.service;

import org.springframework.jms.JmsException;

/**
 * Service interface related to recording queue messages.
 * 
 * @author ricardopalvesjr
 *
 */
public interface IMessageService {

	/**
	 * Records a received webservice Sale data into the backlog queue.
	 * 
	 * @param type
	 *            String Sale product's type.
	 * @param units
	 *            Integer Sale units value.
	 * 
	 * @throws JmsException
	 * @throws Exception
	 */
	public void recordSale(String type, Integer units) throws JmsException, Exception;

	/**
	 * Records a received webservice Sale and Adjustment data into the backlog
	 * queue.
	 * 
	 * @param type
	 *            String Sale product's type.
	 * @param units
	 *            Integer Sale units value.
	 * @param operation
	 *            String Adjustment operation.
	 * @param value
	 *            Integer Adjustment value.
	 * 
	 * @throws JmsException
	 * @throws Exception
	 */
	public void recordSaleAdjustment(String type, Integer units, String operation, Integer value)
			throws JmsException, Exception;

}
