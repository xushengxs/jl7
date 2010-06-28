/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7FieldRepetition;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovyFieldRepetition.
 *
 * @author henribenoit
 */
public class HL7GroovyFieldRepetition {
    
    /** The repetition. */
    private HL7FieldRepetition repetition;

    /**
     * Instantiates a new h l7 groovy field repetition.
     *
     * @param repetition the repetition
     */
    public HL7GroovyFieldRepetition(HL7FieldRepetition repetition) {
        this.repetition = repetition;
    }

    /**
     * Call.
     *
     * @param index the index
     * @return the h l7 groovy component
     */
    public HL7GroovyComponent call(int index) {
        return getAt(index);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7GroovyComponent getAt(int index) {
        return new HL7GroovyComponent(repetition.getAt(index));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return repetition.getValue().hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return repetition.toString();
    }
}
