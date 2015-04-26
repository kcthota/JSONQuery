package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Compares if value of the property starts with the passed in value
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:01:15 AM
 */
public class StartsWithExpression extends SimpleComparisonExpression {

	public StartsWithExpression(ValueExpression expression, JsonNode value) {
		super(expression, value);
	}

	@Override
	public boolean evaluate(JsonNode node) {
		JsonNode propertyValue = expression().evaluate(node);
		if(!propertyValue.isTextual() || !value().isTextual()) {
			return false;
		}
		return propertyValue.textValue().startsWith(value().textValue());
	}
}
