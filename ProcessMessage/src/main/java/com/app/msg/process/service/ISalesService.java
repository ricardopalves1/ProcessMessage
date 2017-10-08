
package com.app.msg.process.service;

import java.util.List;

import com.app.msg.process.domain.Sale;

/**
 * Service interface related to Sales reports.
 * 
 * @author ricardopalvesjr
 *
 */
public interface ISalesService {

	/**
	 * Records a Sale object.
	 * 
	 * @param sales
	 *            Sales The object received itself.
	 */
	public void addSale(Sale sales);

	/**
	 * Reports according to the 10th sales message received.
	 * 
	 * @param sales
	 *            List<Sale> The list of sales.
	 */
	public void report10Sales(List<Sale> sales);

	/**
	 * Reports according to the 50th sales message received.
	 * 
	 * @param sales
	 *            List<Sale> The list of sales.
	 */
	public void report50Sales(List<Sale> sales);

	/**
	 * Retrieves the number of current sales received.
	 * 
	 * @return int The result number.
	 */
	public int getNumberAllSales();

	/**
	 * Retrieves a list of all sales received.
	 * 
	 * @return List<Sale> The list of sales.
	 */
	public List<Sale> getAllsales();

}
