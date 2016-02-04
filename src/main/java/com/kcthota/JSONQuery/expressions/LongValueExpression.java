/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Expressions which return long values only. Example: round
 * @author Krishna Chaitanya Thota
 * Feb 03, 2015 9:56:16 PM
 */
public class LongValueExpression extends ValueExpression {

	public LongValueExpression(String property) {
		super(property);
	}

	public LongValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}
	
	/**
	 * Returns the final String value of this expression
	 * @param node
	 * @return
	 */
	public long value(JsonNode node) {
		return evaluate(node).longValue();
	}

}
