/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Floor the value and returns double value
 * @author kc
 *
 */
public class FloorValueExpression extends DoubleValueExpression {

	public FloorValueExpression(String property) {
		super(property);
	}

	public FloorValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}

	@Override
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		if(valueNode!=null && valueNode.isNumber()) {
			
			return DoubleNode.valueOf(Math.floor(valueNode.asDouble()));
		} else if (valueNode!=null && valueNode.isTextual()){
			try {
				Double doubleVal = Double.parseDouble(valueNode.asText());
				return DoubleNode.valueOf(Math.floor(doubleVal));
			} catch(NumberFormatException e) {
				throw new UnsupportedExprException("Value not parseable to a number");
			}
		} else {
			throw new UnsupportedExprException("Value not a number or text to parse to a number");
		}
	}
	

}
