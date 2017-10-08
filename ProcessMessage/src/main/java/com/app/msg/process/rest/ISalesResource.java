package com.app.msg.process.rest;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.jms.JmsException;

import com.app.msg.process.domain.Adjustment;

/**
 * WebService interface related to receiving Sales and Adjustments.
 * 
 * @author ricardopalvesjr
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/sales")
public interface ISalesResource {

	/**
	 * Receives one incoming Sale data.
	 * 
	 * @param type
	 *            String Sale product's type.
	 * @param units
	 *            Integer Sale units value.
	 * 
	 * @return WebService response generated regarding the data entity received.
	 * 
	 * @throws JmsException
	 * @throws Exception
	 */
	@GET
	@POST
	@Path("/product/{type}/units/{units}")
	public Response record(@Valid @PathParam("type") String type, @Valid @PathParam("units") Integer units)
			throws JmsException, Exception;

	/**
	 * Receives multiple occurrences of Sale data.
	 * 
	 * @param type
	 *            String Sale product's type.
	 * @param units
	 *            Integer Sale units value.
	 * @param occur
	 *            Integer The number of Sale occurrences.
	 * 
	 * @return WebService response generated regarding the data entity received.
	 * 
	 * @throws JmsException
	 * @throws Exception
	 */
	@GET
	@POST
	@Path("/product/{type}/units/{units}/occurrences/{occurrences}")
	public Response recordMultiple(@Valid @PathParam("type") String type, @Valid @PathParam("units") Integer units,
			@Valid @PathParam("occurrences") Integer occur) throws JmsException, Exception;

	/**
	 * Receives one incoming Sale and Adjustment data.
	 * 
	 * @param type
	 *            String Sale product's type.
	 * @param units
	 *            Integer Sale units value.
	 * @param adjustment
	 *            Adjustment The Adjustment object data.
	 * 
	 * @return WebService response generated regarding the data entity received.
	 * 
	 * @throws JmsException
	 * @throws Exception
	 */
	@GET
	@POST
	@Path("/product/{type}/units/{units}/operation/{operation}/value/{value}")
	public Response recordSalesAdjustment(@Valid @PathParam("type") String type,
			@Valid @PathParam("units") Integer units, @Valid @BeanParam Adjustment adjustment)
			throws JmsException, Exception;

}
