package one.equinox.symbols.generation;

import one.equinox.symbols.model.ClassModel;
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
 * In charge of generating the new classes, given a model.
 */
public class ModelToJava {
    public void create(ProcessingEnvironment processingEnv, ClassModel classModel){
        try {
            Properties props = new Properties();
            URL url = ModelToJava.class.getClassLoader().getResource("velocity.properties");
            props.load(url.openStream());

            VelocityEngine ve = new VelocityEngine(props);
            ve.init();

            VelocityContext vc = new VelocityContext();
            vc.put("data", classModel);

            Template vt = ve.getTemplate("SymbolClass.vm");


            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(classModel.getGeneratedPackage()+"."+classModel.getGeneratedClassName());

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
