/**
 *
 */
package org.jl7.dsl;

import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;
import org.jl7.hl7.HL7Message;

/**
 * @author henribenoit
 *
 */
public class HL7DSL {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            File dsl = new File(args[0]);
            HL7GroovyMessage message = new HL7GroovyMessage(new HL7Message());
            processMessage(dsl, message);
//            System.out.println(convertMessage(dsl, message));
        }
        catch (CompilationFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void processMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message", message);
        processDsl(file, binding);
    }

    public static HL7GroovyMessage convertMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message_in", message);
        HL7GroovyMessage messageOut = new HL7GroovyMessage(new HL7Message());
        binding.setVariable("message_out", messageOut);
        processDsl(file, binding);
        return messageOut;
    }

    private static Object processDsl(File file, Binding binding) throws IOException {
        GroovyShell shell = new GroovyShell(binding);

        GroovyCodeSource code = new GroovyCodeSource(file);

        StringBuilder builder = new StringBuilder();
        builder.append("import static org.jl7.hl7.HL7Parser.*;\n");
        builder.append("import org.jl7.dsl.*;\n");
        builder.append(code.getScriptText());

        Script dslScript = shell.parse(builder.toString());
        return dslScript.run();
    }

}
