package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class EqExpression extends AbstractComparisonExpression {

	public EqExpression(String property, JsonNode value) {
		super(property, value);
	}
}
