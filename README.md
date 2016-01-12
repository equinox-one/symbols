# Symbols
Symbols is a Java library to generate static strings constants for attribute names using annotation processor.

# Tell me more
In some cases, in Java (ex: when using reflection methods) we need to refer to class attributes. To do so we usually create static strings of the names of the attributes. This is boiler-plate code and its an origin of problems (ex: when changing attributes names). FritterFactory helps to solve this problem.

# Show me the codes!
Lets imagine that we have the following POJO, that we annotate with Symbolize.
```java
@Symbolize
public class Person {
    String name;
    String surname;
    int age;
}
```

Symbols will (using annotation processing) generate the following class for you:
```java
public class PersonSymbols {
	public static final String CLASS_NAME= "$data.tableName";

	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String AGE = "age";


	public static final String[] ATTRIBUTES = {
	        		NAME ,
    	    		SURNAME ,
    	    		AGE
    		};
}
```

# Why should I use this?
This library is mainly to be used by others for reflection functionallities, however it can also be used, for example to define a database for a model. Where you use it is up to you

## Where is being used
 - [FritterFactory]: Library to automatically populate models, to be used in automatic tests or user made tests.

# Android friendly
Symbols is Android Friendly

License
=======

     Copyright 2016 Equinox.one

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.



[FritterFactory]: https://github.com/equinox-one/fritterfactory
