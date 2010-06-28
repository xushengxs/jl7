/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Field;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovyField.
 *
 * @author henribenoit
 */
public class HL7GroovyField {
    
    /** The field. */
    private HL7Field field;

    /**
     * Instantiates a new h l7 groovy field.
     *
     * @param field the field
     */
    public HL7GroovyField(HL7Field field) {
        this.field = field;
    }

    /**
     * Call.
     *
     * @param index the index
     * @return the h l7 groovy component
     */
    public HL7GroovyComponent call(int index) {
        return new HL7GroovyFieldRepetition(field.getAt(1)).getAt(index);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Field) {
            return field.equals(obj);
        }
        else if (obj instanceof HL7GroovyField) {
            return field.equals((obj));
        }
        else if (obj instanceof String) {
            return field.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7GroovyFieldRepetition getAt(int index) {
        return new HL7GroovyFieldRepetition(field.getAt(index));
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return field.getValue();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return field.getValue().hashCode();
    }

    /**
     * Left shift.
     *
     * @param value the value
     * @return the h l7 groovy field
     */
    public HL7GroovyField leftShift(String value) {
        field.setRepetitionsWithoutDelimiters(value);
        return this;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return field.toString();
    }
}
