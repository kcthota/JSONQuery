package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Evaluates less than or equal to
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:03:35 AM
 */
public class LeExpression extends SimpleComparisonExpression {

	public LeExpression(ValueExpression expression, JsonNode value) {
		super(expression, value);
	}
	
	@Override
	public boolean evaluate(JsonNode node) {
		return doLe(expression().evaluate(node), value());
	}
	
	protected boolean doLe(JsonNode propertyNode, JsonNode passedNode){
		if(propertyNode.isNumber() && passedNode.isNumber() && propertyNode.numberType() == passedNode.numberType()) {
			Number propertyValue = propertyNode.numberValue();
			Number passedValue = passedNode.numberValue();
			if(propertyNode.numberType() == NumberType.INT) {
				return ((Integer) propertyValue <= (Integer) passedValue);
			} else if(propertyNode.numberType() == NumberType.LONG) {
				return ((Long) propertyValue <= (Long) passedValue);
			} else if(propertyNode.numberType() == NumberType.FLOAT) {
				return ((Float) propertyValue <= (Float) passedValue);
			} else if(propertyNode.numberType() == NumberType.DOUBLE) {
				return ((Double) propertyValue <= (Double) passedValue);
			} else {
				throw new UnsupportedExprException("Le supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Le supports only numeric values of same type for comparison");
		}
	}

	

}
