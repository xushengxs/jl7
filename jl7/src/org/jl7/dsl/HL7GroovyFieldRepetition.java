/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7FieldRepetition;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyFieldRepetition {
    private HL7FieldRepetition repetition;

    public HL7GroovyFieldRepetition(HL7FieldRepetition repetition) {
        this.repetition = repetition;
    }

    public String toString() {
        return repetition.toString();
    }

    public HL7GroovyComponent getAt(int index) {
        return new HL7GroovyComponent(repetition.getAt(index));
    }

    public HL7GroovyComponent call(int index) {
        return getAt(index);
    }
}
