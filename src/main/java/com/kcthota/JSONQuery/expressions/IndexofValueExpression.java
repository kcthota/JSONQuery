/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

/**
 * @author Krishna Chaitanya Thota
 * Apr 29, 2015 11:14:38 PM
 */
public class IndexofValueExpression extends IntegerValueExpression {

	private String str;
	public IndexofValueExpression(String property, String str) {
		super(property);
		this.str=str;
	}

	public IndexofValueExpression(String property, String str, ValueExpression innerExpression) {
		super(property, innerExpression);
		this.str=str;
	}

	@Override
	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);
		if(this.str!=null && valueNode!=null && valueNode.isTextual()) {
			return IntNode.valueOf(valueNode.textValue().indexOf(str));
		} 

		return IntNode.valueOf(-1);
	}
	
	

}
