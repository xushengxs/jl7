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
    HL7Segment segment;

    public HL7Segment getSegment() {
        return segment;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HL7Segment)
        {
            return segment.equals(obj);
        }
        else if (obj instanceof HL7GroovySegment) {
            return segment.equals(((HL7Segment)obj));
        }
        else if (obj instanceof String) {
            return segment.getValue().equals(obj);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return segment.getValue().hashCode();
    }
}
