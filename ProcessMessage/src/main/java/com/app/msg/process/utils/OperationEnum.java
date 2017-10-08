package com.app.msg.process.utils;

/**
 * Adjustment operation enum.
 * 
 * @author ricardopalvesjr
 *
 */
public enum OperationEnum {
	ADD("add"), SUBTRACT("subtract"), MULTIPLY("multiply");

	private String operation;

	/**
	 * Constructor method.
	 * 
	 * @param operation
	 *            String Adjustment's operation.
	 */
	OperationEnum(String operation) {
		this.operation = operation;
	}

	/**
	 * Calculates the result value of an Adjustment operation.
	 * 
	 * @param value
	 *            int Received value.
	 * @param units
	 *            int Units to apply on.
	 * 
	 * @return int Result value after the operation.
	 */
	public int calculate(int value, int units) {
		switch (this) {
		case ADD:
			return value + units;
		case SUBTRACT:
			return value - units;
		case MULTIPLY:
			return value * units;
		default:
			throw new AssertionError(this);
		}
	}

	/**
	 * 
	 * @return The operation.
	 */
	public String operation() {
		return operation;
	}

}
