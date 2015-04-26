package com.kcthota.JSONQuery;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;
import com.kcthota.JSONQuery.expressions.ValueExpression;

public class Query extends AbstractQuery {

	public Query(JsonNode node) {
		super(node);
	}

	/**
	 * Verifies if the passed expression is true for the JsonNode
	 * @param expr
	 * @return
	 */
	public boolean is(ComparisonExpression expr) {
		try {
			if(expr!=null) {
				return expr.evaluate(node);
			}
		} catch (MissingNodeException e) {
			return false;
		}
		
		return false;
	}


	/**
	 * Gets the value for the property from the JsonNode
	 * @param property
	 * @return
	 */
	public JsonNode value(String property) {
		return new ValueExpression(property).evaluate(node);
	}
	
	/**
	 * Gets the value as per expression set from the JsonNode
	 * @param property
	 * @return
	 */
	public JsonNode value(ValueExpression expression) {
		return expression.evaluate(node);
	}

	/**
	 * Checks if property exist in the JsonNode
	 * @param property
	 * @return
	 */
	public boolean isExist(String property) {
		try {
			new ValueExpression(property).evaluate(node);
		} catch (MissingNodeException e) {
			return false;
		}
		return true;
	}
}
