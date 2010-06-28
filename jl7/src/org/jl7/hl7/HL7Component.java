package org.jl7.hl7;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Represents an HL7 component.
 *
 * @since 0.1
 * @author henribenoit
 */
public class HL7Component {
    /**
     * Character used to mark the end of a subcomponent and the beginning of the
     * next subcomponent (i.e. subcomponent delimiter). Default: &
     * 
     * @since 0.1
     */
    public char subcomponentSeparator;
    /**
     * Character used to escape characters which would otherwise be interpreted.
     * Default: \
     * 
     * @since 0.1
     */
    public char escapeCharacter;
    /**
     * List of all subcomponents in this component.
     */
    protected ArrayList<HL7Subcomponent> subcomponents = new ArrayList<HL7Subcomponent>();

    /**
     * Returns the subcomponent at the given position.
     * 
     * @param index
     *            position in the component of the subcomponent to be returned
     * 
     * @return the subcomponent at the given position
     * 
     * @since 0.1
     */
    public HL7Subcomponent get(int index) {
        if (index < subcomponents.size()) {
            return subcomponents.get(index);
        }
        else {
            return null;
        }
    }

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7Subcomponent getAt(int index) {
        return get(index - 1);
    }

    /**
     * Returns the string representation of this component where all escape
     * sequences have been replaced by their values.
     * 
     * @return the string representation of this component where all escape
     *         sequences have been replaced by their values
     * 
     * @since 0.1
     */
    public String getValue() {
        String s = null;
        for (HL7Subcomponent subcomponent : subcomponents) {
            if (s == null) {
                s = subcomponent.getValue();
            }
            else {
                s = s + subcomponentSeparator + subcomponent.getValue();
            }
        }
        return s;
    }

    /**
     * Sets the delimiters.
     *
     * @param value the new delimiters
     */
    public void setDelimiters(String value) {
        escapeCharacter = value.charAt(3);
        subcomponentSeparator = value.charAt(4);
    }

    /**
     * Sets all delimiters defined for this component.
     *
     * @param subComponents the sub components
     * @param delimiters the delimiters
     * @param escapesInSubcomponents the escapes in subcomponents
     * @since 0.1
     */
    public void setSubcomponents(String[] subComponents, String delimiters, boolean escapesInSubcomponents) {
        for (String subComponent : subComponents) {
            subcomponents.add(HL7Parser.parseSubcomponent(subComponent, delimiters, escapesInSubcomponents));
        }
    }

    /**
     * Removes all subcomponents from this component and adds the given
     * subcomponents to it.
     *
     * @param hl7String the new subcomponents without delimiters
     * @since 0.1
     */
    public void setSubcomponentsWithoutDelimiters(String hl7String) {
        subcomponents.add(HL7Parser.parseSubcomponentWithoutDelimiters(hl7String));
    }

    /**
     * Returns the string representation of this component.
     *
     * @return the string
     * @see java.lang.Object#toString()
     * @since 0.1
     */
    @Override
    public String toString() {
        String s = null;
        for (HL7Subcomponent subcomponent : subcomponents) {
            if (s == null) {
                s = subcomponent.toString();
            }
            else {
                s = s + subcomponentSeparator + subcomponent.toString();
            }
        }
        return s;
    }
}
