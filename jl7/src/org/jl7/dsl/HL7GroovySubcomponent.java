/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Subcomponent;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovySubcomponent.
 *
 * @author henribenoit
 */
public class HL7GroovySubcomponent {
    
    /** The subcomponent. */
    private HL7Subcomponent subcomponent;

    /**
     * Instantiates a new h l7 groovy subcomponent.
     *
     * @param subcomponent the subcomponent
     */
    public HL7GroovySubcomponent(HL7Subcomponent subcomponent) {
        this.subcomponent = subcomponent;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Subcomponent) {
            return subcomponent.equals(obj);
        }
        else if (obj instanceof HL7GroovySubcomponent) {
            return subcomponent.equals((obj));
        }
        else if (obj instanceof String) {
            return subcomponent.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return subcomponent.getValue().hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return subcomponent.toString();
    }
}
