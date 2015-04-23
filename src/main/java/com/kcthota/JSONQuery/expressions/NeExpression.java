package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class NeExpression extends AbstractComparisonExpression {

	public NeExpression(String property, JsonNode value) {
		super(property, value);
	}
}
