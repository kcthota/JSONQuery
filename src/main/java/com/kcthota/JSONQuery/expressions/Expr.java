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
	
	public static ComparisonExpression eq(ValueExpression expression, String value) {
		return eq(expression, TextNode.valueOf(value));
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
	
	public static ComparisonExpression ne(ValueExpression expression, String value) {
		return ne(expression, TextNode.valueOf(value));
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
		return substringof(val(property), value);
	}
	
	public static ComparisonExpression substringof(ValueExpression expression, String value) {
		return new SubstringOfExpression(expression, TextNode.valueOf(value));
	}
	
	public static ComparisonExpression startsWith(String property, String value) {
		return startsWith(val(property), value);
	}
	
	public static ComparisonExpression startsWith(ValueExpression expression, String value) {
		return new StartsWithExpression(expression, TextNode.valueOf(value));
	}
	
	public static ComparisonExpression endsWith(String property, String value) {
		return endsWith(val(property), value);
	}
	
	public static ComparisonExpression endsWith(ValueExpression expression, String value) {
		return new EndsWithExpression(expression, TextNode.valueOf(value));
	}
	
	public static ValueExpression val(String property) {
		return new ValueExpression(property);
	}
	
	public static ValueExpression val(ValueExpression valExpression, String property) {
		return new ValueExpression(property, valExpression);
	}
	
	public static ValueExpression appendTo(String property, String appendText) {
		return new AppendToValueExpression(property, appendText);
	}
	
	public static ValueExpression appendTo(ValueExpression valExpression, String property, String appendText) {
		return new AppendToValueExpression(property, appendText, valExpression);
	}
	
	public static ValueExpression appendTo(ValueExpression valExpression, String appendText) {
		return new AppendToValueExpression(null, appendText, valExpression);
	}
	
	public static ValueExpression prependTo(String property, String prependText) {
		return new PrependToValueExpression(property, prependText);
	}
	
	public static ValueExpression prependTo(ValueExpression valExpression, String property, String prependText) {
		return new PrependToValueExpression(property, prependText, valExpression);
	}
	
	public static ValueExpression prependTo(ValueExpression valExpression, String prependText) {
		return new PrependToValueExpression(null, prependText, valExpression);
	}
	
	public static ValueExpression trim(String property) {
		return new TrimValueExpression(property);
	}
	
	public static ValueExpression trim(ValueExpression valExpression, String property) {
		return new TrimValueExpression(property, valExpression);
	}
	
	public static ValueExpression trim(ValueExpression valExpression) {
		return new TrimValueExpression(null, valExpression);
	}
	
	public static ValueExpression upper(String property) {
		return new ToUpperValueExpression(property);
	}
	
	public static ValueExpression upper(ValueExpression valExpression, String property) {
		return new ToUpperValueExpression(property, valExpression);
	}
	
	public static ValueExpression upper(ValueExpression valExpression) {
		return new ToUpperValueExpression(null, valExpression);
	}
	
	public static ValueExpression lower(String property) {
		return new ToLowerValueExpression(property);
	}
	
	public static ValueExpression lower(ValueExpression valExpression, String property) {
		return new ToLowerValueExpression(property, valExpression);
	}
	
	public static ValueExpression lower(ValueExpression valExpression) {
		return new ToLowerValueExpression(null, valExpression);
	}
	
	public static ValueExpression replace(String property, String target, String replacement) {
		return new ReplaceValueExpression(property, target, replacement);
	}
	
	public static ValueExpression replace(ValueExpression valExpression, String property, String target, String replacement) {
		return new ReplaceValueExpression(property, target, replacement, valExpression);
	}
	
	public static ValueExpression replace(ValueExpression valExpression, String target, String replacement) {
		return new ReplaceValueExpression(null, target, replacement, valExpression);
	}
	
	public static ValueExpression substring(String property, int startIndex) {
		return new SubstringValueExpression(property, startIndex, null);
	}
	
	public static ValueExpression substring(String property, int startIndex, int endIndex) {
		return new SubstringValueExpression(property, startIndex, endIndex);
	}
	
	public static ValueExpression substring(ValueExpression valExpression, String property, int startIndex) {
		return new SubstringValueExpression(property, startIndex, null, valExpression);
	}
	
	public static ValueExpression substring(ValueExpression valExpression, String property, int startIndex, int endIndex) {
		return new SubstringValueExpression(property, startIndex, endIndex, valExpression);
	}
	
	public static ValueExpression substring(ValueExpression valExpression, int startIndex) {
		return new SubstringValueExpression(null, startIndex, null, valExpression);
	}
	
	public static ValueExpression substring(ValueExpression valExpression, int startIndex, int endIndex) {
		return new SubstringValueExpression(null, startIndex, endIndex, valExpression);
	}
	
}
