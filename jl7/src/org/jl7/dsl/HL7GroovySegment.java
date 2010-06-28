/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Segment;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovySegment.
 *
 * @author henribenoit
 */
public class HL7GroovySegment {
    
    /** The segment. */
    HL7Segment segment;

    /**
     * Instantiates a new h l7 groovy segment.
     *
     * @param segment the segment
     */
    public HL7GroovySegment(HL7Segment segment) {
        this.segment = segment;
    }

    /**
     * Call.
     *
     * @param index the index
     * @return the h l7 groovy field
     */
    public HL7GroovyField call(int index) {
        return new HL7GroovyField(segment.getAt(index));
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Segment) {
            return segment.equals(obj);
        }
        else if (obj instanceof HL7GroovySegment) {
            return segment.equals((obj));
        }
        else if (obj instanceof String) {
            return segment.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7GroovyField getAt(int index) {
        return new HL7GroovyField(segment.getAt(index));
    }

    /**
     * Gets the segment.
     *
     * @return the segment
     */
    public HL7Segment getSegment() {
        return segment;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return segment.getValue().hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return segment.toString();
    }
}
