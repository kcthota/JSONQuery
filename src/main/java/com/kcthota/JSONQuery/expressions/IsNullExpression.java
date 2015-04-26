/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * Evaluates null
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 7:02:46 PM
 */
public class IsNullExpression extends SimpleComparisonExpression {

	public IsNullExpression(String property) {
		super(property, NullNode.getInstance());
	}

	@Override
	public boolean evaluate(JsonNode node) {
		return expression().evaluate(node).equals(NullNode.getInstance());
	}
}
