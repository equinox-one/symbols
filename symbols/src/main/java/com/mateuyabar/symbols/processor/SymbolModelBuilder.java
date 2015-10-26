package com.mateuyabar.symbols.processor;



import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;

/**
 * Created by mateuyabar on 23/04/15.
 */
public class SymbolModelBuilder {
    public static SymbolProcessorModel build(TypeElement classElement){
        String modelClass = classElement.getSimpleName().toString();
        String tableName = modelClass;

        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
        String packageName = packageElement.getQualifiedName().toString();

        SymbolProcessorModel data = new SymbolProcessorModel(packageName, modelClass, tableName);
        for(Element fieldElement:classElement.getEnclosedElements()){
            if(fieldElement.getKind() == ElementKind.FIELD){
                String attributeName = fieldElement.getSimpleName().toString();
                data.addAttribute(new SymbolProcessorModel.Attribute(attributeName));
           }
        }

        return data;
    }
}
