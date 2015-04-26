/**
 * 
 */
package com.kcthota.JSONQuery;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;

/**
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 11:40:06 PM
 */
public abstract class AbstractQuery {
	
	protected JsonNode node;
	
	public AbstractQuery(JsonNode node) {
		this.node = node;
	}
 
	public abstract boolean is(ComparisonExpression expr);
	
	public abstract JsonNode value(String property);
	
	public abstract boolean isExist(String property);
	
}
