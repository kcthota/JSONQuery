package com.kcthota.query;

import static com.kcthota.JSONQuery.expressions.Expr.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;

public class QueryTest {
	
	@Test
	public void testEq(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(eq("name", "Krishna"));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testEqArrays(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putArray("names").add("KC").add("Anya");
		
		ArrayNode testNode = new ObjectMapper().createArrayNode().add("KC").add("Anya");
		
		boolean result = new Query(node).is(eq("names", testNode));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testEqHierarchy(){
		ObjectNode nameNode = new ObjectMapper().createObjectNode();
		nameNode.put("firstName", "Krishna");
		nameNode.put("lastName", "Thota");
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).is(eq("name.lastName", "Thota"));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testNe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		boolean result = new Query(node).is(ne("name", "kri"));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testAnd1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",31)));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testAnd2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), ne("age",30)));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testAnd3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(and(eq("name", "Krishna"), eq("age",30)));
		assertThat(result).isFalse();
	}
	
	@Test
	public void testOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishna"), eq("age",30)));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		
		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",31)));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testOr3(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);

		boolean result = new Query(node).is(or(eq("name", "Krishn"), eq("age",30)));
		assertThat(result).isFalse();
	}
	
	@Test
	public void testAndOr1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(or(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testAndOr2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(or(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M")));
		assertThat(result).isFalse();
	}
	
	@Test
	public void testNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(Null("age"));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testNotNull(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", (String) null);
		boolean result = new Query(node).is(not(Null("name")));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testNotWithMultipleAnd(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).is(not(and(and(eq("name", "Krishna"), eq("age",30)), eq("sex", "M"))));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testIsExist(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("sex", "M");

		boolean result = new Query(node).isExist("name");
		assertThat(result).isTrue();
		
		result = new Query(node).isExist("name1");
		assertThat(result).isFalse();
	}
	
	@Test
	public void testIsExistHierarchy(){
		ObjectNode nameNode = new ObjectMapper().createObjectNode();
		nameNode.put("firstName", "Krishna");
		nameNode.put("lastName", "Thota");
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).isExist("name.lastName");
		assertThat(result).isTrue();
		
		result = new Query(node).isExist("name.middleName");
		assertThat(result).isFalse();
		
		result = new Query(node).isExist("name.test.middleName");
		assertThat(result).isFalse();
	}
	
	@Test
	public void testPropertyWithDot(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name.first", "Krishna");
		node.put("name.last", "Thota");
		
		Query q = new Query(node);
		boolean result = q.isExist("name\\.first");
		assertThat(result).isTrue();

		result = q.isExist("name\\.first\\.name");
		assertThat(result).isFalse();
		
		assertThat(q.value("name\\.first").asText()).isEqualTo("Krishna");
	}
	
	@Test
	public void testMissingNodeException(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		Query q = new Query(node);
		try {
			q.value("age");
			fail("MissingNodeException expected");
		} catch(MissingNodeException e) {
			
		}
		
	}
	
}
