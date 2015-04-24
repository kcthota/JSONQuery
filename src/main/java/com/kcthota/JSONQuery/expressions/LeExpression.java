package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class LeExpression extends AbstractComparisonExpression {

	public LeExpression(String property, JsonNode value) {
		super(property, value);
	}
}
