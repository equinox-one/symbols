package com.mateuyabar.symbols.processor;

import com.google.auto.service.AutoService;
import com.mateuyabar.symbols.SymbolClass;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.Set;


/**
 * Created by mateuyabar on 22/04/15.
 */
// For debuging: -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
@AutoService(Processor.class)
public class SymbolProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment env) {
        for(Element el:env.getElementsAnnotatedWith(SymbolClass.class)){
            SymbolProcessorModel velocitydata = SymbolModelBuilder.build((TypeElement) el);
            SymbolVelocityGenerator.create(processingEnv, ((TypeElement) el).getQualifiedName().toString(), velocitydata);

            if(true)
                return false;

        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(SymbolClass.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


}
