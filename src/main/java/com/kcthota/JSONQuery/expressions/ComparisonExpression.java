package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Compares the value passed to the value of the property in JsonNode
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:05:44 AM
 */
public interface ComparisonExpression extends Expression {
	/**
	 * Evalutes the comparison expression on the passed JsonNode
	 * @param node JSONNode object on which the expression should be evaluated
	 * @return Result of the comparison
	 */
	public boolean evaluate(JsonNode node);
}
