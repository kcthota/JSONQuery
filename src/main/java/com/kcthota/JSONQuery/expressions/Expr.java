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
		return eq(property, TextNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, Integer value) {
		return eq(property, IntNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, Long value) {
		return eq(property, LongNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, boolean value) {
		return eq(property, BooleanNode.valueOf(value));
	}
	
	public static ComparisonExpression eq(String property, JsonNode value) {
		EqExpression expression = new EqExpression(property, value);
		return expression;
	}
	
	public static ComparisonExpression ne(String property, String value) {
		return ne(property, TextNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, Integer value) {
		return ne(property, IntNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, Long value) {
		return ne(property, LongNode.valueOf(value));
	}
	
	public static ComparisonExpression ne(String property, boolean value) {
		return ne(property, BooleanNode.valueOf(value));
	}

	public static ComparisonExpression ne(String property, JsonNode value) {
		NeExpression expression = new NeExpression(property, value);
		return expression;
	}
	
	public static ComparisonExpression gt(String property, Integer value) {
		GtExpression expression = new GtExpression(property, IntNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression gt(String property, Long value) {
		GtExpression expression = new GtExpression(property, LongNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression gt(String property, Float value) {
		GtExpression expression = new GtExpression(property, FloatNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression gt(String property, Double value) {
		GtExpression expression = new GtExpression(property, DoubleNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression lt(String property, Integer value) {
		LtExpression expression = new LtExpression(property, IntNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression lt(String property, Long value) {
		LtExpression expression = new LtExpression(property, LongNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression lt(String property, Float value) {
		LtExpression expression = new LtExpression(property, FloatNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression lt(String property, Double value) {
		LtExpression expression = new LtExpression(property, DoubleNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression ge(String property, Integer value) {
		GeExpression expression = new GeExpression(property, IntNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression ge(String property, Long value) {
		GeExpression expression = new GeExpression(property, LongNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression ge(String property, Float value) {
		GeExpression expression = new GeExpression(property, FloatNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression ge(String property, Double value) {
		GeExpression expression = new GeExpression(property, DoubleNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression le(String property, Integer value) {
		LeExpression expression = new LeExpression(property, IntNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression le(String property, Long value) {
		LeExpression expression = new LeExpression(property, LongNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression le(String property, Float value) {
		LeExpression expression = new LeExpression(property, FloatNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression le(String property, Double value) {
		LeExpression expression = new LeExpression(property, DoubleNode.valueOf(value));
		return expression;
	}
	
	public static ComparisonExpression and(ComparisonExpression...expressions) {
		AndExpression expression = new AndExpression(expressions);
		return expression;
	}
	
	public static ComparisonExpression or(ComparisonExpression...expressions) {
		OrExpression expression = new OrExpression(expressions);
		return expression;
	}
	
	public static ComparisonExpression Null(String property) {
		IsNullExpression expression = new IsNullExpression(property);
		return expression;
	}
	
	public static ComparisonExpression not(ComparisonExpression expr) {
		NotExpression expression = new NotExpression(expr);
		return expression;
	}
	
	public static ComparisonExpression substringof(String property, String value) {
		SubstringOf expression = new SubstringOf(property, TextNode.valueOf(value));
		return expression;
	}
	
}
