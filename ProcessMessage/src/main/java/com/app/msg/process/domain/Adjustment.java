package com.app.msg.process.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import com.app.msg.process.utils.Operation;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Adjustment domain object class.
 * 
 * @author ricardopalvesjr
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Adjustment implements Serializable {

	private static final long serialVersionUID = -2084244874000298101L;
	public static final int HASH_VALUE = 7;
	public static final int HASH_MULTIPLIER = 31;

	@Operation
	@PathParam("operation")
	private String operation;

	@NotNull
	@PathParam("value")
	private Integer value;

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

		Adjustment test = (Adjustment) obj;
		if (this.getOperation().equals(test.getOperation()) && this.getValue() == test.getValue()) {
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
		Long a = new Long(this.getValue().hashCode());
		Long i = new Long(this.getOperation().hashCode());

		int hash = (HASH_MULTIPLIER * HASH_VALUE) + a.hashCode();
		hash = (HASH_MULTIPLIER * hash) + i.hashCode();

		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "com.app.msg.process.domain.Adjustment[Operation=" + operation + ":Value=" + value + "]";
	}

}
