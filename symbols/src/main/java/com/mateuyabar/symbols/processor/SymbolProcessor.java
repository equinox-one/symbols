package com.mateuyabar.symbols.processor;

import com.google.auto.service.AutoService;
import com.mateuyabar.symbols.Symbolize;
import com.mateuyabar.symbols.generation.ModelToJava;
import com.mateuyabar.symbols.model.ClassModel;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;



/**
 * Processor that created Symbol classes
 *
 * NOTE: For debuging run mvnDebug clean install and attach debug
 */
@AutoService(Processor.class)
public class SymbolProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment env) {
        ModelBuilder modelBuilder = new ModelBuilder();
        //Create a Class model for each class witht he SybolClass annotation
        for(Element el:env.getElementsAnnotatedWith(Symbolize.class)){
            TypeElement typeElement = (TypeElement) el;
            modelBuilder.add(typeElement);
        }
        
        //Generate new source files
        for(ClassModel classModel: modelBuilder.build()) {
            new ModelToJava().create(processingEnv, classModel);
        }
        
        return false;
    }
    


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Symbolize.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


}
