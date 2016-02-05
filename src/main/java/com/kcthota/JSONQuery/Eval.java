package com.kcthota.JSONQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;
import com.kcthota.JSONQuery.exceptions.MissingNodeException;

public class Eval {
	private static ScriptEngineManager manager = new ScriptEngineManager();
    private static ScriptEngine engine = manager.getEngineByName("nashorn");
    
    private static Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
    
	public static Object eval(JsonNode jsonNode, String expression) {
		String formattedExpression = replaceTokens(jsonNode, expression);
		Object returnVal = null;
		try {
			returnVal =  engine.eval(formattedExpression);
		} catch (ScriptException e) {
			//logging
		}
		return returnVal;
	}
	
	private static String replaceTokens(JsonNode jsonNode, String expression) {
		if (expression == null) {
			return expression;
		}
		Query query=Query.q(jsonNode);
		Matcher matcher = pattern.matcher(expression);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String token = matcher.group(1);
			try {
			String tokenValue = query.value(token).asText();
			matcher.appendReplacement(sb, tokenValue == null ? token
					: tokenValue);
			} catch(MissingNodeException e) {
				continue;
			}
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
