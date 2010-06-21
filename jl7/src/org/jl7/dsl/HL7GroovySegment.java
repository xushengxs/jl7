/**
 *
 */
package org.jl7.dsl;

import org.jl7.hl7.HL7Segment;

/**
 * @author henribenoit
 *
 */
public class HL7GroovySegment {
    private HL7Segment segment;

    public HL7GroovySegment(HL7Segment segment) {
        this.segment = segment;
    }

    public String toString() {
        return segment.toString();
    }

    public HL7GroovyField getAt(int index) {
        return new HL7GroovyField(segment.getAt(index));
    }

    public HL7GroovyField call(int index) {
        return new HL7GroovyField(segment.getAt(index));
    }
}
