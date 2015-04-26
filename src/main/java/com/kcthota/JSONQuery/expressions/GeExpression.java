package com.kcthota.JSONQuery.expressions;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

/**
 * Evaluates greater than or equal to
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 12:04:07 AM
 */
public class GeExpression extends SimpleComparisonExpression {

	public GeExpression(String property, JsonNode value) {
		super(property, value);
	}
	
	@Override
	public boolean evaluate(JsonNode node) {
		return doGe(expression().evaluate(node), value());
	}
	
	private boolean doGe(JsonNode propertyNode, JsonNode passedNode){
		if(propertyNode.isNumber() && passedNode.isNumber() && propertyNode.numberType() == passedNode.numberType()) {
			Number propertyValue = propertyNode.numberValue();
			Number passedValue = passedNode.numberValue();
			if(propertyNode.numberType() == NumberType.INT) {
				return ((Integer) propertyValue >= (Integer) passedValue);
			} else if(propertyNode.numberType() == NumberType.LONG) {
				return ((Long) propertyValue >= (Long) passedValue);
			} else if(propertyNode.numberType() == NumberType.FLOAT) {
				return ((Float) propertyValue >= (Float) passedValue);
			} else if(propertyNode.numberType() == NumberType.DOUBLE) {
				return ((Double) propertyValue >=(Double) passedValue);
			} else {
				throw new UnsupportedExprException("Ge supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Ge supports only numeric values of same type for comparison");
		}
	}

}
