package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractComparisonExpression implements ComparisonExpression {
	
	private String property;
	
	private JsonNode value;
	
	public AbstractComparisonExpression(String property, JsonNode value) {
		this.property = property;
		this.value = value;
	}
	
	public String property() {
		return property;
	}

	public JsonNode value() {
		return value;
	}

}
