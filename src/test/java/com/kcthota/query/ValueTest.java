/**
 * 
 */
package com.kcthota.query;

import static com.kcthota.JSONQuery.expressions.Expr.appendTo;
import static com.kcthota.JSONQuery.expressions.Expr.prependTo;
import static com.kcthota.JSONQuery.expressions.Expr.eq;
import static com.kcthota.JSONQuery.expressions.Expr.val;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
import com.kcthota.JSONQuery.exceptions.UnsupportedExprException;

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
}
