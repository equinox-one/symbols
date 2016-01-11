package com.mateuyabar.symbols.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of the data of a class
 */
public class ClassModel {
    String originPackageName;
    String originModelClass;
    List<AttributeModel> attributes = new ArrayList<AttributeModel>();
    String supperClassName;
    ClassModel superClassModel;

    public ClassModel(String originPackageName, String originModelClass, String supperClassName) {
        this.originPackageName = originPackageName;
        this.originModelClass = originModelClass;
        this.supperClassName = supperClassName;
    }


    public void addAttribute(AttributeModel attribute){
        attributes.add(attribute);
    }

    public String getGeneratedClassName(){
        return originModelClass +"Symbols";
    }

    public String getGeneratedPackage(){
        return originPackageName+".symbols";
    }

    public String getGeneratedFullName(){
        return getGeneratedPackage()+"."+getGeneratedClassName();
    }

    public String getOriginFullName(){
        return getOriginPackageName()+"."+getOriginModelClass();
    }

    public String getOriginModelClass() {
        return originModelClass;
    }

    public List<AttributeModel> getOwnAttributes() {
        return attributes;
    }

    public List<AttributeModel> getAttributes() {
        List<AttributeModel> result = new ArrayList<AttributeModel>(getOwnAttributes());
        if(getSuperClassModel()!=null){
            result.addAll(getSuperClassModel().getAttributes());
        }
        return result;
    }

    public String getOriginPackageName() {
        return originPackageName;
    }

    public String getSupperClassName() {
        return supperClassName;
    }

    public ClassModel getSuperClassModel() {
        return superClassModel;
    }

    public void setSuperClassModel(ClassModel superClassModel) {
        this.superClassModel = superClassModel;
    }
}
