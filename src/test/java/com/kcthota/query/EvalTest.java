package com.kcthota.query;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Eval;

public class EvalTest {
	
	@Test
	public void booleanExprTest() {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		assertThat(Eval.eval(node, "'${city}' == 'Santa Clara'")).isEqualTo(true);
		
		assertThat(Eval.eval(node, "'${interests/0}' == 'hiking'")).isEqualTo(true);
		
		assertThat(Eval.eval(node, "'${name/firstName}' == 'Krishna'")).isEqualTo(true);
	}
	
	@Test
	public void valueExtractionTest() {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.putObject("name").put("firstName", "Krishna").put("lastName", "Thota");
		node.put("city", "Santa Clara");
		node.putArray("interests").add("hiking").add("biking");
		
		assertThat(Eval.eval(node, "'${city}'")).isEqualTo("Santa Clara");
		
		assertThat(Eval.eval(node, "'${interests/0}'")).isEqualTo("hiking");
		
		assertThat(Eval.eval(node, "'${name/firstName}'")).isEqualTo("Krishna");
		
	}
}
