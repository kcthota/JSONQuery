package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Evaluates equal to
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:04:34 AM
 */
public class EqExpression extends SimpleComparisonExpression {

	public EqExpression(ValueExpression expression, JsonNode value) {
		super(expression, value);
	}

	@Override
	public boolean evaluate(JsonNode node) {
		return expression().evaluate(node).equals(value());
	}
	
	
}
