package com.kcthota.JSONQuery;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;
import com.kcthota.JSONQuery.expressions.IntegerValueExpression;
import com.kcthota.JSONQuery.expressions.ValueExpression;

public class Query extends AbstractQuery {

	public Query(JsonNode node) {
		super(node);
	}

	/**
	 * Verifies if the passed expression is true for the JsonNode
	 * @param expr Comparison expression to be evaluated
	 * @return returns if the expression is true for the JsonNode
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
		return value(new ValueExpression(property));
	}
	
	/**
	 * Gets the value as per expression set from the JsonNode
	 * @param expression Value expression to be evaluated 
	 * @return Returns the value for the passed expression
	 */
	public JsonNode value(ValueExpression expression) {
		return expression.evaluate(node);
	}
	
	/**
	 * Returns the integer value as per expression set
	 * @param expression IntegerValueExpression to be evaluated
	 * @return Returns the integer value of the result of expression evaluated
	 */
	public int value(IntegerValueExpression expression) {
		return expression.evaluate(node).intValue();
	}

	/**
	 * Checks if property exist in the JsonNode
	 * @param property JSON property
	 * @return Returns the value for the passed property
	 */
	public boolean isExist(String property) {
		try {
			new ValueExpression(property).evaluate(node);
		} catch (MissingNodeException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Allows filtering values in a ArrayNode as per passed ComparisonExpression
	 * @param expression
	 * @return
	 */
	public ArrayNode filter(ComparisonExpression expression) {
		
		ArrayNode result = new ObjectMapper().createArrayNode();
		if(node.isArray()) {
			Iterator<JsonNode> iterator = node.iterator();
			while(iterator.hasNext()) {
				JsonNode curNode = iterator.next();
				if(new Query(curNode).is(expression)) {
					result.add(curNode);
				}
			}
		} else {
			throw new UnsupportedExprException("Filters are only supported on ArrayNode objects");
		}
		return result;
	}
	
	/**
	 * Allows applying filter on a property value, which is ArrayNode, as per passed ComparisonExpression
	 * @param property
	 * @param expression
	 * @return
	 */
	public ArrayNode filter(String property, ComparisonExpression expression) {
		JsonNode propValueNode = this.value(property);
		return Query.q(propValueNode).filter(expression);
	}
	
	/**
	 * Spins up a new instance of Query
	 * @param node
	 * @return
	 */
	public static Query q(JsonNode node) {
		return new Query(node);
	}
}
