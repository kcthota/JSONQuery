package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Evaluates if all Comparison expressions are true
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:06:18 AM
 */
public class AndExpression extends ContainerComparisonExpression {

	public AndExpression(ComparisonExpression... expressions) {
		super(expressions);
	}

	
	@Override
	public boolean evaluate(JsonNode node) {
		for (ComparisonExpression curExpr : getExpressions()) {
			boolean currentResult = curExpr.evaluate(node);
			if (!currentResult) {
				return false;
			}
		}
		return true;
	}

}
