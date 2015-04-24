/**
 * 
 */
package com.kcthota.JSONQuery;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;
import com.kcthota.JSONQuery.expressions.ComparisonExpression;


/**
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 11:37:19 PM
 */
public abstract class AbstractQueryHelpers {
	
	protected boolean doGt(ComparisonExpression castedExpr, JsonNode propValue){
		if(castedExpr.value().isNumber() && propValue.isNumber() && castedExpr.value().numberType() == propValue.numberType()) {
			Number nodeValue = propValue.numberValue();
			Number passedValue = castedExpr.value().numberValue();
			if(castedExpr.value().numberType() == NumberType.INT) {
				return ((Integer) nodeValue > (Integer) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.LONG) {
				return ((Long) nodeValue > (Long) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.FLOAT) {
				return ((Float) nodeValue > (Float) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.DOUBLE) {
				return ((Double) nodeValue > (Double) passedValue);
			} else {
				throw new UnsupportedExprException("Gt supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Gt supports only numeric values of same type for comparison");
		}
	}
	
	protected boolean doLt(ComparisonExpression castedExpr, JsonNode propValue){
		if(castedExpr.value().isNumber() && propValue.isNumber() && castedExpr.value().numberType() == propValue.numberType()) {
			Number nodeValue = propValue.numberValue();
			Number passedValue = castedExpr.value().numberValue();
			if(castedExpr.value().numberType() == NumberType.INT) {
				return ((Integer) nodeValue < (Integer) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.LONG) {
				return ((Long) nodeValue < (Long) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.FLOAT) {
				return ((Float) nodeValue < (Float) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.DOUBLE) {
				return ((Double) nodeValue < (Double) passedValue);
			} else {
				throw new UnsupportedExprException("Lt supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Lt supports only numeric values of same type for comparison");
		}
	}
	
	protected boolean doGe(ComparisonExpression castedExpr, JsonNode propValue){
		if(castedExpr.value().isNumber() && propValue.isNumber() && castedExpr.value().numberType() == propValue.numberType()) {
			Number nodeValue = propValue.numberValue();
			Number passedValue = castedExpr.value().numberValue();
			if(castedExpr.value().numberType() == NumberType.INT) {
				return ((Integer) nodeValue >= (Integer) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.LONG) {
				return ((Long) nodeValue >= (Long) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.FLOAT) {
				return ((Float) nodeValue >= (Float) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.DOUBLE) {
				return ((Double) nodeValue >= (Double) passedValue);
			} else {
				throw new UnsupportedExprException("Ge supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Ge supports only numeric values of same type for comparison");
		}
	}
	
	protected boolean doLe(ComparisonExpression castedExpr, JsonNode propValue){
		if(castedExpr.value().isNumber() && propValue.isNumber() && castedExpr.value().numberType() == propValue.numberType()) {
			Number nodeValue = propValue.numberValue();
			Number passedValue = castedExpr.value().numberValue();
			if(castedExpr.value().numberType() == NumberType.INT) {
				return ((Integer) nodeValue <= (Integer) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.LONG) {
				return ((Long) nodeValue <= (Long) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.FLOAT) {
				return ((Float) nodeValue <= (Float) passedValue);
			} else if(castedExpr.value().numberType() == NumberType.DOUBLE) {
				return ((Double) nodeValue <= (Double) passedValue);
			} else {
				throw new UnsupportedExprException("Le supports only INT, LONG, DOUBLE, FLOAT values");
			}
		} else {
			throw new UnsupportedExprException("Le supports only numeric values of same type for comparison");
		}
	}
}
