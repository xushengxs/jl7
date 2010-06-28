package org.jl7.hl7proc;

import org.jl7.hl7.HL7Field;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7PatientProcessor.
 */
public class HL7PatientProcessor {
    
    /**
     * Gets the alternate patient id.
     *
     * @param seg the seg
     * @return the alternate patient id
     */
    public static HL7Field getAlternatePatientID(HL7Segment seg) {
        if (seg.getSegmentType().equals("PID")) {
            return seg.get(4);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(2);
        }
        return null;
    }

    /**
     * Gets the alternate patient id.
     *
     * @param segs the segs
     * @return the alternate patient id
     */
    public static HL7Field getAlternatePatientID(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getAlternatePatientID(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets the patient account number.
     *
     * @param seg the seg
     * @return the patient account number
     */
    public static HL7Field getPatientAccountNumber(HL7Segment seg) {
        if (seg.getSegmentType().equals("PID")) {
            return seg.get(18);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(3);
        }
        return null;
    }

    /**
     * Gets the patient account number.
     *
     * @param segs the segs
     * @return the patient account number
     */
    public static HL7Field getPatientAccountNumber(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getPatientAccountNumber(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets the patient id.
     *
     * @param seg the seg
     * @return the patient id
     */
    public static HL7Field getPatientID(HL7Segment seg) {
        if (seg.getSegmentType().equals("PID")) {
            return seg.get(3);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(1);
        }
        return null;
    }

    /**
     * Gets the patient id.
     *
     * @param segs the segs
     * @return the patient id
     */
    public static HL7Field getPatientID(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getPatientID(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets the patient id external.
     *
     * @param seg the seg
     * @return the patient id external
     */
    public static HL7Field getPatientIDExternal(HL7Segment seg) {
        if (seg.getSegmentType().equals("PID")) {
            return seg.get(2);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(4);
        }
        return null;
    }

    /**
     * Gets the patient id external.
     *
     * @param segs the segs
     * @return the patient id external
     */
    public static HL7Field getPatientIDExternal(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getPatientIDExternal(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Gets the patient name.
     *
     * @param seg the seg
     * @return the patient name
     */
    public static HL7Field getPatientName(HL7Segment seg) {
        if (seg.getSegmentType().equals("PID")) {
            return seg.get(5);
        }
        else if (seg.getSegmentType().equals("MRG")) {
            return seg.get(7);
        }
        return null;
    }

    /**
     * Gets the patient name.
     *
     * @param segs the segs
     * @return the patient name
     */
    public static HL7Field getPatientName(HL7SegmentGroup segs) {
        for (HL7Segment seg : segs) {
            HL7Field value = getPatientName(seg);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
