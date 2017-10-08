/**
 * 
 */
package com.app.msg.process.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.msg.process.domain.Adjustment;
import com.app.msg.process.domain.Product;
import com.app.msg.process.domain.Sale;
import com.app.msg.process.service.ISalesService;
import com.app.msg.process.utils.SaleFactory;

/**
 * Test case for ReportHelper class.
 * 
 * @author ricardopalvesjr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportHelperTest {

	@Autowired
	private ISalesService salesService;

	@Autowired
	private SaleFactory salesFactory;

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.app.msg.process.helper.ReportHelper#getMapProdTotalUnits(java.util.List)}.
	 */
	@Test
	public void testGetMapProdTotalUnits() {
		String type = "Apple";
		Integer units = 10;

		try {
			salesFactory.setType(type);
			salesFactory.setUnits(units);
			salesFactory.setOperation("");
			salesFactory.setValue(0);
			Sale sale = salesFactory.getObject();
			salesService.addSale(sale);

			Map<Product, Integer> productUnits = ReportHelper.getMapProdTotalUnits(salesService.getAllsales());

			assertTrue(productUnits.containsKey(sale.getProduct()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.app.msg.process.helper.ReportHelper#getMapAdjProduct(java.util.List)}.
	 */
	@Test
	public void testGetMapAdjProduct() {

		String type = "Banana";
		Integer units = 20;
		String operation = "multiply";
		Integer value = 3;

		try {
			salesFactory.setType(type);
			salesFactory.setUnits(units);
			salesFactory.setOperation(operation);
			salesFactory.setValue(value);
			Sale sale = salesFactory.getObject();
			salesService.addSale(sale);

			Map<Adjustment, Product> adjProducts = ReportHelper.getMapAdjProduct(salesService.getAllsales());

			assertTrue(adjProducts.containsKey(sale.getAdjustment()));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link com.app.msg.process.helper.ReportHelper#getMapProdCounts(java.util.List)}.
	 */
	@Test
	public void testGetMapProdCounts() {

		String type = "Orange";
		Integer units = 50;

		try {
			salesFactory.setType(type);
			salesFactory.setUnits(units);
			salesFactory.setOperation("");
			salesFactory.setValue(0);
			Sale sale = salesFactory.getObject();
			salesService.addSale(sale);

			Map<Product, Long> productCounts = ReportHelper.getMapProdCounts(salesService.getAllsales());

			assertTrue(productCounts.containsKey(sale.getProduct()));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
