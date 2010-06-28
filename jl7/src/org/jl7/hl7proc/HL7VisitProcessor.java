package org.jl7.hl7proc;

import org.jl7.hl7.HL7Field;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7VisitProcessor.
 */
public class HL7VisitProcessor {
    
    /**
     * Gets the alternate visit id.
     *
     * @param seg the seg
     * @return the alternate visit id
     */
    public static HL7Field getAlternateVisitID(HL7Segment seg) {
        if (seg.getSegmentType().equals("PV1")) {
            return seg.get(50);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(6);
        }
        return null;
    }

    /**
     * Gets the alternate visit id.
     *
     * @param segs the segs
     * @return the alternate visit id
     */
    public static HL7Field getAlternateVisitID(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getAlternateVisitID(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets the visit number.
     *
     * @param seg the seg
     * @return the visit number
     */
    public static HL7Field getVisitNumber(HL7Segment seg) {
        if (seg.getSegmentType().equals("PV1")) {
            return seg.get(19);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(5);
        }
        return null;
    }

    /**
     * Gets the visit number.
     *
     * @param segs the segs
     * @return the visit number
     */
    public static HL7Field getVisitNumber(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getVisitNumber(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
