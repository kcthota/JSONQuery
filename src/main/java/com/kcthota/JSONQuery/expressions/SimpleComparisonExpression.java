/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Krishna Chaitanya Thota
 * Apr 25, 2015 10:41:47 PM
 */
public abstract class SimpleComparisonExpression implements ComparisonExpression{
	
	private ValueExpression expr;
	
	private JsonNode value;
	

	public SimpleComparisonExpression(String property, JsonNode value) {
		this.expr = new ValueExpression(property);
		this.value = value;
	}
	
	/**
	 * Returns the current ValueExperssion
	 * @return
	 */
	public ValueExpression expression() {
		return expr;
	}

	/**
	 * returns the value set to expression for comparison
	 * @return
	 */
	public JsonNode value() {
		return value;
	}
	
	@Override
	public abstract boolean evaluate(JsonNode node);
}
