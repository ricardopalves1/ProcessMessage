/**
 * 
 */
package com.app.msg.process.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Product domain object class.
 * 
 * @author ricardopalvesjr
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {

	private static final long serialVersionUID = 8224394959812581698L;
	public static final int HASH_VALUE = 7;
	public static final int HASH_MULTIPLIER = 31;

	@NotNull
	private String type;

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

		Product test = (Product) obj;
		if (this.getType().equals(test.getType())) {
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
		Long a = new Long(this.getType().hashCode());

		return (HASH_MULTIPLIER * HASH_VALUE) + a.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "com.app.msg.process.domain.Product[Type=" + type + "]";
	}

}
