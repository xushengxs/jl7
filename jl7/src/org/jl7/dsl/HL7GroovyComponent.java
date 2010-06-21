/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Component;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyComponent {
    private HL7Component component;

    public HL7GroovyComponent(HL7Component component) {
        this.component = component;
    }

    public String toString() {
        return component.toString();
    }

    public HL7GroovySubcomponent getAt(int index) {
        return new HL7GroovySubcomponent(component.getAt(index));
    }

    public HL7GroovySubcomponent call(int index) {
        return getAt(index);
    }
}
