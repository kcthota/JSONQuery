package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Negation of the result of passed in comparison expression
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:02:46 AM
 */
public class NotExpression implements ComparisonExpression {
	
	ComparisonExpression expression;
	
	public NotExpression(ComparisonExpression expr) {
		this.expression = expr;
	}


	@Override
	public boolean evaluate(JsonNode node) {
		return !expression.evaluate(node);
	}

}
