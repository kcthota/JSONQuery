/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Returns the value of the property in the json node.
 * @author Krishna Chaitanya Thota
 * Apr 25, 2015 8:18:49 PM
 */
public class AppendToValueExpression extends ValueExpression {
	
	private String appendText = "";
	
	public AppendToValueExpression(String property, String appendText) {
		super(property);
		if(appendText!=null) {
			this.appendText = appendText;
		}
	}
	
	
	public JsonNode evaluate(JsonNode node) {
	
		JsonNode valueNode = getValue(node, property);
		
		if(valueNode==null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		return TextNode.valueOf(valueNode.textValue() + appendText);
	}
	
}
