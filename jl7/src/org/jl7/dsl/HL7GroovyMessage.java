/**
 *
 */
package org.jl7.dsl;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;

import java.util.List;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;
import org.jl7.hl7proc.HL7MessageSplitter;

/**
 * @author henribenoit
 * 
 */
public class HL7GroovyMessage implements GroovyObject {
    private static HL7GroovySegmentGroup getGroovySegmentGroup(final Object[] varArgs, List<HL7SegmentGroup> groups) {
        if (varArgs[0] instanceof Integer) {
            HL7SegmentGroup group = groups.get((Integer) varArgs[0] - 1);
            return new HL7GroovySegmentGroup(group);
        }
        return null;
    }

    private HL7Message msg;

    public HL7GroovyMessage(HL7Message msg) {
        this.msg = msg;
    }

    public HL7GroovyMessage(HL7Message msg, Closure args) {
        this(msg);
        args.call();
    }

    public HL7GroovyMessage(String msg) {
        this.msg = HL7Parser.parseMessage(msg, true);
    }

    public MetaClass getMetaClass() {
        return GroovySystem.getMetaClassRegistry().getMetaClass(this.getClass());
        // return new DelegatingMetaClass(HL7GroovyMessage.class);
    }

    public HL7Message getMsg() {
        return msg;
    }

    public Object getProperty(String name) {
        if (name.length() == 3) {
            // It's a segment
            return new HL7GroovySegments(msg.get(name));
        }
        else {
            // It's a group
            if (name.equals("PATIENTS")) {
                return HL7MessageSplitter.GetPatients(msg);
            }
            else if (name.equals("VISITS")) {
                return HL7MessageSplitter.GetVisits(msg);
            }
            else if (name.equals("ORDERS")) {
                return HL7MessageSplitter.GetOrders(msg);
            }
            else if (name.equals("PROCEDURES")) {
                return HL7MessageSplitter.GetProcedures(msg);
            }
            else if (name.equals("INSURANCES")) {
                return HL7MessageSplitter.GetInsurances(msg);
            }
            else if (name.equals("GUARANTORS")) {
                return HL7MessageSplitter.GetGuarantors(msg);
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
            if (name.equals("PATIENTS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetPatients(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("VISITS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetVisits(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("ORDERS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetOrders(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("PROCEDURES")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetProcedures(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("INSURANCES")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetInsurances(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("GUARANTORS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.GetGuarantors(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
        }
        return null;
    }

    public HL7GroovyMessage leftShift(HL7GroovyMessage message) {
        return leftShift(message.getMsg().getSegments());
    }

    public HL7GroovyMessage leftShift(HL7GroovySegment seg) {
        return leftShift(seg.getSegment());
    }

    public HL7GroovyMessage leftShift(HL7GroovySegmentGroup segments) {
        for (HL7Segment segment : segments.getSegmentGroup().getSegments()) {
            leftShift(segment);
        }
        return this;
    }

    public HL7GroovyMessage leftShift(HL7GroovySegments segs) {
        return leftShift(segs.getSegments());
    }

    public HL7GroovyMessage leftShift(HL7Segment segment) {
        HL7Message.addSegment(msg, segment);
        return this;
    }

    public HL7GroovyMessage leftShift(HL7SegmentGroup segments) {
        for (HL7Segment segment : segments.getSegments()) {
            leftShift(segment);
        }
        return this;
    }

    public HL7GroovyMessage leftShift(List objects) {
        for (Object o : objects) {
            System.out.println(o.getClass().getName());
            if (o instanceof HL7Segment) {
                leftShift((HL7Segment) o);
            }
            else if (o instanceof HL7SegmentGroup) {
                leftShift((HL7SegmentGroup) o);
            }
        }
        return this;
    }

    public HL7GroovyMessage leftShift(String segment) {
        msg.addSegment(segment, msg.getDelimiters(), true);
        return this;
    }

    public void setMetaClass(MetaClass metaClass) {
        // TODO Auto-generated method stub

    }

    public void setProperty(String property, Object value) {
        if (value instanceof String) {
            HL7Segment seg = HL7Parser.parseSegment((String) value, msg.getDelimiters(), true);
            setProperty(property, seg);
        }
        else if (value instanceof HL7SegmentGroup) {
            for (HL7Segment segment : ((HL7SegmentGroup) value).getSegments()) {
                setProperty(property, segment);
            }
        }
        else if (value instanceof HL7GroovySegmentGroup) {
            for (HL7Segment segment : ((HL7GroovySegmentGroup) value).getSegmentGroup().getSegments()) {
                setProperty(property, segment);
            }
        }
        else if (value instanceof HL7Segment) {
            HL7Segment segment = (HL7Segment) value;
            int index = msg.getIndex(segment.getSegmentType());
            if (index != -1) {
                msg.putAt(index, segment.getValue());
            }
            else {
                leftShift(segment);
            }
        }
        else if (value instanceof HL7GroovySegment) {
            setProperty(property, ((HL7GroovySegment) value).segment);
        }
        else if (value instanceof HL7GroovySegments) {
            List<HL7Segment> segments = ((HL7GroovySegments) value).getSegments();
            for (HL7Segment segment : segments) {
                setProperty(property, segment);
            }
        }
        else {
            throw new MissingPropertyException(property, value.toString(), this.getClass());
        }
    }

    @Override
    public String toString() {
        return msg.getValue();
    }
}
