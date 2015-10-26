package com.mateuyabar.symbols.processor;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.net.URL;
import java.util.Properties;

/**
 * Created by mateuyabar on 23/04/15.
 */
public class SymbolVelocityGenerator {
    public static void create(ProcessingEnvironment processingEnv, String fqClassName, SymbolProcessorModel velocitydata){
        try {
            Properties props = new Properties();
            URL url = SymbolVelocityGenerator.class.getClassLoader().getResource("velocity.properties");
            props.load(url.openStream());

            VelocityEngine ve = new VelocityEngine(props);
            ve.init();

            VelocityContext vc = new VelocityContext();
            vc.put("data", velocitydata);

            Template vt = ve.getTemplate("SymbolClass.vm");


            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(velocitydata.getGeneratedPackage()+"."+velocitydata.getGeneratedClassName());

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating source file: " + jfo.toUri());
            Writer writer = jfo.openWriter();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "applying velocity template: " + vt.getName());

            vt.merge(vc, writer);

            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
