/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Field;

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

    public String getValue() {
        return field.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Field)
        {
            return field.equals(obj);
        }
        else if (obj instanceof HL7GroovyField) {
            return field.equals(((HL7Field)obj));
        }
        else if (obj instanceof String) {
            return field.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return field.getValue().hashCode();
    }
}
