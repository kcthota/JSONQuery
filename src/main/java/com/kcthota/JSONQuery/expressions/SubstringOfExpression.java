package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Compares if the passed in value is substring of value of the property
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:01:15 AM
 */
public class SubstringOfExpression extends SimpleComparisonExpression {

	public SubstringOfExpression(ValueExpression expression, JsonNode value) {
		super(expression, value);
	}

	@Override
	public boolean evaluate(JsonNode node) {
		JsonNode propertyValue = expression().evaluate(node);
		if(!propertyValue.isTextual() || !value().isTextual()) {
			return false;
		}
		return propertyValue.textValue().contains(value().textValue());
	}
}
