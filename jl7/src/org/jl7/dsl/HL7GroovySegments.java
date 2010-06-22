/**
 *
 */
package org.jl7.dsl;

import java.util.List;

import org.jl7.hl7.HL7Segment;

/**
 * @author henribenoit
 *
 */
public class HL7GroovySegments {
    private List<HL7Segment> segments;

    public List<HL7Segment> getSegments() {
        return segments;
    }

    public HL7GroovySegments(List<HL7Segment> segments) {
        this.segments=segments;
    }

    public String toString() {
        return segments.toString();
    }

    public HL7GroovySegment getAt(int index) {
        return new HL7GroovySegment(segments.get(index - 1));
    }

    public HL7GroovyField call(int index) {
        HL7GroovySegment segment = new HL7GroovySegment(segments.get(0));
        return segment.getAt(index);
    }
}
