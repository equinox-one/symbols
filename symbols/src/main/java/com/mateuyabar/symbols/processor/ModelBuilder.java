package com.mateuyabar.symbols.processor;


import com.mateuyabar.symbols.model.AttributeModel;
import com.mateuyabar.symbols.model.ClassModel;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.*;

/**
 * Extracts the information needed from the typeElements and creates a model to be used to generated the sources.
 */
public class ModelBuilder {
    Set<TypeElement> typeElementList = new HashSet<TypeElement>();
    Map<TypeElement, ClassModel> classModels;

    /**
     * Adds a new TypeElement for the build
     * @param typeElement
     */
    public void add(TypeElement typeElement){
        typeElementList.add(typeElement);
        //We want also to generate symbols for the parent class, even if not Symbolize annotated.
        TypeElement superTypeElement = getSuperClassTypeElement(typeElement);
        if(superTypeElement!=null){
            add(superTypeElement);
        }
    }

    /**
     * Generates a list of ClassModels with the added TypeElements
     * @return
     */
    public List<ClassModel> build(){
        classModels = new HashMap<TypeElement, ClassModel>();
        //Generate class Models
        for(TypeElement typeElement : typeElementList){
            ClassModel classModel = buildClassModel(typeElement);
            classModels.put(typeElement, classModel);
        }
        //add super-Models to Models
        for(TypeElement typeElement : typeElementList){
            ClassModel classModel = classModels.get(typeElement);
            TypeElement superTypeElement = getSuperClassTypeElement(typeElement);
            if(superTypeElement!=null){
                ClassModel superClassModel = classModels.get(superTypeElement);
                classModel.setSuperClassModel(superClassModel);
            }
        }

        return new ArrayList<ClassModel>(classModels.values());
    }

    /**
     * Returns the typeElement of the superclass or null if its Object.
     * @param typeElement class
     * @return superclass
     */
    private TypeElement getSuperClassTypeElement(TypeElement typeElement){
        if(!typeElement.getSuperclass().toString().equals(Object.class.getName())){
            return (TypeElement) ((DeclaredType) typeElement.getSuperclass()).asElement();
        } else {
            return null;
        }
    }

    /**
     * Creates a ClassModel given a typeElement
     * @param classElement origin
     * @return generated model
     */
    private ClassModel buildClassModel(TypeElement classElement){
        String modelClass = classElement.getSimpleName().toString();

        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
        String packageName = packageElement.getQualifiedName().toString();

        TypeMirror superClassTypeMirror = classElement.getSuperclass();
        String superClassName = superClassTypeMirror.toString();
        //NOTE: we can get info with typeUtils.asElement(superClassTypeMirror).getEnclosedElements();

        ClassModel data = new ClassModel(packageName, modelClass, superClassName);

        //Add attributes
        for(Element fieldElement:classElement.getEnclosedElements()){
            if(fieldElement.getKind() == ElementKind.FIELD){
                String attributeName = fieldElement.getSimpleName().toString();
                data.addAttribute(new AttributeModel(attributeName));
           }
        }

        return data;
    }
}
