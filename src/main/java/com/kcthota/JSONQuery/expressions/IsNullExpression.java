/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 7:02:46 PM
 */
public class IsNullExpression implements ComparisonExpression {

	String property;
	
	public IsNullExpression(String property) {
		this.property = property;
	}
	public String property() {
		return property;
	}


	public JsonNode value() {
		return NullNode.getInstance();
	}

}
