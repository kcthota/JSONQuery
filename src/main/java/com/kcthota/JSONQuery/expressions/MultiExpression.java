package com.kcthota.JSONQuery.expressions;

import java.util.List;


public interface MultiExpression extends Expression {
	
	public List<Expression> getExpressions();
}
