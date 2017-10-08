package com.app.msg.process.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.app.msg.process.domain.Adjustment;
import com.app.msg.process.domain.Product;
import com.app.msg.process.domain.Sale;

/**
 * Factory class for Sale objects.
 * 
 * @author ricardopalvesjr
 *
 */
@Component
public class SaleFactory implements FactoryBean<Sale> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaleFactory.class);

	private String type;
	private Integer units;
	private String operation;
	private Integer value;

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Sale getObject() throws Exception {
		Sale sales = new Sale();
		sales.setUnits(getUnits());

		Product product = new Product();
		product.setType(getType());
		sales.setProduct(product);

		Adjustment adjustment = new Adjustment();
		adjustment.setOperation(getOperation());
		adjustment.setValue(getValue());
		sales.setAdjustment(adjustment);

		LOGGER.debug(messageSource.getMessage("dbug.factory.create", new Object[] { sales }, null));

		return sales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return Sale.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the units
	 */
	public Integer getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            the units to set
	 */
	public void setUnits(Integer units) {
		this.units = units;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

}
