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
	private Integer endIndex = null;

	public SubstringValueExpression(String property, Integer startIndex, Integer endIndex) {
		super(property);
		if (startIndex != null) {
			this.startIndex = startIndex;
		}

		if (endIndex != null) {
			this.endIndex = endIndex;
		}
	}

	public SubstringValueExpression(String property, Integer startIndex, Integer endIndex, ValueExpression innerExpression) {
		super(property, innerExpression);
		if (startIndex != null) {
			this.startIndex = startIndex;
		}

		if (endIndex != null) {
			this.endIndex = endIndex;
		}
	}

	public JsonNode evaluate(JsonNode node) {
		JsonNode valueNode = super.evaluate(node);

		if (valueNode == null || !valueNode.isTextual()) {
			throw new UnsupportedExprException("Property value is not a string");
		}

		String value = valueNode.textValue();

		if (endIndex == null || (endIndex != null && endIndex > value.length() - 1)) {
			endIndex = value.length() - 1;
		}

		return TextNode.valueOf(value.substring(startIndex, endIndex));
	}

}
