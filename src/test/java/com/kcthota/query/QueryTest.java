package com.kcthota.query;


import static com.kcthota.JSONQuery.expressions.Expr.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;
import com.kcthota.JSONQuery.expressions.AndExpression;

public class QueryTest {
	
	@Test
	public void testEq(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("active", true);
		
		Query q = new Query(node);
		boolean result = q.is(eq("name", "Krishna"));
		assertThat(result).isTrue();
		
		result = q.is(eq(appendTo("name", " C"), "Krishna C"));
		assertThat(result).isTrue();
		
		result = q.is(eq(prependTo(appendTo("name", " C"), "Mr. "), "Mr. Krishna C"));
		assertThat(result).isTrue();
		
		assertThat(q.is(eq("active", true))).isTrue();
	}
	
	@Test
	public void testEqArrays(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putArray("names").add("KC").add("Anya");
		
		ArrayNode testNode = new ObjectMapper().createArrayNode().add("KC").add("Anya");
		
		boolean result = new Query(node).is(eq("names", testNode));
		assertThat(result).isTrue();
		
		result = new Query(node).is(eq("names/0", "KC"));
		assertThat(result).isTrue();
		
		result = new Query(node).is(eq("names/1", "Anya"));
		assertThat(result).isTrue();
		
		assertThat(Query.q(node).is(ne("names", testNode))).isFalse();
	}
	
	@Test
	public void testEqHierarchy(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		
		boolean result = new Query(node).is(eq("name/lastName", "Thota"));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testNe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("active", true);
		
		Query q = new Query(node);
		
		boolean result = q.is(ne("name", "kri"));
		assertThat(result).isTrue();
		
		result = q.is(ne(prependTo("name", "Mr. "), "Mr. Krishna"));
		assertThat(result).isFalse();
		
		assertThat(q.is(ne("active", true))).isFalse();
	}
	
	@Test
	public void testAnd1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		AndExpression expr = and(eq("name", "Krishna"), eq("age",31));
		expr.add(ne("age", 30));
		boolean result = new Query(node).is(expr);
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
		
		boolean result = new Query(node).isExist("name/lastName");
		assertThat(result).isTrue();
		
		result = new Query(node).isExist("name/middleName");
		assertThat(result).isFalse();
		
		result = new Query(node).isExist("name/test/middleName");
		assertThat(result).isFalse();
	}
	
	@Test
	public void testPropertyWithDot(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name.first", "Krishna");
		node.put("name.last", "Thota");
		
		Query q = new Query(node);
		boolean result = q.isExist("name.first");
		assertThat(result).isTrue();

		result = q.isExist("name.first.name");
		assertThat(result).isFalse();
		
		assertThat(q.value("name.first").asText()).isEqualTo("Krishna");
	}
	
	@Test
	public void testMissingNodeException1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putArray("interests").add("hiking").add("biking");
		
		Query q = new Query(node);
		try {
			q.value("interests/2");
			fail("MissingNodeException expected");
		} catch(MissingNodeException e) {
			
		}
		
	}
	
	@Test
	public void testMissingNodeException2(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		
		Query q = new Query(node);
		try {
			q.value("age");
			fail("MissingNodeException expected");
		} catch(MissingNodeException e) {
			
		}
		
		try {
			q.value("name/0");
			fail("MissingNodeException expected");
		} catch(MissingNodeException e) {
			
		}
		
	}
	
	@Test
	public void testGt(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("float", 10f);
		node.put("double", 10d);
		node.put("long", 9223372036854775806l);
		
		Query q = new Query(node);
		
		boolean result = q.is(gt("int", 9));
		assertThat(result).isTrue();
		
		result = q.is(gt("float", 9f));
		assertThat(result).isTrue();
		
		result = q.is(gt("double", 9d));
		assertThat(result).isTrue();
		
		result = q.is(gt("long", 9l));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testGtExceptions(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("String", "value");
		
		Query q = new Query(node);
		
		try {
			q.is(gt("int", 9.0));
			fail("UnsupportedExprException when comparing Int with Float");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Gt supports only numeric values of same type for comparison");
		}
		
		try {
			q.is(gt("String", 9));
			fail("UnsupportedExprException when comparing String with Integer");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Gt supports only numeric values of same type for comparison");
		}
		
	}
	
	@Test
	public void testLt(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("float", 10f);
		node.put("double", 10d);
		node.put("long", 9223372036854775806l);
		
		Query q = new Query(node);
		
		boolean result = q.is(lt("int", 11));
		assertThat(result).isTrue();
		
		result = q.is(lt("float", 11f));
		assertThat(result).isTrue();
		
		result = q.is(lt("double", 11d));
		assertThat(result).isTrue();
		
		result = q.is(lt("long", 9223372036854775807l));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testLtExceptions(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("String", "value");
		
		Query q = new Query(node);
		
		try {
			q.is(lt("int", 11.0));
			fail("UnsupportedExprException when comparing Int with Float");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Lt supports only numeric values of same type for comparison");
		}
		
		try {
			q.is(lt("String", 11));
			fail("UnsupportedExprException when comparing String with Integer");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Lt supports only numeric values of same type for comparison");
		}
		
	}
	
	@Test
	public void testGe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("float", 10f);
		node.put("double", 10d);
		node.put("long", 9223372036854775803l);
		
		Query q = new Query(node);
		
		boolean result = q.is(ge("int", 10));
		assertThat(result).isTrue();
		
		result = q.is(ge("int", 9));
		assertThat(result).isTrue();
		
		result = q.is(ge("int", 11));
		assertThat(result).isFalse();
		
		result = q.is(ge("float", 10f));
		assertThat(result).isTrue();
		
		result = q.is(ge("float", 9f));
		assertThat(result).isTrue();
		
		result = q.is(ge("float", 11f));
		assertThat(result).isFalse();
		
		result = q.is(ge("double", 10d));
		assertThat(result).isTrue();
		
		result = q.is(ge("double", 9d));
		assertThat(result).isTrue();
		
		result = q.is(ge("double", 11d));
		assertThat(result).isFalse();
		
		result = q.is(ge("long", 9223372036854775803l));
		assertThat(result).isTrue();
		
		result = q.is(ge("long", 9223372036854775802l));
		assertThat(result).isTrue();
		
		result = q.is(ge("long", 9223372036854775806l));
		assertThat(result).isFalse();
	}
	
	@Test
	public void testGeExceptions(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("String", "value");
		
		Query q = new Query(node);
		
		try {
			q.is(ge("int", 11.0));
			fail("UnsupportedExprException when comparing Int with Float");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Ge supports only numeric values of same type for comparison");
		}
		
		try {
			q.is(ge("String", 11));
			fail("UnsupportedExprException when comparing String with Integer");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Ge supports only numeric values of same type for comparison");
		}
		
	}
	
	@Test
	public void testLe(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("float", 10f);
		node.put("double", 10d);
		node.put("long", 9223372036854775803l);
		
		Query q = new Query(node);
		
		boolean result = q.is(le("int", 10));
		assertThat(result).isTrue();
		
		result = q.is(le("int", 11));
		assertThat(result).isTrue();
		
		result = q.is(le("int", 9));
		assertThat(result).isFalse();
		
		result = q.is(le("float", 10f));
		assertThat(result).isTrue();
		
		result = q.is(le("float", 11f));
		assertThat(result).isTrue();
		
		result = q.is(le("float", 9f));
		assertThat(result).isFalse();
		
		result = q.is(le("double", 10d));
		assertThat(result).isTrue();
		
		result = q.is(le("double", 11d));
		assertThat(result).isTrue();
		
		result = q.is(le("double", 9d));
		assertThat(result).isFalse();
		
		result = q.is(le("long", 9223372036854775803l));
		assertThat(result).isTrue();
		
		result = q.is(le("long", 9223372036854775806l));
		assertThat(result).isTrue();
		
		result = q.is(le("long", 9223372036854775802l));
		assertThat(result).isFalse();
	}
	
	@Test
	public void testLeExceptions(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("int", 10);
		node.put("String", "value");
		
		Query q = new Query(node);
		
		try {
			q.is(le("int", 11.0));
			fail("UnsupportedExprException when comparing Int with Float");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Le supports only numeric values of same type for comparison");
		}
		
		try {
			q.is(le("String", 11));
			fail("UnsupportedExprException when comparing String with Integer");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Le supports only numeric values of same type for comparison");
		}
		
	}
	
	@Test
	public void testSubstringof(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("state", "CA");
		
		Query q = new Query(node);
		boolean result = q.is(substringof("name", "Kris"));
		assertThat(result).isTrue();
		
		result = q.is(substringof("age", "Kris")); //age not a string, returns false
		assertThat(result).isFalse();
		
		result = q.is(not(substringof("name", "Kris")));
		assertThat(result).isFalse();
		
		result = q.is(and(eq("state","CA"), substringof("name", "Kris")));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testStartsWith(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("state", "CA");
		
		Query q = new Query(node);
		boolean result = q.is(startsWith("name", "Kris"));
		assertThat(result).isTrue();
		
		result = q.is(startsWith("age", "Kris")); //age not a string, returns false
		assertThat(result).isFalse();
		
		result = q.is(not(startsWith("name", "Kris")));
		assertThat(result).isFalse();
		
		result = q.is(or(eq("state","CA"), startsWith("name", "shna")));
		assertThat(result).isTrue();
	}
	
	@Test
	public void testEndsWith(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("name", "Krishna");
		node.put("age", 31);
		node.put("state", "CA");
		
		Query q = new Query(node);
		boolean result = q.is(endsWith("name", "shna"));
		assertThat(result).isTrue();
		
		result = q.is(endsWith("age", "Kris")); //age not a string, returns false
		assertThat(result).isFalse();
		
		result = q.is(not(endsWith("name", "shna")));
		assertThat(result).isFalse();
		
		result = q.is(and(eq("state","CA"), endsWith("name", "shna")));
		assertThat(result).isTrue();
	}
	
	
}
