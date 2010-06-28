/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Component;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovyComponent.
 *
 * @author henribenoit
 */
public class HL7GroovyComponent {
    
    /** The component. */
    private HL7Component component;

    /**
     * Instantiates a new h l7 groovy component.
     *
     * @param component the component
     */
    public HL7GroovyComponent(HL7Component component) {
        this.component = component;
    }

    /**
     * Call.
     *
     * @param index the index
     * @return the h l7 groovy subcomponent
     */
    public HL7GroovySubcomponent call(int index) {
        return getAt(index);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Component) {
            return component.equals(obj);
        }
        else if (obj instanceof HL7GroovyComponent) {
            return component.equals((obj));
        }
        else if (obj instanceof String) {
            return component.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7GroovySubcomponent getAt(int index) {
        return new HL7GroovySubcomponent(component.getAt(index));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return component.getValue().hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return component.toString();
    }
}
