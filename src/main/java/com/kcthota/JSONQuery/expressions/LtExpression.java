package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class LtExpression extends AbstractComparisonExpression {

	public LtExpression(String property, JsonNode value) {
		super(property, value);
	}
}
