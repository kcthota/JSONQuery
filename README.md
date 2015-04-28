# JSONQuery [![Build Status](https://travis-ci.org/kcthota/JSONQuery.svg?branch=master)](https://travis-ci.org/kcthota/JSONQuery)

Query JsonNode objects with intuitive expressions.

##Usage
Stable Release:

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>JSONQuery</artifactId>
	<version>0.0.2</version>
</dependency>
```
Latest Snapshot Release:

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>JSONQuery</artifactId>
	<version>0.0.3-SNAPSHOT</version>
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
new Query(node).is(eq("state", "CA")); //returns true

new Query(node).is(eq("name/first", "Jane")); //returns false

new Query(node).is(eq("name/last", "Doe")); //returns true

new Query(node).is(eq("interests/0", "hiking")); //returns true

new Query(node).is(eq(appendTo("state", ", USA"), "CA, USA")); //returns true

```

**Not Equals**

```
new Query(node).is(ne("state", "CA")); //returns false

new Query(node).is(ne("name/first", "Jane")); //returns true

new Query(node).is(ne("age", 35)); //returns true

new Query(node).is(ne(prependTo("state", "1"), "CA")); //returns true

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

new Query(node).is(not(eq("name/first", "Jane"))); //returns true

new Query(node).is(not(eq("name/last", "Doe"))); //returns false

new Query(node).is(not(Null("state"))); //returns true
```

**AND**

```
new Query(node).is(and(eq("name/first", "John"), eq("age",31))); //returns true

new Query(node).is(and(eq("name/first", "John"), ne("age",31))); //returns false
```

**OR**

```
new Query(node).is(or(eq("name/first", "John"), eq("age",31))); //returns true

new Query(node).is(or(eq("name/first", "John"), ne("age",31))); //returns true
```

**Multiple AND/OR**

```
new Query(node).is(or(and(eq("name/first", "John"), eq("age",31)), eq("state", "FL"))); //returns true

new Query(node).is(and(and(eq("name/first", "John"), eq("age",31)), eq("state", "FL"))); //returns false
```

**Substring Of**

```
new Query(node).is(substringof("name/first", "Jo")); //returns true

new Query(node).is(substringof("age", "Jo")); //returns false, age value is not string
```

**Starts With**

```
new Query(node).is(startsWith("name/first", "Jo")); //returns true

new Query(node).is(startsWith("age", "Jo")); //returns false, age value is not string
```

**Ends With**

```
new Query(node).is(endsWith("name/first", "Jo")); //returns false

new Query(node).is(endsWith("age", "Jo")); //returns false, age value is not string
```

###isExist
Checks if property exist for the JsonNode.

```
new Query(node).isExist("age"); //returns true

new Query(node).isExist("name1"); //returns false

new Query(node).isExist("name/middle"); //returns false

```

###value
Retrieves the value of a property in the JsonNode

**as is**

```
new Query(node).value("name/first").textValue(); //returns John

new Query(node).value("name"); //returns the JsonNode object for name.

new Query(node).value("city"); //throws MissingNodeException

new Query(node).value("interests/0").textValue(); //returns hiking

```

**Append to**

```
new Query(node).value(appendTo("name/first", "K")).textValue(); //returns JohnK

new Query(node).value(appendTo("interests/0", " hills")).textValue(); //returns hiking hills

new Query(node).value(appendTo("age", "K")); //throws UnsupportedExprException, when appending to non-string values

```

**Prepend to**

```
new Query(node).value(prependTo("name/first", "K")).textValue(); //returns KJohn

new Query(node).value(prependTo("interests/0", "hill ")).textValue(); //returns hill hiking

new Query(node).value(prependTo("age", "K")); //throws UnsupportedExprException, when appending to non-string values

```

**Value Expression Combinations**

```
new Query(node).value(val(val("name"),"first")).textValue(); //returns John

new Query(node).value(appendTo(prependTo("name/first", "K"),"C")).textValue(); //returns KJohnC

new Query(node).value(appendTo(prependTo("interests/0", "hill ")," in CA")).textValue(); //returns hill hiking in CA

```


Refer [unit tests] (https://github.com/kcthota/JSONQuery/tree/master/src/test/java/com/kcthota/query) for more examples.

###Only in SNAPSHOT release
Following features are newly added and available in latest snapshot release

**Trim**

```
new Query(node).value(trim("name/first")).textValue(); //returns John

new Query(node).value(trim("interests/0")).textValue(); //returns hiking

new Query(node).value(trim("age")); //throws UnsupportedExprException, when appending to non-string values

```

**To Upper case**

```
new Query(node).value(upper("name/first")).textValue(); //returns JOHN

new Query(node).value(upper("interests/0")).textValue(); //returns HIKING

new Query(node).value(upper("age")); //throws UnsupportedExprException, when appending to non-string values

```

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