/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Ceil the value and returns double value
 * @author kc
 *
 */
public class CeilValueExpression extends DoubleValueExpression {

	public CeilValueExpression(String property) {
		super(property);
	}

	public CeilValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}

	@Override
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		if(valueNode!=null && valueNode.isNumber()) {
			
			return DoubleNode.valueOf(Math.ceil(valueNode.asDouble()));
		} else if (valueNode!=null && valueNode.isTextual()){
			try {
				Double doubleVal = Double.parseDouble(valueNode.asText());
				return DoubleNode.valueOf(Math.ceil(doubleVal));
			} catch(NumberFormatException e) {
				throw new UnsupportedExprException("Value not parseable to a number");
			}
		} else {
			throw new UnsupportedExprException("Value not a number or text to parse to a number");
		}
	}
	

}
