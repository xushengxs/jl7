/**
 *
 */
package org.jl7.dsl;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClass;

import java.util.List;

import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7GroovySegmentGroup.
 *
 * @author henribenoit
 */
public class HL7GroovySegmentGroup implements GroovyObject {
    
    /** The Constant DEFAULT_DELIMITERS. */
    private static final String DEFAULT_DELIMITERS = "|^~\\&";
    
    /** The group. */
    private HL7SegmentGroup group;

    /**
     * Instantiates a new h l7 groovy segment group.
     *
     * @param group the group
     */
    public HL7GroovySegmentGroup(HL7SegmentGroup group) {
        this.group = group;
    }

    /**
     * Instantiates a new h l7 groovy segment group.
     *
     * @param group the group
     * @param args the args
     */
    public HL7GroovySegmentGroup(HL7SegmentGroup group, Closure args) {
        this(group);
        args.call();
    }

    /* (non-Javadoc)
     * @see groovy.lang.GroovyObject#getMetaClass()
     */
    public MetaClass getMetaClass() {
        return GroovySystem.getMetaClassRegistry().getMetaClass(this.getClass());
    }

    /* (non-Javadoc)
     * @see groovy.lang.GroovyObject#getProperty(java.lang.String)
     */
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

    /**
     * Gets the segment group.
     *
     * @return the segment group
     */
    public HL7SegmentGroup getSegmentGroup() {
        return group;
    }

    /* (non-Javadoc)
     * @see groovy.lang.GroovyObject#invokeMethod(java.lang.String, java.lang.Object)
     */
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

    /**
     * Left shift.
     *
     * @param seg the seg
     * @return the h l7 groovy segment group
     */
    public HL7GroovySegmentGroup leftShift(HL7GroovySegment seg) {
        return leftShift(seg.getSegment());
    }

    /**
     * Left shift.
     *
     * @param message the message
     * @return the h l7 groovy segment group
     */
    public HL7GroovySegmentGroup leftShift(HL7GroovySegmentGroup message) {
        return leftShift(message.getSegmentGroup().getSegments());
    }

    /**
     * Left shift.
     *
     * @param segs the segs
     * @return the h l7 groovy segment group
     */
    public HL7GroovySegmentGroup leftShift(HL7GroovySegments segs) {
        return leftShift(segs.getSegments());
    }

    /**
     * Left shift.
     *
     * @param segment the segment
     * @return the h l7 groovy segment group
     */
    private HL7GroovySegmentGroup leftShift(HL7Segment segment) {
        group.add(segment);
        return this;
    }

    /**
     * Left shift.
     *
     * @param segments the segments
     * @return the h l7 groovy segment group
     */
    private HL7GroovySegmentGroup leftShift(List<HL7Segment> segments) {
        for (HL7Segment segment : segments) {
            leftShift(segment);
        }
        return this;
    }

    /**
     * Left shift.
     *
     * @param segment the segment
     * @return the h l7 groovy segment group
     */
    public HL7GroovySegmentGroup leftShift(String segment) {
        group.add(HL7Parser.parseSegment(segment, DEFAULT_DELIMITERS, true));
        return this;
    }

    /* (non-Javadoc)
     * @see groovy.lang.GroovyObject#setMetaClass(groovy.lang.MetaClass)
     */
    public void setMetaClass(MetaClass metaClass) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see groovy.lang.GroovyObject#setProperty(java.lang.String, java.lang.Object)
     */
    public void setProperty(String propertyName, Object newValue) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return group.getValue();
    }
}
