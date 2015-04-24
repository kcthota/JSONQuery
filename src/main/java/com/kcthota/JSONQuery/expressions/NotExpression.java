package com.kcthota.JSONQuery.expressions;

public class NotExpression implements Expression {
	
	Expression expression;
	
	public NotExpression(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

}
