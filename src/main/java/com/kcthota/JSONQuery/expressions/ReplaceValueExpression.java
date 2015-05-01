/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Replaces matching string with new value and returns the changed value
 * @author Krishna Chaitanya Thota
 * Apr 28, 2015 7:28:45 PM
 */
public class ReplaceValueExpression extends StringValueExpression {
	
	private String target = "";
	private String replacement = "";
	
	public ReplaceValueExpression(String property, String target, String replacement) {
		super(property);
		if(target!=null) {
			this.target = target;
		}
		
		if(replacement!=null) {
			this.replacement = replacement;
		}
	}
	
	public ReplaceValueExpression(String property, String target, String replacement, ValueExpression innerExpression) {
		super(property, innerExpression);
		if(target!=null) {
			this.target = target;
		}
		
		if(replacement!=null) {
			this.replacement = replacement;
		}
	}
	
	
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		
		if(valueNode==null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		return TextNode.valueOf(valueNode.textValue().replace(target, replacement));
	}
	
}
