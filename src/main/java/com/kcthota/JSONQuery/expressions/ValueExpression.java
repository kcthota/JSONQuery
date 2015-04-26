/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;

/**
 * Returns the value of the property in the json node.
 * @author Krishna Chaitanya Thota
 * Apr 25, 2015 8:18:49 PM
 */
public class ValueExpression implements Expression {
	
	String property;
	
	public ValueExpression(String property) {
		this.property = property;
	}
	
	public JsonNode evaluate(JsonNode node) {
	
		return getValue(node, property);
	}
	
	/**
	 * Fetches the value for a property from the passed JsonNode. Throws MissingNodeException if the property doesn't exist
	 * @param node
	 * @param property
	 * @return
	 */
	protected JsonNode getValue(JsonNode node, String property) {
		if(node==null) {
			throw new MissingNodeException(property + " is missing");
		}
		
		JsonNode value = node.at(formatPropertyName(property));
		if (value.isMissingNode()) {
			throw new MissingNodeException(property + " is missing");
		}
		return value;	
	}
	
	
	/**
	 * Formats the property name. Example: converts a\.b to a.b.
	 * @param property
	 * @return
	 */
	protected String formatPropertyName(String property) {
		if(!property.startsWith("/")) {
			return "/"+property;
		}
		return property;
	}
	
}
