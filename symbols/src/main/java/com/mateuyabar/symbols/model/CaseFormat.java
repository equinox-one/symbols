package com.mateuyabar.symbols.model;

/**
 * Case formatinc functions
 */
public class CaseFormat {

	public String cammelCaseToSnakeCase(String cammelCase){
		String regex = "([a-z])([A-Z]+)";
	    String replacement = "$1_$2";
	    return cammelCase.replaceAll(regex, replacement).toLowerCase();
	}
	
	public String firstLetterToLowerCase(String string){
		String firstLetter = string.substring(0,1).toLowerCase();
		String restLetters = string.substring(1);
		return firstLetter + restLetters;
	}

}