package com.mateuyabar.symbols.processor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateuyabar on 23/04/15.
 */
public class SymbolProcessorModel {
    String originPackageName;
    String tableName;
    String originModelClass;
    List<Attribute> attributes = new ArrayList<Attribute>();

    public SymbolProcessorModel(String originPackageName, String originModelClass, String tableName) {
        this.originPackageName = originPackageName;
        this.originModelClass = originModelClass;
        this.tableName = tableName;
    }

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);
    }

    public String getTableName() {
        return tableName;
    }

    public String getGeneratedClassName(){
        return originModelClass +"Symbols";
    }

    public String getGeneratedPackage(){
        return originPackageName+".symbols";
    }

    public String getOriginModelClass() {
        return originModelClass;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public String getOriginPackageName() {
        return originPackageName;
    }

    public static class Attribute{
        String attributeName;

        public Attribute(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public String getStaticAttributeName(){
            return new CaseFormat().cammelCaseToSnakeCase(attributeName).toUpperCase();
        }
    }
}
