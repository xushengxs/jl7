/**
 *
 */
package org.jl7.dsl;

import java.util.List;

import org.jl7.hl7.HL7Segment;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovySegments.
 *
 * @author henribenoit
 */
public class HL7GroovySegments {
    
    /** The segments. */
    private List<HL7Segment> segments;

    /**
     * Instantiates a new h l7 groovy segments.
     *
     * @param segments the segments
     */
    public HL7GroovySegments(List<HL7Segment> segments) {
        this.segments = segments;
    }

    /**
     * Call.
     *
     * @param index the index
     * @return the h l7 groovy field
     */
    public HL7GroovyField call(int index) {
        HL7GroovySegment segment = new HL7GroovySegment(segments.get(0));
        return segment.getAt(index);
    }

    /**
     * Gets the at.
     *
     * @param index the index
     * @return the at
     */
    public HL7GroovySegment getAt(int index) {
        return new HL7GroovySegment(segments.get(index - 1));
    }

    /**
     * Gets the segments.
     *
     * @return the segments
     */
    public List<HL7Segment> getSegments() {
        return segments;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return segments.toString();
    }
}
