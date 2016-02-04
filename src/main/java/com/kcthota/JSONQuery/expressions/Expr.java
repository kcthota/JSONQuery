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
	
	public static EqExpression eq(String property, String value) {
		return eq(val(property), TextNode.valueOf(value));
	}
	
	public static EqExpression eq(String property, Integer value) {
		return eq(val(property), IntNode.valueOf(value));
	}
	
	public static EqExpression eq(String property, Long value) {
		return eq(val(property), LongNode.valueOf(value));
	}
	
	public static EqExpression eq(String property, boolean value) {
		return eq(val(property), BooleanNode.valueOf(value));
	}
	
	public static EqExpression eq(String property, JsonNode value) {
		return eq(val(property), value);
	}
	
	public static EqExpression eq(ValueExpression expression, String value) {
		return eq(expression, TextNode.valueOf(value));
	}
	
	public static EqExpression eq(ValueExpression expression, JsonNode value) {
		return new EqExpression(expression, value);
	}
	
	public static NeExpression ne(String property, String value) {
		return ne(val(property), TextNode.valueOf(value));
	}
	
	public static NeExpression ne(String property, Integer value) {
		return ne(val(property), IntNode.valueOf(value));
	}
	
	public static NeExpression ne(String property, Long value) {
		return ne(val(property), LongNode.valueOf(value));
	}
	
	public static NeExpression ne(String property, boolean value) {
		return ne(val(property), BooleanNode.valueOf(value));
	}
	
	public static NeExpression ne(String property, JsonNode value) {
		return ne(val(property), value);
	}
	
	public static NeExpression ne(ValueExpression expression, String value) {
		return ne(expression, TextNode.valueOf(value));
	}

	public static NeExpression ne(ValueExpression expression, JsonNode value) {
		return new NeExpression(expression, value);
	}
	
	public static GtExpression gt(String property, Integer value) {
		return new GtExpression(val(property), IntNode.valueOf(value));
	}
	
	public static GtExpression gt(String property, Long value) {
		return new GtExpression(val(property), LongNode.valueOf(value));
	}
	
	public static GtExpression gt(String property, Float value) {
		return new GtExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static GtExpression gt(String property, Double value) {
		return new GtExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static LtExpression lt(String property, Integer value) {
		return new LtExpression(val(property), IntNode.valueOf(value));
	}
	
	public static LtExpression lt(String property, Long value) {
		return new LtExpression(val(property), LongNode.valueOf(value));
	}
	
	public static LtExpression lt(String property, Float value) {
		return new LtExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static LtExpression lt(String property, Double value) {
		return new LtExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static GeExpression ge(String property, Integer value) {
		return new GeExpression(val(property), IntNode.valueOf(value));
	}
	
	public static GeExpression ge(String property, Long value) {
		return new GeExpression(val(property), LongNode.valueOf(value));
	}
	
	public static GeExpression ge(String property, Float value) {
		return new GeExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static GeExpression ge(String property, Double value) {
		return new GeExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static LeExpression le(String property, Integer value) {
		return new LeExpression(val(property), IntNode.valueOf(value));
	}
	
	public static LeExpression le(String property, Long value) {
		return new LeExpression(val(property), LongNode.valueOf(value));
	}
	
	public static LeExpression le(String property, Float value) {
		return new LeExpression(val(property), FloatNode.valueOf(value));
	}
	
	public static LeExpression le(String property, Double value) {
		return new LeExpression(val(property), DoubleNode.valueOf(value));
	}
	
	public static AndExpression and(ComparisonExpression...expressions) {
		return new AndExpression(expressions);
	}
	
	public static OrExpression or(ComparisonExpression...expressions) {
		return new OrExpression(expressions);
	}
	
	public static IsNullExpression Null(String property) {
		return new IsNullExpression(val(property));
	}
	
	public static NotExpression not(ComparisonExpression expr) {
		return new NotExpression(expr);
	}
	
	public static SubstringOfExpression substringof(String property, String value) {
		return substringof(val(property), value);
	}
	
	public static SubstringOfExpression substringof(ValueExpression expression, String value) {
		return new SubstringOfExpression(expression, TextNode.valueOf(value));
	}
	
	public static StartsWithExpression startsWith(String property, String value) {
		return startsWith(val(property), value);
	}
	
	public static StartsWithExpression startsWith(ValueExpression expression, String value) {
		return new StartsWithExpression(expression, TextNode.valueOf(value));
	}
	
	public static EndsWithExpression endsWith(String property, String value) {
		return endsWith(val(property), value);
	}
	
	public static EndsWithExpression endsWith(ValueExpression expression, String value) {
		return new EndsWithExpression(expression, TextNode.valueOf(value));
	}
	
	public static ValueExpression val(String property) {
		return new ValueExpression(property);
	}
	
	public static ValueExpression val(ValueExpression valExpression, String property) {
		return new ValueExpression(property, valExpression);
	}
	
	public static AppendToValueExpression appendTo(String property, String appendText) {
		return new AppendToValueExpression(property, appendText);
	}
	
	public static AppendToValueExpression appendTo(ValueExpression valExpression, String property, String appendText) {
		return new AppendToValueExpression(property, appendText, valExpression);
	}
	
	public static AppendToValueExpression appendTo(ValueExpression valExpression, String appendText) {
		return new AppendToValueExpression(null, appendText, valExpression);
	}
	
	public static PrependToValueExpression prependTo(String property, String prependText) {
		return new PrependToValueExpression(property, prependText);
	}
	
	public static PrependToValueExpression prependTo(ValueExpression valExpression, String property, String prependText) {
		return new PrependToValueExpression(property, prependText, valExpression);
	}
	
	public static PrependToValueExpression prependTo(ValueExpression valExpression, String prependText) {
		return new PrependToValueExpression(null, prependText, valExpression);
	}
	
	public static TrimValueExpression trim(String property) {
		return new TrimValueExpression(property);
	}
	
	public static TrimValueExpression trim(ValueExpression valExpression, String property) {
		return new TrimValueExpression(property, valExpression);
	}
	
	public static TrimValueExpression trim(ValueExpression valExpression) {
		return new TrimValueExpression(null, valExpression);
	}
	
	public static ToUpperValueExpression upper(String property) {
		return new ToUpperValueExpression(property);
	}
	
	public static ToUpperValueExpression upper(ValueExpression valExpression, String property) {
		return new ToUpperValueExpression(property, valExpression);
	}
	
	public static ToUpperValueExpression upper(ValueExpression valExpression) {
		return new ToUpperValueExpression(null, valExpression);
	}
	
	public static ToLowerValueExpression lower(String property) {
		return new ToLowerValueExpression(property);
	}
	
	public static ToLowerValueExpression lower(ValueExpression valExpression, String property) {
		return new ToLowerValueExpression(property, valExpression);
	}
	
	public static ToLowerValueExpression lower(ValueExpression valExpression) {
		return new ToLowerValueExpression(null, valExpression);
	}
	
	public static ReplaceValueExpression replace(String property, String target, String replacement) {
		return new ReplaceValueExpression(property, target, replacement);
	}
	
	public static ReplaceValueExpression replace(ValueExpression valExpression, String property, String target, String replacement) {
		return new ReplaceValueExpression(property, target, replacement, valExpression);
	}
	
	public static ReplaceValueExpression replace(ValueExpression valExpression, String target, String replacement) {
		return new ReplaceValueExpression(null, target, replacement, valExpression);
	}
	
	public static SubstringValueExpression substring(String property, int startIndex) {
		return new SubstringValueExpression(property, startIndex, null);
	}
	
	public static SubstringValueExpression substring(String property, int startIndex, int endIndex) {
		return new SubstringValueExpression(property, startIndex, endIndex);
	}
	
	public static SubstringValueExpression substring(ValueExpression valExpression, String property, int startIndex) {
		return new SubstringValueExpression(property, startIndex, null, valExpression);
	}
	
	public static SubstringValueExpression substring(ValueExpression valExpression, String property, int startIndex, int endIndex) {
		return new SubstringValueExpression(property, startIndex, endIndex, valExpression);
	}
	
	public static SubstringValueExpression substring(ValueExpression valExpression, int startIndex) {
		return new SubstringValueExpression(null, startIndex, null, valExpression);
	}
	
	public static SubstringValueExpression substring(ValueExpression valExpression, int startIndex, int endIndex) {
		return new SubstringValueExpression(null, startIndex, endIndex, valExpression);
	}
	
	public static IntegerValueExpression length(String property) {
		return new LengthValueExpression(property);
	}
	
	public static IntegerValueExpression length(ValueExpression valExpression, String property) {
		return new LengthValueExpression(property, valExpression);
	}
	
	public static IntegerValueExpression indexof(String property, String str) {
		return new IndexofValueExpression(property, str);
	}
	
	public static IntegerValueExpression indexof(ValueExpression valExpression, String property, String str) {
		return new IndexofValueExpression(property, str, valExpression);
	}
	
	public static LongValueExpression round(String property) {
		return new RoundValueExpression(property);
	}
	
	public static LongValueExpression round(ValueExpression valExpression, String property) {
		return new RoundValueExpression(property, valExpression);
	}
	
	public static DoubleValueExpression ceil(String property) {
		return new CeilValueExpression(property);
	}
	
	public static DoubleValueExpression ceil(ValueExpression valExpression, String property) {
		return new CeilValueExpression(property, valExpression);
	}
}
