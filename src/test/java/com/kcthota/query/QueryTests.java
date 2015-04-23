package com.kcthota.query;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import com.kcthota.JSONQuery.expressions.Expr;

public class QueryTests {
	
	@Test
	public void checkEq(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(Expr.eq("name", "Krishna"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkNe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(Expr.ne("name", "kri"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(Expr.and(Expr.eq("name", "Krishna"), Expr.eq("age",31)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(Expr.and(Expr.eq("name", "Krishna"), Expr.ne("age",30)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(Expr.and(Expr.eq("name", "Krishna"), Expr.eq("age",30)));
		Assert.assertEquals(false, result);
	}
}
