/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Krishna Chaitanya Thota
 * Apr 30, 2015 7:40:48 PM
 */
public class StringValueExpression extends ValueExpression {

	/**
	 * @param property
	 * @param innerExpression
	 */
	public StringValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}

	public StringValueExpression(String property) {
		super(property);
	}

	
	@Override
	public JsonNode evaluate(JsonNode node) {
		return super.evaluate(node);
	}

	/**
	 * Returns the final String value of this expression
	 * @param node
	 * @return
	 */
	public String value(JsonNode node) {
		return evaluate(node).textValue();
	}
	
}
