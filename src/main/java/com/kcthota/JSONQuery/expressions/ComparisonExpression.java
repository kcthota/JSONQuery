package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

public interface ComparisonExpression extends Expression {
	
	public String property();

	public JsonNode value();
}
