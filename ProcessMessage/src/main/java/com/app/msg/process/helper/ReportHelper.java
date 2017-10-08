package com.app.msg.process.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.msg.process.domain.Adjustment;
import com.app.msg.process.domain.Product;
import com.app.msg.process.domain.Sale;

/**
 * Report helper class.
 * 
 * @author ricardopalvesjr
 *
 */
public class ReportHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportHelper.class);

	/**
	 * Private constructor.
	 */
	private ReportHelper() {
	}

	/**
	 * Retrieves a map of Products x Total units.
	 * 
	 * @param sales
	 *            List<Sale> List of sales received.
	 * @return Map<Product, Integer> The map object populated.
	 */
	public static Map<Product, Integer> getMapProdTotalUnits(List<Sale> sales) {
		Map<Product, Integer> productUnits = sales.stream().collect(Collectors.toMap(Sale::getProduct, Sale::getUnits,
				(oldValue, newValue) -> oldValue + newValue, LinkedHashMap::new));
		LOGGER.debug(productUnits.toString());

		return productUnits;
	}

	/**
	 * Retrieves a map of Adjustments x Products.
	 * 
	 * @param sales
	 *            List<Sale> List of sales received.
	 * 
	 * @return Map<Adjustment, Product> The map object populated.
	 */
	public static Map<Adjustment, Product> getMapAdjProduct(List<Sale> sales) {
		Map<Adjustment, Product> adjProducts = sales.stream().collect(Collectors.toMap(Sale::getAdjustment,
				Sale::getProduct, (oldValue, newValue) -> newValue, LinkedHashMap::new));
		LOGGER.debug(adjProducts.toString());

		return adjProducts;
	}

	/**
	 * Retrieves a map of Products x Counting Occurrences
	 * 
	 * @param sales
	 *            List<Sale> List of sales received.
	 * @return Map<Product, Long> The map object populated.
	 */
	public static Map<Product, Long> getMapProdCounts(List<Sale> sales) {
		Map<Product, Long> productCounts = sales.stream()
				.collect(Collectors.groupingBy(Sale::getProduct, Collectors.counting()));
		LOGGER.debug(productCounts.toString());

		return productCounts;
	}

}
