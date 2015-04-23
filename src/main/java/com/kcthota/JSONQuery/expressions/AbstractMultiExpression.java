package com.kcthota.JSONQuery.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMultiExpression implements MultiExpression {
	
	private List<Expression> expressions = new ArrayList<Expression>();
	
	public List<Expression> getExpressions() {
		
		return expressions;
	}
	
	public AbstractMultiExpression(Expression...expressions) {
		this.expressions = Arrays.asList(expressions);
	}
	
	public void add(Expression...expressions) {
		this.expressions.addAll(Arrays.asList(expressions));
	}
}
