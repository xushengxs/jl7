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

    private static HL7GroovyMessage convertMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message_in", message);
        HL7GroovyMessage messageOut = new HL7GroovyMessage(new HL7Message());
        binding.setVariable("message_out", messageOut);
        processDsl(file, binding);
        return messageOut;
    }

    public static HL7Message convertMessage(File file, HL7Message message) throws IOException {
        return convertMessage(file, new HL7GroovyMessage(message)).getMsg();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            File dsl = new File(args[0]);
            File dsl2 = new File(args[1]);
            HL7GroovyMessage message = new HL7GroovyMessage(new HL7Message());
            message
                    .leftShift("MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII");
            message.leftShift("EVN|A08|20060705000000");
            message
                    .leftShift("PID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA");
            message
                    .leftShift("PV1|1|a|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V");
            message.leftShift("PV2|||||||1||||||||||||||||^^^^^^^^^103");
            message.leftShift("ROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1");
            message.leftShift("PR1|1||1||20060705|1");
            message.leftShift("GT1|1||||||||||||||||||||NOT APPLICABLE");

            processMessage(dsl2, message);
            System.out.println();
            System.out.println("----------");
            System.out.println();
            System.out.println(convertMessage(dsl, message).getMsg().getValue());
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

    private static void processMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message", message);
        processDsl(file, binding);
    }

    public static void processMessage(File file, HL7Message message) throws IOException {
        processMessage(file, new HL7GroovyMessage(message));
    }

}
