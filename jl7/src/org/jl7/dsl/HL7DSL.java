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

// TODO: Auto-generated Javadoc
/**
 * The Class HL7DSL.
 * 
 * @author henribenoit
 */
public class HL7DSL {

    /**
     * Convert message.
     * 
     * @param file
     *            the file
     * @param message
     *            the message
     * @return the h l7 groovy message
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static HL7GroovyMessage convertMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message_in", message);
        HL7GroovyMessage messageOut = new HL7GroovyMessage(new HL7Message());
        binding.setVariable("message_out", messageOut);
        processDsl(file, binding);
        return messageOut;
    }

    /**
     * Convert message.
     * 
     * @param file
     *            the file
     * @param message
     *            the message
     * @return the h l7 message
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static HL7Message convertMessage(File file, HL7Message message) throws IOException {
        return convertMessage(file, new HL7GroovyMessage(message)).getMsg();
    }

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        try {
            File dsl = new File(args[0]);
            File dsl2 = new File(args[1]);
            HL7GroovyMessage message = new HL7GroovyMessage(new HL7Message());
            message.leftShift("MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII");
            message.leftShift("EVN|A08|20060705000000");
            message.leftShift("PID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA");
            message.leftShift("PV1|1|u|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V");
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

    /**
     * Process dsl.
     * 
     * @param file
     *            the file
     * @param binding
     *            the binding
     * @return the object
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
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

    /**
     * Process message.
     * 
     * @param file
     *            the file
     * @param message
     *            the message
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static void processMessage(File file, HL7GroovyMessage message) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("message", message);
        processDsl(file, binding);
    }

    /**
     * Process message.
     * 
     * @param file
     *            the file
     * @param message
     *            the message
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void processMessage(File file, HL7Message message) throws IOException {
        processMessage(file, new HL7GroovyMessage(message));
    }

}
