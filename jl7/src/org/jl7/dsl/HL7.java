package org.jl7.dsl;

import groovy.lang.Closure;

import org.jl7.hl7.HL7Message;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7.
 */
public class HL7 {
    
    /**
     * Message.
     *
     * @param msg the msg
     * @return the h l7 groovy message
     */
    public static HL7GroovyMessage message(HL7Message msg) {
        return new HL7GroovyMessage(msg);
    }

    /**
     * Message.
     *
     * @param msg the msg
     * @param args the args
     * @return the h l7 groovy message
     */
    public static HL7GroovyMessage message(HL7Message msg, Closure args) {
        HL7GroovyMessage groovyMessage = new HL7GroovyMessage(msg);
        args.call();
        return groovyMessage;
    }
}
