package com.kcthota.query;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;

import static com.kcthota.JSONQuery.expressions.Expr.*;

public class QueryTest {
	
	@Test
	public void testEq(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(eq("name", "Krishna"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testEqArrays(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putArray("names").add("KC").add("Anya");
		
		ArrayNode testNode = new ObjectMapper().createArrayNode().add("KC").add("Anya");
		
		boolean result = new Query(node).is(eq("names", testNode));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testEqHierarchy(){
		ObjectNode nameNode = new ObjectMapper().createObjectNode();
		nameNode.put("firstName", "Krishna");
		nameNode.put("lastName", "Thota");
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).is(eq("name.lastName", "Thota"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testNe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(ne("name", "kri"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testAnd1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",31)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testAnd2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), ne("age",30)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testAnd3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",30)));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishna"), eq("age",30)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",31)));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testOr3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);

		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",30)));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testAndOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(or(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testAndOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(or(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(Null("age"));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testNotNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(not(Null("name")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testNotWithMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(not(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M"))));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testIsExist(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).isExist("name");
		Assert.assertEquals(true, result);
		
		result = new Query(node).isExist("name1");
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testIsExistHierarchy(){
		ObjectNode nameNode = new ObjectMapper().createObjectNode();
		nameNode.put("firstName", "Krishna");
		nameNode.put("lastName", "Thota");
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).isExist("name.lastName");
		Assert.assertEquals(true, result);
		
		result = new Query(node).isExist("name.middleName");
		Assert.assertEquals(false, result);
		
		result = new Query(node).isExist("name.test.middleName");
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void testPropertyWithDot(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name.first", "Krishna");
		node.put("name.last", "Thota");
		
		Query q = new Query(node);
		boolean result = q.isExist("name\\.first");
		Assert.assertEquals(true, result);

		result = q.isExist("name\\.first\\.name");
		Assert.assertEquals(false, result);
		
		Assert.assertEquals("Krishna",q.value("name\\.first").asText());
	}
	
}
