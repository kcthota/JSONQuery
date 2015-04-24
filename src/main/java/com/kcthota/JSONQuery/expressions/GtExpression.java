package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class GtExpression extends AbstractComparisonExpression {

	public GtExpression(String property, JsonNode value) {
		super(property, value);
	}
}
