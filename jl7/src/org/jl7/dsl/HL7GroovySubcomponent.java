/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Subcomponent;

/**
 * @author henribenoit
 * 
 */
public class HL7GroovySubcomponent {
    private HL7Subcomponent subcomponent;

    public HL7GroovySubcomponent(HL7Subcomponent subcomponent) {
        this.subcomponent = subcomponent;
    }

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

    @Override
    public int hashCode() {
        return subcomponent.getValue().hashCode();
    }

    @Override
    public String toString() {
        return subcomponent.toString();
    }
}
