package com.kcthota.query;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import static com.kcthota.JSONQuery.expressions.Expr.*;

public class QueryTests {
	
	@Test
	public void checkEq(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(eq("name", "Krishna"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkEqHierarchy(){
		ObjectNode nameNode = new ObjectMapper().createObjectNode();
		nameNode.put("firstName", "Krishna");
		nameNode.put("lastName", "Thota");
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).is(eq("name.lastName", "Thota"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkNe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(ne("name", "kri"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",31)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), ne("age",30)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAnd3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",30)));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void checkOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishna"), eq("age",30)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",31)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkOr3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);

		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",30)));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void checkAndOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(or(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkAndOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(or(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void checkNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(Null("age"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkNotNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(not(Null("name")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void checkNotWithMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(not(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M"))));
		Assert.assertEquals(true, result);
	}
}
