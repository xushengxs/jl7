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

    public HL7GroovyComponent call(int index) {
        return getAt(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7FieldRepetition) {
            return repetition.equals(obj);
        }
        else if (obj instanceof HL7GroovyFieldRepetition) {
            return repetition.equals((obj));
        }
        else if (obj instanceof String) {
            return repetition.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    public HL7GroovyComponent getAt(int index) {
        return new HL7GroovyComponent(repetition.getAt(index));
    }

    @Override
    public int hashCode() {
        return repetition.getValue().hashCode();
    }

    @Override
    public String toString() {
        return repetition.toString();
    }
}
