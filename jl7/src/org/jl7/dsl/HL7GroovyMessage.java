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

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovyMessage.
 * 
 * @author henribenoit
 */
public class HL7GroovyMessage implements GroovyObject {

    /**
     * Gets the groovy segment group.
     * 
     * @param varArgs
     *            the var args
     * @param groups
     *            the groups
     * @return the groovy segment group
     */
    private static HL7GroovySegmentGroup getGroovySegmentGroup(final Object[] varArgs, List<HL7SegmentGroup> groups) {
        if (varArgs[0] instanceof Integer) {
            HL7SegmentGroup group = groups.get((Integer) varArgs[0] - 1);
            return new HL7GroovySegmentGroup(group);
        }
        return null;
    }

    /** The msg. */
    private final HL7Message msg;

    /**
     * Instantiates a new h l7 groovy message.
     * 
     * @param msg
     *            the msg
     */
    public HL7GroovyMessage(HL7Message msg) {
        this.msg = msg;
    }

    /**
     * Instantiates a new h l7 groovy message.
     * 
     * @param msg
     *            the msg
     * @param args
     *            the args
     */
    public HL7GroovyMessage(HL7Message msg, Closure args) {
        this(msg);
        args.call();
    }

    /**
     * Instantiates a new h l7 groovy message.
     * 
     * @param msg
     *            the msg
     */
    public HL7GroovyMessage(String msg) {
        this.msg = HL7Parser.parseMessage(msg, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see groovy.lang.GroovyObject#getMetaClass()
     */
    public MetaClass getMetaClass() {
        return GroovySystem.getMetaClassRegistry().getMetaClass(this.getClass());
        // return new DelegatingMetaClass(HL7GroovyMessage.class);
    }

    /**
     * Gets the msg.
     * 
     * @return the msg
     */
    public HL7Message getMsg() {
        return msg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see groovy.lang.GroovyObject#getProperty(java.lang.String)
     */
    public Object getProperty(String name) {
        if (name.length() == 3) {
            // It's a segment
            return new HL7GroovySegments(msg.get(name));
        }
        else {
            // It's a group
            if (name.equals("PATIENTS")) {
                return HL7MessageSplitter.getPatients(msg);
            }
            else if (name.equals("VISITS")) {
                return HL7MessageSplitter.getVisits(msg);
            }
            else if (name.equals("ORDERS")) {
                return HL7MessageSplitter.getOrders(msg);
            }
            else if (name.equals("PROCEDURES")) {
                return HL7MessageSplitter.getProcedures(msg);
            }
            else if (name.equals("INSURANCES")) {
                return HL7MessageSplitter.getInsurances(msg);
            }
            else if (name.equals("GUARANTORS")) {
                return HL7MessageSplitter.getGuarantors(msg);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see groovy.lang.GroovyObject#invokeMethod(java.lang.String,
     * java.lang.Object)
     */
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
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getPatientGroups(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("VISITS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getVisitGroups(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("ORDERS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getOrderGroups(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("PROCEDURES")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getProcedureGroups(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("INSURANCES")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getInsuranceGroups(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
            else if (name.equals("GUARANTORS")) {
                List<HL7SegmentGroup> groups = HL7MessageSplitter.getGuarantors(msg);
                return getGroovySegmentGroup(varArgs, groups);
            }
        }
        return null;
    }

    /**
     * Left shift.
     * 
     * @param message
     *            the message
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7GroovyMessage message) {
        return leftShift(message.getMsg().getSegments());
    }

    /**
     * Left shift.
     * 
     * @param seg
     *            the seg
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7GroovySegment seg) {
        return leftShift(seg.getSegment());
    }

    /**
     * Left shift.
     * 
     * @param segments
     *            the segments
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7GroovySegmentGroup segments) {
        for (HL7Segment segment : segments.getSegmentGroup().getSegments()) {
            leftShift(segment);
        }
        return this;
    }

    /**
     * Left shift.
     * 
     * @param segs
     *            the segs
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7GroovySegments segs) {
        return leftShift(segs.getSegments());
    }

    /**
     * Left shift.
     * 
     * @param segment
     *            the segment
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7Segment segment) {
        HL7Message.addSegment(msg, segment);
        return this;
    }

    /**
     * Left shift.
     * 
     * @param segments
     *            the segments
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(HL7SegmentGroup segments) {
        for (HL7Segment segment : segments.getSegments()) {
            leftShift(segment);
        }
        return this;
    }

    /**
     * Left shift.
     * 
     * @param objects
     *            the objects
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(@SuppressWarnings("rawtypes") List objects) {
        for (Object o : objects) {
            if (o instanceof HL7Segment) {
                leftShift((HL7Segment) o);
            }
            else if (o instanceof HL7SegmentGroup) {
                leftShift((HL7SegmentGroup) o);
            }
        }
        return this;
    }

    /**
     * Left shift.
     * 
     * @param segment
     *            the segment
     * @return the h l7 groovy message
     */
    public HL7GroovyMessage leftShift(String segment) {
        msg.addSegment(segment, msg.getDelimiters(), true);
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see groovy.lang.GroovyObject#setMetaClass(groovy.lang.MetaClass)
     */
    public void setMetaClass(MetaClass metaClass) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see groovy.lang.GroovyObject#setProperty(java.lang.String,
     * java.lang.Object)
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return msg.getValue();
    }
}
