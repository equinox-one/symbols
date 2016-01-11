package com.mateuyabar.symbols;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicates that the class will be sybolized.
 * That means that a new class will be created that will contain static names of the attributes of the class.
 *
 * Use only in classes.
 */
@Target(ElementType.TYPE)
public @interface Symbolize {
}
