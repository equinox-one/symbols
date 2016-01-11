package com.mateuyabar.symbols.model;

/**
 * Model to store attribute data
 */
public class AttributeModel {
        String attributeName;

        public AttributeModel(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public String getStaticAttributeName(){
            return new CaseFormat().cammelCaseToSnakeCase(attributeName).toUpperCase();
        }
    }