package com.kcthota.JSONQuery;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;
import com.kcthota.JSONQuery.expressions.Expression;

public class Query extends AbstractQuery {

	public Query(JsonNode node) {
		super(node);
	}

	/**
	 * Verifies if the passed expression is true for the JsonNode
	 * @param expr
	 * @return
	 */
	public boolean is(Expression expr) {
		return is(expr, null);
	}


	/**
	 * Gets the value for the property from the JsonNode
	 * @param property
	 * @return
	 */
	public JsonNode value(String property) {
		return getValue(node, property);
	}

	/**
	 * Checks if property exist in the JsonNode
	 * @param property
	 * @return
	 */
	public boolean isExist(String property) {
		try {
			getValue(node, property);
		} catch (MissingNodeException e) {
			return false;
		}
		return true;
	}

	
}
