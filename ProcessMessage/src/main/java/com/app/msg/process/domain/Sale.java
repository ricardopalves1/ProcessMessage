/**
 * 
 */
package com.app.msg.process.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Sale domain object class.
 * 
 * @author ricardopalvesjr
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sale implements Serializable {

	private static final long serialVersionUID = 6593306137352370325L;
	public static final int HASH_VALUE = 7;
	public static final int HASH_MULTIPLIER = 31;

	@NotNull
	private Product product;

	@NotNull
	private Integer units;

	private Adjustment adjustment;

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * @return the adjustment
	 */
	public Adjustment getAdjustment() {
		return adjustment;
	}

	/**
	 * @param adjustment
	 *            the adjustment to set
	 */
	public void setAdjustment(Adjustment adjustment) {
		this.adjustment = adjustment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Sale test = (Sale) obj;
		if (this.getProduct().equals(test.getProduct()) && this.getUnits() == test.getUnits()
				&& this.getAdjustment().equals(test.getAdjustment())) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		Long a = new Long(this.getUnits().hashCode());
		Long i = new Long(this.getProduct().hashCode());
		Long z = new Long(this.getAdjustment().hashCode());

		int hash = (HASH_MULTIPLIER * HASH_VALUE) + a.hashCode();
		hash = (HASH_MULTIPLIER * hash) + i.hashCode();
		hash = (HASH_MULTIPLIER * hash) + z.hashCode();

		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "com.app.msg.process.domain.Sale[Product=" + product + ":Units=" + units + ":Adjustment=" + adjustment
				+ "]";
	}

}
