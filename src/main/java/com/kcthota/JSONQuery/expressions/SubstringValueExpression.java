/**
 * 
 */
package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Returns substring of a string
 * 
 * @author Krishna Chaitanya Thota Apr 28, 2015 8:38:07 PM
 */
public class SubstringValueExpression extends ValueExpression {

	private Integer startIndex = 0;
	private Integer length = null;

	public SubstringValueExpression(String property, Integer startIndex, Integer length) {
		super(property);
		if (startIndex != null) {
			this.startIndex = startIndex;
		}

		if (length != null) {
			this.length = length;
		}
	}

	public SubstringValueExpression(String property, Integer startIndex, Integer length, ValueExpression innerExpression) {
		super(property, innerExpression);
		if (startIndex != null) {
			this.startIndex = startIndex;
		}

		if (length != null) {
			this.length = length;
		}
	}

	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);

		if (valueNode == null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		String value = valueNode.textValue();

		if (length == null || (length != null && length > value.length())) {
			length = value.length();
		}

		return TextNode.valueOf(value.substring(startIndex, length));
	}

}
