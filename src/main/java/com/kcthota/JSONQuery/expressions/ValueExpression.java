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
	
	ValueExpression innerExpression=null;
	
	public ValueExpression(String property, ValueExpression innerExpression) {
		this.property = property;
		this.innerExpression = innerExpression;
	}
	
	public ValueExpression(String property) {
		this.property = property;
	}
	
	/**
	 * Evaluates the expression on passed JSONNode
	 * @param node JSONNode object
	 * @return returns the value of the property
	 */
	public JsonNode evaluate(JsonNode node) {
		if(innerExpression==null) {
			return getValue(node, property);
		} else {
			return getValue(innerExpression.evaluate(node), property);
		}
	}
	
	/**
	 * Fetches the value for a property from the passed JsonNode. Throws MissingNodeException if the property doesn't exist
	 * @param node JSONNode Object
	 * @param property Property for which the value should be returned
	 * @return returns the value of the property
	 */
	protected JsonNode getValue(JsonNode node, String property) {
		if(node==null) {
			throw new MissingNodeException(property + " is missing");
		}
		
		if(property==null) {
			return node;
		}
		
		JsonNode value = node.at(formatPropertyName(property));
		if (value.isMissingNode()) {
			throw new MissingNodeException(property + " is missing");
		}
		return value;	
	}
	
	
	/**
	 * Formats the property name. Prepends if / is missing
	 * @param property JSONPointer string
	 * @return Format the property name. Prepends / if missing
	 */
	protected String formatPropertyName(String property) {
		if(!property.startsWith("/")) {
			return "/"+property;
		}
		return property;
	}
	
}
