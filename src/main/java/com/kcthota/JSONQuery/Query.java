package com.kcthota.JSONQuery;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.expressions.AndExpression;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;
import com.kcthota.JSONQuery.expressions.EqExpression;
import com.kcthota.JSONQuery.expressions.Expression;
import com.kcthota.JSONQuery.expressions.MultiExpression;
import com.kcthota.JSONQuery.expressions.NeExpression;
import com.kcthota.JSONQuery.expressions.OrExpression;

public class Query {

	private JsonNode node;

	public Query(JsonNode node) {
		this.node = node;
	}

	public boolean is(Expression expr) {
		return is(expr, null);
	}

	private boolean is(Expression expr, Boolean currentResult) {
		// at any time if currentResult is false, stop and return false;
		if (currentResult != null && !currentResult)
			return false;

		try {
			if (expr instanceof ComparisonExpression) {
				ComparisonExpression castedExpr = (ComparisonExpression) expr;
				if (castedExpr instanceof EqExpression) {
					return getValue(node, castedExpr.property()).equals(castedExpr.value());
				} else if (expr instanceof NeExpression) {
					return !getValue(node, castedExpr.property()).equals(castedExpr.value());
				}
			} else if (expr instanceof MultiExpression) {
				MultiExpression castedExpr = (MultiExpression) expr;
				List<Expression> expressions = castedExpr.getExpressions();
				if (castedExpr instanceof AndExpression) {
					for (Expression curExpr : expressions) {
						currentResult = is(curExpr, currentResult);
						if (!currentResult) {
							return false;
						}
					}
					return true;
				} else if (castedExpr instanceof OrExpression) {
					for (Expression curExpr : expressions) {
						currentResult = is(curExpr, currentResult);
						if (currentResult) {
							return true;
						}

						// reset currentResult for Or
						currentResult = null;
					}
					return false;
				}
			}
		} catch (Exception e) {
			//ignore exception and return false for now.
			//TODO handle exceptions
		}
		return false;
	}
	
	public JsonNode value(String property) {
		return getValue(node, property);
	}

	private JsonNode getValue(JsonNode node, String property){
		JsonNode value=null;
		int index = property.indexOf('.');
		if(index<0) {
			value = node.path(property);
			if(value.isMissingNode()) {
				throw new RuntimeException("Property doesn't exist: "+property);
			}
			return value;
		} else {
			String propertyName = property.substring(0, property.indexOf('.'));
			return getValue(node.get(propertyName), property.substring(index+1));
		}
	}
}
