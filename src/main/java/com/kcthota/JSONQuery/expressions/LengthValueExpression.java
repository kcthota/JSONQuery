/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

/**
 * @author Krishna Chaitanya Thota
 * Apr 29, 2015 10:47:43 PM
 */
public class LengthValueExpression extends IntegerValueExpression {

	public LengthValueExpression(String property) {
		super(property);
	}

	public LengthValueExpression(String property, ValueExpression innerExpression) {
		super(property, innerExpression);
	}

	@Override
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		if(valueNode!=null && valueNode.isTextual()) {
			return IntNode.valueOf(valueNode.textValue().length());
		} else {
			return IntNode.valueOf(valueNode.size());
		}
	}
	

}
