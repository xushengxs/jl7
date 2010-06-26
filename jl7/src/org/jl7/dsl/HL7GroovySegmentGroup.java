/**
 *
 */
package org.jl7.dsl;

import java.util.List;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;
import org.jl7.hl7proc.HL7MessageSplitter;

/**
 * @author henribenoit
 *
 */
public class HL7GroovySegmentGroup implements GroovyObject {
    private static final String DEFAULT_DELIMITERS = "|^~\\&";
    private HL7SegmentGroup group;

    public HL7SegmentGroup getSegmentGroup() {
        return group;
    }

    public HL7GroovySegmentGroup(HL7SegmentGroup group) {
        this.group = group;
    }

    public HL7GroovySegmentGroup(HL7SegmentGroup group, Closure args) {
        this(group);
        args.call();
    }

    public HL7GroovySegmentGroup leftShift(String segment) {
        group.add(HL7Parser.parseSegment(segment, DEFAULT_DELIMITERS, true));
        return this;
    }

    public HL7GroovySegmentGroup leftShift(HL7GroovySegment seg) {
        return leftShift(seg.getSegment());
    }

    private HL7GroovySegmentGroup leftShift(HL7Segment segment) {
        group.add(segment);
        return this;
    }

    public HL7GroovySegmentGroup leftShift(HL7GroovySegments segs) {
        return leftShift(segs.getSegments());
    }

    public HL7GroovySegmentGroup leftShift(HL7GroovySegmentGroup message) {
        return leftShift(message.getSegmentGroup().getSegments());
    }

    private HL7GroovySegmentGroup leftShift(List<HL7Segment> segments) {
        for (HL7Segment segment : segments) {
            leftShift(segment);
        }
        return this;
    }

    public String toString() {
        return group.getValue();
    }


    public MetaClass getMetaClass() {
        return GroovySystem.getMetaClassRegistry().getMetaClass(this.getClass());
    }

    public Object getProperty(String propertyName) {
        String[] items = propertyName.split("\\_");
        if (items.length > 0) {
            HL7GroovySegments segments = new HL7GroovySegments(group.get(items[0]));
            if (items.length > 1) {
                HL7GroovySegment segment = segments.getAt(1);
                int fieldIndex = Integer.parseInt(items[1]);
                if (items.length > 2) {

                }
                else {
                    return segment.getAt(fieldIndex);
                }
            }
            else {
                return segments;
            }
        }
        return null;
    }

    public Object invokeMethod(String name, Object args) {
        final Object[] varArgs = (Object[]) args;
        if (name.length() == 3) {
            // It's a segment
            final HL7GroovySegments segments = new HL7GroovySegments(group.get(name));
            if (varArgs[0] instanceof Integer) {
                HL7GroovySegment segment = segments.getAt(1);
                return segment.getAt((Integer) varArgs[0]);
            }
        }
        return null;
    }

    public void setMetaClass(MetaClass metaClass) {
        // TODO Auto-generated method stub

    }

    public void setProperty(String propertyName, Object newValue) {
        // TODO Auto-generated method stub

    }
}
