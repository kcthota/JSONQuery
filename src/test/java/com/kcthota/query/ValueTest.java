/**
 * 
 */
package com.kcthota.query;

import static com.kcthota.JSONQuery.expressions.Expr.appendTo;
import static com.kcthota.JSONQuery.expressions.Expr.prependTo;
import static com.kcthota.JSONQuery.expressions.Expr.eq;
import static com.kcthota.JSONQuery.expressions.Expr.val;
import static com.kcthota.JSONQuery.expressions.Expr.trim;
import static com.kcthota.JSONQuery.expressions.Expr.upper;
import static com.kcthota.JSONQuery.expressions.Expr.lower;
import static com.kcthota.JSONQuery.expressions.Expr.replace;
import static com.kcthota.JSONQuery.expressions.Expr.substring;
import static com.kcthota.JSONQuery.expressions.Expr.length;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;
import com.kcthota.JSONQuery.expressions.Expr;

/**
 * @author Krishna Chaitanya Thota
 * Apr 26, 2015 1:40:47 AM
 */
public class ValueTest {
	@Test
	public void testValue1(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		assertThat(q.value("city").textValue()).isEqualTo("Santa Clara");
		
		assertThat(q.value("name/firstName").textValue()).isEqualTo("Krishna");
		
		assertThat(q.value(val("name/lastName")).textValue()).isEqualTo("Thota");
		
		assertThat(q.value(val("interests/0")).textValue()).isEqualTo("hiking");
		
		assertThat(q.value("interests/1").textValue()).isEqualTo("biking");
		
		assertThat(q.value(val(val("name"), "firstName")).textValue()).isEqualTo("Krishna");
	}
	
	@Test
	public void testAppendTo(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(appendTo("name/firstName", "1")).textValue()).isEqualTo("Krishna1");
		
		assertThat(q.value(appendTo("city", ", CA")).textValue()).isEqualTo("Santa Clara, CA");
		
		assertThat(q.value(appendTo(val("name/firstName"), null, " C" )).textValue()).isEqualTo("Krishna C");
		
		assertThat(q.value(appendTo(val("name/firstName"), " C")).textValue()).isEqualTo("Krishna C");

		
		assertThat(q.is(eq(appendTo("name/lastName", "1"), "Thota1"))).isTrue();
		
		assertThat(q.value(appendTo("interests/0", " hills")).textValue()).isEqualTo("hiking hills");
		
		try {
			q.value(appendTo("age", "1"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
		
	}
	
	@Test
	public void testPrependTo(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(prependTo("name/firstName", "Mr. ")).textValue()).isEqualTo("Mr. Krishna");
		
		assertThat(q.value(prependTo(val("name/firstName"), "Mr. ")).textValue()).isEqualTo("Mr. Krishna");
		
		assertThat(q.is(eq(prependTo("name/lastName", "1"), "1Thota"))).isTrue();
		
		assertThat(q.value(prependTo("interests/0", "hill ")).textValue()).isEqualTo("hill hiking");
		
		try {
			q.value(prependTo("age", "1"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
		
	}
	
	@Test
	public void testTrim(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", " Krishna ").put("lastName", " Thota ");
		node.put("age", 25);
		node.put("city", "Santa Clara, ");
		node.putArray("interests").add(" hiking ").add(" biking ");
		
		Query q=new Query(node);
		
		assertThat(q.value(trim("name/firstName")).textValue()).isEqualTo("Krishna");
		
		
		assertThat(q.is(eq(trim("name/lastName"), "Thota"))).isTrue();
		
		assertThat(q.value(appendTo(trim("interests/0"), " hills")).textValue()).isEqualTo("hiking hills");
		
		try {
			q.value(trim("age"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
		
	}
	
	@Test
	public void testUpper(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara,");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(upper("name/firstName")).textValue()).isEqualTo("KRISHNA");
		
		
		assertThat(q.is(eq(upper("name/lastName"), "THOTA"))).isTrue();
		
		assertThat(q.value(upper(appendTo("interests/0", " hills"))).textValue()).isEqualTo("HIKING HILLS");
		
		try {
			q.value(trim("age"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
		
	}
	
	@Test
	public void testLower(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara,");
		node.putArray("interests").add("HIKING").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(lower("name/firstName")).textValue()).isEqualTo("krishna");
		
		
		assertThat(q.is(eq(lower("name/lastName"), "thota"))).isTrue();
		
		assertThat(q.value(lower(appendTo("interests/0", " hills"))).textValue()).isEqualTo("hiking hills");
		
		try {
			q.value(trim("age"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
		
	}
	
	@Test
	public void testReplace(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(replace("name/firstName", "Krish", "Chris")).textValue()).isEqualTo("Chrisna");
		
		
		assertThat(q.is(eq(replace("name/lastName", "Thota", "KC"), "KC"))).isTrue();
		
		assertThat(q.is(eq(replace("city", " ", ""), "SantaClara"))).isTrue();
		
		assertThat(q.value(lower(replace("interests/0", "hiking", "hike"))).textValue()).isEqualTo("hike");
		
		assertThat(q.is(eq(replace("city", "something", "somethingelse"), "Santa Clara"))).isTrue();
		
		try {
			q.value(trim("age"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
	}
	
	@Test
	public void testSubstring(){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q=new Query(node);
		
		assertThat(q.value(substring("name/firstName", 1)).textValue()).isEqualTo("rishna");
		
		
		assertThat(q.is(eq(substring("name/lastName", 0, 2), "Th"))).isTrue();
		
		assertThat(q.is(eq(substring("city", 6, 20), "Clara"))).isTrue();
		
		assertThat(q.value(lower(substring("interests/0", 3))).textValue()).isEqualTo("ing");
		
		
		try {
			q.value(trim("age"));
			fail("UnsupportedExprException expected when appending to non-string values");
		} catch(UnsupportedExprException e) {
			assertThat(e.getMessage()).isEqualTo("Property value is not a string");
		}
	}
	
	@Test
	public void testLengthExpression() {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("age", 25);
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		Query q = Query.q(node);
		
		assertThat(q.value(length("city"))).isEqualTo(11);
		
		assertThat(q.value(length(val("name"), "firstName"))).isEqualTo(7);
		
		assertThat(q.value(length("interests"))).isEqualTo(2);
		
		assertThat(q.value(length(null))).isEqualTo(4);
		
		assertThat(q.value(length("age"))).isEqualTo(0);
	}
}
