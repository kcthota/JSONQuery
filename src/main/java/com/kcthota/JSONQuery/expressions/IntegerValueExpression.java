/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Expressions which return Integer values only. Example: length, indexof
 * @author Krishna Chaitanya Thota
 * Apr 29, 2015 10:46:16 PM
 */
public class IntegerValueExpression extends ValueExpression {

	public IntegerValueExpression(String property) {
		super(property);
	}

	public IntegerValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}
	
	/**
	 * Returns the final String value of this expression
	 * @param node
	 * @return
	 */
	public int value(JsonNode node) {
		return evaluate(node).intValue();
	}

}
