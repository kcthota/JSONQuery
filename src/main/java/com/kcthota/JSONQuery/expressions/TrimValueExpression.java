/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Returns the trimmed value of the property in the json node.
 * @author Krishna Chaitanya Thota
 * Apr 25, 2015 8:18:49 PM
 */
public class TrimValueExpression extends ValueExpression {
	
	
	public TrimValueExpression(String property) {
		super(property);
	}
	
	public TrimValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}
	
	
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		
		if(valueNode==null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		return TextNode.valueOf(valueNode.textValue().trim());
	}
	
}
