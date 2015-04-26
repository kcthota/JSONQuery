package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Expression helper to create expressions
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 9:25:26 PM
 */
public class Expr {
	
	public static ComparisonExpression eq(String property, String value) {
		return eq(val(property), TextNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, Integer value) {
		return eq(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, Long value) {
		return eq(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, boolean value) {
		return eq(val(property), BooleanNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, JsonNode value) {
		return eq(val(property), value);
	}
	
	public static ComparisonExpression eq(ValueExpression expression, JsonNode value) {
		return new EqExpression(expression, value);
	}
	
	public static ComparisonExpression ne(String property, String value) {
		return ne(val(property), TextNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, Integer value) {
		return ne(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, Long value) {
		return ne(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, boolean value) {
		return ne(val(property), BooleanNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, JsonNode value) {
		return ne(val(property), value);
	}

	public static ComparisonExpression ne(ValueExpression expression, JsonNode value) {
		return new NeExpression(expression, value);
	}
	
	public static ComparisonExpression gt(String property, Integer value) {
		return new GtExpression(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression gt(String property, Long value) {
		return new GtExpression(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression gt(String property, Float value) {
		return new GtExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static ComparisonExpression gt(String property, Double value) {
		return new GtExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static ComparisonExpression lt(String property, Integer value) {
		return new LtExpression(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression lt(String property, Long value) {
		return new LtExpression(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression lt(String property, Float value) {
		return new LtExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static ComparisonExpression lt(String property, Double value) {
		return new LtExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static ComparisonExpression ge(String property, Integer value) {
		return new GeExpression(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression ge(String property, Long value) {
		return new GeExpression(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression ge(String property, Float value) {
		return new GeExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static ComparisonExpression ge(String property, Double value) {
		return new GeExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static ComparisonExpression le(String property, Integer value) {
		return new LeExpression(val(property), IntNode.valueOf(value));
	}
	
	public static ComparisonExpression le(String property, Long value) {
		return new LeExpression(val(property), LongNode.valueOf(value));
	}
	
	public static ComparisonExpression le(String property, Float value) {
		return new LeExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static ComparisonExpression le(String property, Double value) {
		return new LeExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static ComparisonExpression and(ComparisonExpression...expressions) {
		return new AndExpression(expressions);
	}
	
	public static ComparisonExpression or(ComparisonExpression...expressions) {
		return new OrExpression(expressions);
	}
	
	public static ComparisonExpression Null(String property) {
		return new IsNullExpression(val(property));
	}
	
	public static ComparisonExpression not(ComparisonExpression expr) {
		return new NotExpression(expr);
	}
	
	public static ComparisonExpression substringof(String property, String value) {
		return new SubstringOf(val(property), TextNode.valueOf(value));
	}
	
	public static ValueExpression val(String property) {
		return new ValueExpression(property);
	}
}
