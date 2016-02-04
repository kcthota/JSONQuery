/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Expressions which return double values only. Example: floor, ciel
 * @author Krishna Chaitanya Thota
 * Feb 03, 2015 9:56:16 PM
 */
public class DoubleValueExpression extends ValueExpression {

	public DoubleValueExpression(String property) {
		super(property);
	}

	public DoubleValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}
	
	/**
	 * Returns the final String value of this expression
	 * @param node
	 * @return
	 */
	public double value(JsonNode node) {
		return evaluate(node).doubleValue();
	}

}
