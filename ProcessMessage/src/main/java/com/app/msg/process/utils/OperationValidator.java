package com.app.msg.process.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Adjustment operation validator class.
 * 
 * @author ricardopalvesjr
 *
 */
public class OperationValidator implements ConstraintValidator<Operation, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationValidator.class);

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Operation constraintAnnotation) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean valid = false;
		OperationEnum[] enumValues = OperationEnum.values();
		for (int i = 0; i < enumValues.length; i++) {
			if (value.equalsIgnoreCase(enumValues[i].operation())) {
				valid = true;
				break;
			}
		}

		LOGGER.debug(messageSource.getMessage("dbug.operarion.valid", new Object[] { valid }, null));

		return valid;
	}

}
