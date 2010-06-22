/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Field;
import org.jl7.hl7.HL7Parser;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyField {
    private HL7Field field;

    public HL7GroovyField(HL7Field field) {
        this.field = field;
    }

    public String toString() {
        return field.toString();
    }

    public HL7GroovyFieldRepetition getAt(int index) {
        return new HL7GroovyFieldRepetition(field.getAt(index));
    }

    public HL7GroovyComponent call(int index) {
        return new HL7GroovyFieldRepetition(field.getAt(1)).getAt(index);
    }

    public HL7GroovyField leftShift(String value) {
        field.setRepetitionsWithoutDelimiters(value);
        return this;
    }
}
