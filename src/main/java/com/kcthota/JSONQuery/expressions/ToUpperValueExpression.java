/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Returns the ToUpper value of the property in the json node.
 * @author Krishna Chaitanya Thota
 * Apr 27, 2015 8:18:49 PM
 */
public class ToUpperValueExpression extends StringValueExpression {
	
	public ToUpperValueExpression(String property) {
		super(property);
	}
	
	public ToUpperValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}
	
	
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		
		if(valueNode==null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		return TextNode.valueOf(valueNode.textValue().toUpperCase());
	}
	
}
