package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class Expr {
	
	public static Expression eq(String property, String value) {
		return eq(property, TextNode.valueOf(value));
	}
	
	public static Expression eq(String property, Integer value) {
		return eq(property, IntNode.valueOf(value));
	}
	
	public static Expression eq(String property, Long value) {
		return eq(property, LongNode.valueOf(value));
	}
	
	public static Expression eq(String property, JsonNode value) {
		EqExpression expression = new EqExpression(property, value);
		return expression;
	}
	
	public static Expression ne(String property, String value) {
		return ne(property, TextNode.valueOf(value));
	}
	
	public static Expression ne(String property, Integer value) {
		return ne(property, IntNode.valueOf(value));
	}
	
	public static Expression ne(String property, Long value) {
		return ne(property, LongNode.valueOf(value));
	}
	

	public static Expression ne(String property, JsonNode value) {
		NeExpression expression = new NeExpression(property, value);
		return expression;
	}
	
	public static Expression and(Expression...expressions) {
		AndExpression expression = new AndExpression(expressions);
		return expression;
	}
	
	public static Expression or(Expression...expressions) {
		OrExpression expression = new OrExpression(expressions);
		return expression;
	}
	
}
