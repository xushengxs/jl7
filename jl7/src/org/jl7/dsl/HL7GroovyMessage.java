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

/**
 * @author henribenoit
 *
 */
public class HL7GroovyMessage implements GroovyObject {
    private HL7Message msg;

    public HL7Message getMsg() {
        return msg;
    }

    public HL7GroovyMessage(HL7Message msg) {
        this.msg = msg;
    }

    public HL7GroovyMessage(String msg) {
        this.msg = HL7Parser.parseMessage(msg, true);
    }

    public HL7GroovyMessage(HL7Message msg, Closure args) {
        this(msg);
        args.call();
    }

    public void setProperty(String property, Object value) {
        if (value instanceof String) {
            HL7Segment seg = HL7Parser.parseSegment((String) value, msg.getDelimiters(), true);
            setProperty(property, seg);
        }
        else if (value instanceof HL7Segment) {
            HL7Segment segment = (HL7Segment)value;
            int index = msg.getIndex(segment.getSegmentType());
            if (index != -1) {
                msg.putAt(index, segment.getValue());
            }
            else {
                leftShift(segment);
            }
        }
        else if (value instanceof HL7GroovySegment) {
            setProperty(property, ((HL7GroovySegment)value).segment);
        }
        else if (value instanceof HL7GroovySegments) {
            List<HL7Segment> segments = ((HL7GroovySegments)value).getSegments();
            for (HL7Segment segment : segments) {
                setProperty(property, segment);
            }
        }
        else {
            throw new MissingPropertyException(property, value.toString(), this.getClass());
        }
    }

    public HL7GroovyMessage leftShift(String segment) {
        msg.addSegment(segment, msg.getDelimiters(), true);
        return this;
    }

    public HL7GroovyMessage leftShift(HL7GroovySegment seg) {
        return leftShift(seg.getSegment());
    }

    private HL7GroovyMessage leftShift(HL7Segment segment) {
        HL7Message.addSegment(msg, segment);
        return this;
    }

    public HL7GroovyMessage leftShift(HL7GroovySegments segs) {
        return leftShift(segs.getSegments());
    }

    public HL7GroovyMessage leftShift(HL7GroovyMessage message) {
        return leftShift(message.getMsg().getSegments());
    }

    private HL7GroovyMessage leftShift(List<HL7Segment> segments) {
        for (HL7Segment segment : segments) {
            leftShift(segment);
        }
        return this;
    }

    public String toString() {
        return msg.getValue();
    }


    public MetaClass getMetaClass() {
        return GroovySystem.getMetaClassRegistry().getMetaClass(this.getClass());
//        return new DelegatingMetaClass(HL7GroovyMessage.class);
    }

    public Object getProperty(String propertyName) {
        String[] items = propertyName.split("\\_");
        if (items.length > 0) {
            HL7GroovySegments segments = new HL7GroovySegments(msg.get(items[0]));
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
            final HL7GroovySegments segments = new HL7GroovySegments(msg.get(name));
            if (varArgs[0] instanceof Integer) {
                HL7GroovySegment segment = segments.getAt(1);
                return segment.getAt((Integer) varArgs[0]);
            }
        }
        else {
            // It's a group
        }
        return null;
    }

    public void setMetaClass(MetaClass metaClass) {
        // TODO Auto-generated method stub

    }
}
