/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Rounds the value and returns long value
 * @author kc
 *
 */
public class RoundValueExpression extends LongValueExpression {

	public RoundValueExpression(String property) {
		super(property);
	}

	public RoundValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}

	@Override
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		if(valueNode!=null && valueNode.isNumber()) {
			return LongNode.valueOf(Math.round(valueNode.asDouble()));
		} else if (valueNode!=null && valueNode.isTextual()){
			try {
				Double doubleVal = Double.parseDouble(valueNode.asText());
				return LongNode.valueOf(Math.round(doubleVal));
			} catch(NumberFormatException e) {
				throw new UnsupportedExprException("Value not parseable to a number");
			}
		} else {
			throw new UnsupportedExprException("Value not a number or text to parse to a number");
		}
	}
	

}
