/**
 * 
 */
package com.kcthota.query;

import static com.kcthota.JSONQuery.expressions.Expr.Null;
import static com.kcthota.JSONQuery.expressions.Expr.eq;
import static com.kcthota.JSONQuery.expressions.Expr.gt;
import static com.kcthota.JSONQuery.expressions.Expr.le;
import static com.kcthota.JSONQuery.expressions.Expr.not;
import static com.kcthota.JSONQuery.expressions.Expr.or;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kcthota.JSONQuery.Query;
/**
 * @author Krishna Chaitanya Thota
 * Apr 28, 2015 9:23:50 PM
 */
public class FilterTest {
	
	private static JsonNode node;
	
	@BeforeClass
	public static void setup() {
		
		
		ObjectNode node1 = new ObjectMapper().createObjectNode();
		node1.putObject("name").put("firstName", "John").put("lastName", "Doe");
		node1.put("age", 25);
		node1.putNull("address");
		node1.put("state", "CA");
		
		ObjectNode node2 = new ObjectMapper().createObjectNode();
		node2.putObject("name").put("firstName", "Jane").put("lastName", "Doe");
		node2.put("age", 18);
		node2.put("address", "1st St.");
		node2.put("state", "CA");
		
		ObjectNode node3 = new ObjectMapper().createObjectNode();
		node3.putObject("name").put("firstName", "Jill").put("lastName", "Doe");
		node3.put("age", 30);
		node3.put("address", "2nd St.");
		node3.put("state", "FL");
		
		node = new ObjectMapper().createArrayNode().add(node1).add(node2).add(node3);
	}
	
	@Test
	public void testStringFilter() {
		ArrayNode node = new ObjectMapper().createArrayNode().add("John").add("Kevin").add("Joe").add("Jeff");
		
		Query q=new Query(node);
		ArrayNode result = q.filter(eq((String)null, "John"));
		
		assertThat(result.size()).isEqualTo(1);
		assertThat(Query.q(result).value("0").textValue()).isEqualTo("John");
	}
	
	@Test
	public void testIntegerFilter() {
		ArrayNode node = new ObjectMapper().createArrayNode().add(1).add(2).add(3).add(4);
		
		Query q=new Query(node);
		ArrayNode result = q.filter(gt((String)null, 2));
		
		assertThat(result.size()).isEqualTo(2);
		assertThat(Query.q(result).value("0").intValue()).isEqualTo(3);
		assertThat(Query.q(result).value("1").intValue()).isEqualTo(4);
	}
	
	@Test
	public void testFloatFilter() {
		ArrayNode node = new ObjectMapper().createArrayNode().add(1f).add(2.56f).add(3.01f).add(1.4f);
		
		Query q=new Query(node);
		ArrayNode result = q.filter(gt((String)null, 2f));
		
		assertThat(result.size()).isEqualTo(2);
		assertThat(Query.q(result).value("0").floatValue()).isEqualTo(2.56f);
		assertThat(Query.q(result).value("1").floatValue()).isEqualTo(3.01f);
	}
	
	@Test
	public void testJSONNodesFilter1() {
		
		Query q=new Query(FilterTest.node);
		ArrayNode result = q.filter(le("age", 18));
		
		assertThat(result.size()).isEqualTo(1);
		
		assertThat(Query.q(result).value("0/name/firstName").textValue()).isEqualTo("Jane");
		
		result = q.filter(gt("age", 25));
		
		assertThat(result.size()).isEqualTo(1);
		assertThat(Query.q(result).value("0/name/firstName").textValue()).isEqualTo("Jill");
		
		result = q.filter(or(eq("age", 25), eq("state", "FL")));
		assertThat(result.size()).isEqualTo(2);
		
	}
	
	@Test
	public void testJSONNodesFilter2() {
		ObjectNode myNode = new ObjectMapper().createObjectNode();
		myNode.putArray("users").addAll((ArrayNode)node);
		
		Query q=new Query(myNode);
		ArrayNode result = q.filter("users", le("age", 18));
		
		assertThat(result.size()).isEqualTo(1);
		
		assertThat(Query.q(result).value("0/name/firstName").textValue()).isEqualTo("Jane");
		
		result = q.filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(2);
		
		assertThat(Query.q(result).value("0/address").textValue()).isEqualTo("1st St.");
		
		assertThat(Query.q(result).value("1/address").textValue()).isEqualTo("2nd St.");
	}
	
	@Test
	public void testJSONNodesSkip() {
		ObjectNode myNode = new ObjectMapper().createObjectNode();
		myNode.putArray("users").addAll((ArrayNode)node);
		
		Query q=new Query(myNode);
		
		
		ArrayNode result = q.skip(1).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(1);
		
		assertThat(Query.q(result).value("0/address").textValue()).isEqualTo("2nd St.");
		
		result = q.skip(2).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(0);
		
		result = q.skip(0).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(2);
	}
	
	@Test
	public void testJSONNodesTop() {
		ObjectNode myNode = new ObjectMapper().createObjectNode();
		myNode.putArray("users").addAll((ArrayNode)node);
		
		Query q=new Query(myNode);
		
		
		ArrayNode result = q.top(1).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(1);
		
		assertThat(Query.q(result).value("0/address").textValue()).isEqualTo("1st St.");
		
		result = q.top(0).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(0);
		
		result = q.top(5).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(2);
	}
	
	@Test
	public void testJSONNodesSkipAndTop() {
		ObjectNode myNode = new ObjectMapper().createObjectNode();
		myNode.putArray("users").addAll((ArrayNode)node);
		
		Query q=new Query(myNode);
		
		
		ArrayNode result = q.skip(0).top(1).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(1);
		
		assertThat(Query.q(result).value("0/address").textValue()).isEqualTo("1st St.");
		
		result = q.skip(1).top(1).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(1);
		
		result = q.skip(5).top(5).filter("users", not(Null("address")));
		
		assertThat(result.size()).isEqualTo(0);
		
		
	}
	
	@Test
	public void testJSONNodesPagination() {
		ObjectNode myNode = new ObjectMapper().createObjectNode();
		myNode.putArray("users").addAll((ArrayNode)node);
		
		ArrayNode result = Query.q(myNode).filter("users", null);
		
		assertThat(result.size()).isEqualTo(3);
		
		result = Query.q(myNode).top(1).filter("users", null);
		
		assertThat(result.size()).isEqualTo(1);
		
		result = Query.q(myNode).skip(1).filter("users", null);
		
		assertThat(result.size()).isEqualTo(2);
		
		result = Query.q(myNode).top(1).skip(1).filter("users", null);
		
		assertThat(result.size()).isEqualTo(1);
	}
}
