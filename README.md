# JSONQuery [![Build Status](https://travis-ci.org/kcthota/JSONQuery.svg?branch=master)](https://travis-ci.org/kcthota/JSONQuery)

Query JsonNode objects with intuitive expressions.

##Usage
Stable Release:

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>JSONQuery</artifactId>
	<version>0.0.3</version>
</dependency>
```
Latest Snapshot Release:

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>JSONQuery</artifactId>
	<version>0.0.4-SNAPSHOT</version>
</dependency>
```

##Example:
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

###is
Checks if the passed in expression is true for the object.

**Equals**
```
Query.q(node).is(eq("state", "CA")); //returns true

Query.q(node).is(eq("name/first", "Jane")); //returns false

Query.q(node).is(eq("name/last", "Doe")); //returns true

Query.q(node).is(eq("interests/0", "hiking")); //returns true

Query.q(node).is(eq(appendTo("state", ", USA"), "CA, USA")); //returns true

```

**Not Equals**

```
Query.q(node).is(ne("state", "CA")); //returns false

Query.q(node).is(ne("name/first", "Jane")); //returns true

Query.q(node).is(ne("age", 35)); //returns true

Query.q(node).is(ne(prependTo("state", "1"), "CA")); //returns true

```

**Greater than**

```
Query.q(node).is(gt("age", 31)); //returns false
```

**Less than**

```
Query.q(node).is(lt("age", 32)); //returns true
```

**Greater than or Equal to**

```
Query.q(node).is(ge("age", 31)); //returns true
```

**Less than or Equal to**

```
Query.q(node).is(le("age", 32)); //returns true
```

**Null**

```
Query.q(node).is(Null("state")); //returns false
```

**Not**

```
Query.q(node).is(not(eq("state", "CA"))); //returns false

Query.q(node).is(not(eq("name/first", "Jane"))); //returns true

Query.q(node).is(not(eq("name/last", "Doe"))); //returns false

Query.q(node).is(not(Null("state"))); //returns true
```

**AND**

```
Query.q(node).is(and(eq("name/first", "John"), eq("age",31))); //returns true

Query.q(node).is(and(eq("name/first", "John"), ne("age",31))); //returns false
```

**OR**

```
Query.q(node).is(or(eq("name/first", "John"), eq("age",31))); //returns true

Query.q(node).is(or(eq("name/first", "John"), ne("age",31))); //returns true
```

**Multiple AND/OR**

```
Query.q(node).is(or(and(eq("name/first", "John"), eq("age",31)), eq("state", "FL"))); //returns true

Query.q(node).is(and(and(eq("name/first", "John"), eq("age",31)), eq("state", "FL"))); //returns false
```

**Substring Of**

```
Query.q(node).is(substringof("name/first", "Jo")); //returns true

Query.q(node).is(substringof("age", "Jo")); //returns false, age value is not string
```

**Starts With**

```
Query.q(node).is(startsWith("name/first", "Jo")); //returns true

Query.q(node).is(startsWith("age", "Jo")); //returns false, age value is not string
```

**Ends With**

```
Query.q(node).is(endsWith("name/first", "Jo")); //returns false

Query.q(node).is(endsWith("age", "Jo")); //returns false, age value is not string
```

###isExist
Checks if property exist for the JsonNode.

```
Query.q(node).isExist("age"); //returns true

Query.q(node).isExist("name1"); //returns false

Query.q(node).isExist("name/middle"); //returns false

```

###value
Retrieves the value of a property in the JsonNode

**as is**

```
Query.q(node).value("name/first").textValue(); //returns John

Query.q(node).value("name"); //returns the JsonNode object for name.

Query.q(node).value("city"); //throws MissingNodeException

Query.q(node).value("interests/0").textValue(); //returns hiking

```

**Append to**

```
Query.q(node).value(appendTo("name/first", "K")).textValue(); //returns JohnK

Query.q(node).value(appendTo("interests/0", " hills")).textValue(); //returns hiking hills

Query.q(node).value(appendTo("age", "K")); //throws UnsupportedExprException, when appending to non-string values

```

**Prepend to**

```
Query.q(node).value(prependTo("name/first", "K")).textValue(); //returns KJohn

Query.q(node).value(prependTo("interests/0", "hill ")).textValue(); //returns hill hiking

Query.q(node).value(prependTo("age", "K")); //throws UnsupportedExprException, when appending to non-string values

```

**Trim**

```
Query.q(node).value(trim("name/first")).textValue(); //returns John

Query.q(node).value(trim("interests/0")).textValue(); //returns hiking

Query.q(node).value(trim("age")); //throws UnsupportedExprException, when appending to non-string values

```

**To Upper case**

```
Query.q(node).value(upper("name/first")).textValue(); //returns JOHN

Query.q(node).value(upper("interests/0")).textValue(); //returns HIKING

Query.q(node).value(upper("age")); //throws UnsupportedExprException, when appending to non-string values

```

**To Lower case**

```
Query.q(node).value(lower("name/first")).textValue(); //returns john

Query.q(node).value(lower("interests/0")).textValue(); //returns hiking

Query.q(node).value(lower("age")); //throws UnsupportedExprException, when appending to non-string values

```

**Replace**

```
Query.q(node).value(replace("name/first", "John", "Jane")).textValue(); //returns Jane

Query.q(node).value(replace("interests/0", "hik", "bik")).textValue(); //returns biking

Query.q(node).value(replace("interests/0", "zzz", "")).textValue(); //unchanged without match, returns hiking

Query.q(node).value(lower("age")); //throws UnsupportedExprException, when appending to non-string values

```

**Substring**

```
Query.q(node).value(substring("name/first", 1)).textValue(); //returns ohn

Query.q(node).value(substring("interests/0", 0, 2)).textValue(); //returns hi

Query.q(node).value(substring("interests/0", 2, 100)).textValue(); //caps to max length and returns king

Query.q(node).value(lower("age")); //throws UnsupportedExprException, when appending to non-string values

```

**length**

```

Query.q(node).value(length("name/firstName")); //returns 4; len(John)

Query.q(node).value(length(null)); //returns 4, number of fields in object node

Query.q(node).value(length("interests")); //returns 2, number of elements in array

Query.q(node).value(length("age")); //returns 0, if property value is not an array, object or string

```

**indexof**

```

Query.q(node).value(indexof("name/firstName", "J")); //returns 0

Query.q(node).value(indexof("name/firstName", null)); //returns -1, indexof str is null

Query.q(node).value(indexof("interests/0", "iking")); //returns 1

Query.q(node).value(indexof("interests", "hiking")); //returns -1, interests is an array, not a string

Query.q(node).value(indexof("age", "24")); //returns -1, property value is not string

```

**Value Expression Combinations**

```
Query.q(node).value(val(val("name"),"first")).textValue(); //returns John

Query.q(node).value(appendTo(prependTo("name/first", "K"),"C")).textValue(); //returns KJohnC

Query.q(node).value(appendTo(prependTo("interests/0", "hill ")," in CA")).textValue(); //returns hill hiking in CA

```

## Filter

Allows filtering objects in an ArrayNode as per passed in expression

```
Query.q(node).filter("interests", eq((String)null, "hiking"); //returns a new ArrayNode, ["hiking"]

Query.q(node).filter("interests", ne((String)null, "hiking"); //returns ["biking"]

Query.q(node).filter("interests", or(eq((String)null, "biking"), eq((String)null, "hiking"))); //returns ["hiking", "biking"]

Query.q(ArrayNode).filter(not(Null("address")); //returns array of objects with address property not null

Query.q(node).filter("interests", or(eq((String)null, "biking"), eq((String)null, "hiking"))).value("0").textValue(); //returns hiking

```

##Filter Pagination

```
Query.q(node).skip(1).filter("interests", eq((String)null, "hiking"); //skips the first result

Query.q(node).top(1).filter("interests", eq((String)null, "hiking"); //retuns the first result

Query.q(node).skip(2).top(1).filter("interests", eq((String)null, "hiking"); //returns the first result, skipping 2 objects from top

```

Refer [unit tests] (https://github.com/kcthota/JSONQuery/tree/master/src/test/java/com/kcthota/query) for more examples.

<!--
###Only in SNAPSHOT release

Following features are newly added and available in latest snapshot release
-->

## License:

Copyright 2015 Krishna Chaitanya Thota (kcthota@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.