/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Prepends the passed text to the value in the json node and returns a new TextNode
 * @author Krishna Chaitanya Thota
 * Apr 25, 2015 8:18:49 PM
 */
public class PrependToValueExpression extends ValueExpression {
	
	private String prependText = "";
	
	public PrependToValueExpression(String property, String prependText) {
		super(property);
		if(prependText!=null) {
			this.prependText = prependText;
		}
	}
	
	public PrependToValueExpression(String property, String prependText, ValueExpression innerExpression) {
		super(property, innerExpression);
		if(prependText!=null) {
			this.prependText = prependText;
		}
	}
	
	
	public JsonNode evaluate(JsonNode node) {
	
		JsonNode valueNode = super.evaluate(node);
		
		if(valueNode==null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		return TextNode.valueOf(prependText + valueNode.textValue());
	}
	
}
