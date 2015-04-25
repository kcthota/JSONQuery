package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public class SubstringOf extends AbstractComparisonExpression {

	public SubstringOf(String property, JsonNode value) {
		super(property, value);
	}
}
