/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Expression which can hold multiple comparison expressions. Ex: AND, OR extend this class
 * @author Krishna Chaitanya Thota Apr 25, 2015 10:49:08 PM
 */
public abstract class ContainerComparisonExpression implements ComparisonExpression {

	private List<ComparisonExpression> expressions = new ArrayList<ComparisonExpression>();

	public ContainerComparisonExpression(ComparisonExpression... expressions) {
		this.expressions = Arrays.asList(expressions);
	}

	/**
	 * Returns list of current expressions
	 * @return returns the list of comparison expressions set to the expression
	 */
	public List<ComparisonExpression> getExpressions() {

		return expressions;
	}

	/**
	 * Adds new expressions to the existing expression
	 * @param expressions Sets the passed expressions to the container expression
	 */
	public void add(ComparisonExpression... expressions) {
		this.expressions.addAll(Arrays.asList(expressions));
	}

	@Override
	public abstract boolean evaluate(JsonNode node);

}
