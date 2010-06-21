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

    public String toString() {
        return subcomponent.toString();
    }

}
