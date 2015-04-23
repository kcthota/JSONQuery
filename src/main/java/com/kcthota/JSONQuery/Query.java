package com.kcthota.JSONQuery;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.expressions.AndExpression;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;
import com.kcthota.JSONQuery.expressions.EqExpression;
import com.kcthota.JSONQuery.expressions.Expression;
import com.kcthota.JSONQuery.expressions.MultiExpression;
import com.kcthota.JSONQuery.expressions.NeExpression;

public class Query {
	
	private JsonNode node;
	
	public Query(JsonNode node) {
		this.node = node;
	}
	
	public boolean is(Expression expr) {
		return is(expr, null);
	}
	
	private boolean is(Expression expr, Boolean currentResult) {
		//at any time if currentResult is false, stop and return false;
		if(currentResult!=null && !currentResult)
			return false;

		if(expr instanceof ComparisonExpression) {
			ComparisonExpression castedExpr = (ComparisonExpression) expr;
			if(castedExpr instanceof EqExpression) {
				return node.get(castedExpr.property()).equals(castedExpr.value());
			} else if(expr instanceof NeExpression) {
				return !node.get(castedExpr.property()).equals(castedExpr.value());
			}
		} else if(expr instanceof MultiExpression) {
			MultiExpression castedExpr = (MultiExpression) expr;
			if(castedExpr instanceof AndExpression) {
				List<Expression> expressions = castedExpr.getExpressions();
				for(Expression curExpr : expressions) {
					if(!is(curExpr,currentResult)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
