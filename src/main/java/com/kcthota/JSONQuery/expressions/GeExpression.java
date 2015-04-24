package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class GeExpression extends AbstractComparisonExpression {

	public GeExpression(String property, JsonNode value) {
		super(property, value);
	}
}
