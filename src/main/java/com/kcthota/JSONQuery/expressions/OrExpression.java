package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Evaluates if at least one the comparison expressions is true
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:06:49 AM
 */
public class OrExpression extends ContainerComparisonExpression {

	public OrExpression(ComparisonExpression... expressions) {
		super(expressions);
	}

	@Override
	public boolean evaluate(JsonNode node) {
		for (ComparisonExpression curExpr : getExpressions()) {
			Boolean currentResult = curExpr.evaluate(node);
			if (currentResult) {
				return true;
			}

			// reset currentResult for Or
			currentResult = null;
		}
		return false;
	}

}
