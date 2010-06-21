package org.jl7.dsl;

import org.jl7.hl7.*;
import groovy.lang.Closure;

public class HL7 {
    public static HL7GroovyMessage message(HL7Message msg) {
        return new HL7GroovyMessage(msg);
    }

    public static HL7GroovyMessage message(HL7Message msg, Closure args) {
        HL7GroovyMessage groovyMessage = new HL7GroovyMessage(msg);
        args.call();
        return groovyMessage;
    }
}
