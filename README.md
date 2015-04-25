# JSONQuery [![Build Status](https://travis-ci.org/kcthota/JSONQuery.svg?branch=master)](https://travis-ci.org/kcthota/JSONQuery)

Query JsonNode objects with intuitive expressions

#Example:
Consider the following JSON object.

```
{
   "name":{
      "first":"John",
      "last":"Doe"
   },
   "age":31,
   "interests":["hiking","biking"]
   "state":"CA"
}
```

##is
Checks if the passed in expression is true for the object.

**Equals**
```
new Query(node).is(eq("state", "CA")); //returns true

new Query(node).is(eq("name.first", "Jane")); //returns false

new Query(node).is(eq("name.last", "Doe")); //returns true
```

**Not Equals**

```
new Query(node).is(ne("state", "CA")); //returns false

new Query(node).is(ne("name.first", "Jane")); //returns true

new Query(node).is(ne("age", 35)); //returns true
```

**Greater than**

```
new Query(node).is(gt("age", 31)); //returns false
```

**Less than**

```
new Query(node).is(lt("age", 32)); //returns true
```

**Greater than or Equal to**

```
new Query(node).is(ge("age", 31)); //returns true
```

**Less than or Equal to**

```
new Query(node).is(le("age", 32)); //returns true
```

**Null**

```
new Query(node).is(Null("state")); //returns false
```

**Not**

```
new Query(node).is(not(eq("state", "CA"))); //returns false

new Query(node).is(not(eq("name.first", "Jane"))); //returns true

new Query(node).is(not(eq("name.last", "Doe"))); //returns false

new Query(node).is(not(Null("state"))); //returns true
```

**AND**

```
new Query(node).is(and(eq("name.first", "John"), eq("age",31))); //returns true

new Query(node).is(and(eq("name.first", "John"), ne("age",31))); //returns false
```

**OR**

```
new Query(node).is(or(eq("name.first", "John"), eq("age",31))); //returns true

new Query(node).is(or(eq("name.first", "John"), ne("age",31))); //returns true
```

**Multiple AND/OR**

```
new Query(node).is(or(and(eq("name.first", "John"), eq("age",31)), eq("state", "FL"))); //returns true

new Query(node).is(and(and(eq("name.first", "John"), eq("age",31)), eq("state", "FL"))); //returns false
```

##isExist
Checks if property exist for the JsonNode.

```
new Query(node).isExist("age"); //returns true

new Query(node).isExist("name1"); //returns false

new Query(node).isExist("name.middle"); //returns false

```

##value
Retrieves the value for a property in the JsonNode

```
new Query(node).value("name.first").textValue(); //returns John

new Query(node).value("name"); //returns the JsonNode object for name.

new Query(node).value("city"); //throws MissingNodeException
```