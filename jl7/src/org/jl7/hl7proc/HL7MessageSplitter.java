package org.jl7.hl7proc;

import java.util.ArrayList;
import java.util.List;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;

public class HL7MessageSplitter {
    public static List<HL7SegmentGroup> getGuarantors(HL7Message msg) {
        List<HL7SegmentGroup> guarantors = null;
        List<HL7SegmentGroup> visits = getVisits(msg);
        // Handle messages without PV1 segment
        if (visits.size() == 0) {
            visits = getPatients(msg);
            // Handle messages with neither PV1 nor PID segment
            if (visits.size() == 0) {
                visits = new ArrayList<HL7SegmentGroup>();
                visits.add(new HL7SegmentGroup(msg.getSegments()));
            }
        }
        for (HL7SegmentGroup visit : visits) {
            HL7SegmentGroup visitData = new HL7SegmentGroup(visit.getAllSegmentsBeforeTypeExceptHeader("GT1"));
            guarantors = visit.getSegmentGroups("GT1");
            for (HL7SegmentGroup guarantor : guarantors) {
                // Remove procedure info
                visitData.remove("PR1|ROL");
                // Remove insurance info
                guarantor.removeAllSegmentsAfter("IN1");
                // Added patient and visit info
                guarantor.insertAtBeginning(visitData.getSegments());
            }
        }
        return guarantors;
    }

    public static List<HL7SegmentGroup> getInsuranceGroups(HL7Message msg) {
        List<HL7SegmentGroup> groups = msg.getSegmentGroups("IN1");
        List<HL7SegmentGroup> result = new ArrayList<HL7SegmentGroup>();
        for (HL7SegmentGroup group : groups) {
            group.removeAllSegmentsAfter("PID|PV1|PR1|DG1|FT1|OBR|ORC");
            result.add(new HL7SegmentGroup(group.get("IN1|IN2|IN3|ROL")));
        }
        return result;
    }

    public static List<HL7SegmentGroup> getInsurances(HL7Message msg) {
        List<HL7SegmentGroup> insurances = null;
        List<HL7SegmentGroup> visits = getVisits(msg);
        // Handle messages without PV1 segment
        if (visits.size() == 0) {
            visits = getPatients(msg);
            // Handle messages with neither PV1 nor PID segment
            if (visits.size() == 0) {
                visits = new ArrayList<HL7SegmentGroup>();
                visits.add(new HL7SegmentGroup(msg.getSegments()));
            }
        }
        for (HL7SegmentGroup visit : visits) {
            HL7SegmentGroup visitData = new HL7SegmentGroup(visit.getAllSegmentsBeforeTypeExceptHeader("IN1"));
            insurances = visit.getSegmentGroups("IN1");
            for (HL7SegmentGroup insurance : insurances) {
                // Remove procedure and guarantor info
                visitData.remove("PR1|ROL|GT1");
                // Added patient and visit info
                insurance.insertAtBeginning(visitData.getSegments());
            }
        }
        return insurances;
    }

    public static List<HL7SegmentGroup> getOrderGroups(HL7Message msg) {
        List<HL7SegmentGroup> groups = msg.getSegmentGroups("ORC");
        List<HL7SegmentGroup> result = new ArrayList<HL7SegmentGroup>();
        for (HL7SegmentGroup group : groups) {
            group.removeAllSegmentsAfter("PID|PV1|PR1|DG1|FT1|IN1");
            result.add(new HL7SegmentGroup(group.get("ORC|OBR|NTE")));
        }
        return result;
    }

    public static List<HL7SegmentGroup> getOrders(HL7Message msg) {
        List<HL7SegmentGroup> orders = null;
        List<HL7SegmentGroup> visits = getVisits(msg);
        // Handle messages without PV1 segment
        if (visits.size() == 0) {
            visits = getPatients(msg);
            // Handle messages with neither PV1 nor PID segment
            if (visits.size() == 0) {
                visits = new ArrayList<HL7SegmentGroup>();
                visits.add(new HL7SegmentGroup(msg.getSegments()));
            }
        }
        for (HL7SegmentGroup visit : visits) {
            List<HL7Segment> visitData = visit.getAllSegmentsBeforeTypeExceptHeader("ORC");
            orders = visit.getSegmentGroups("ORC");
            // Handle messages with OBR segments but no ORC segment
            if (orders.size() == 0) {
                visitData = visit.getAllSegmentsBeforeTypeExceptHeader("OBR");
                orders = visit.getSegmentGroups("OBR");
            }
            for (HL7SegmentGroup order : orders) {
                order.insertAtBeginning(visitData);
            }
        }
        return orders;
    }

    public static List<HL7SegmentGroup> getPatientGroups(HL7Message msg) {
        List<HL7SegmentGroup> groups = msg.getSegmentGroups("PID|MRG");
        List<HL7SegmentGroup> result = new ArrayList<HL7SegmentGroup>();
        for (HL7SegmentGroup group : groups) {
            group.removeAllSegmentsAfter("PV1|PR1|IN1|DG1|FT1|OBR|ORC");
            result.add(new HL7SegmentGroup(group.get("PID|MRG|PD1|ARV|ROL|NK1|NTE")));
        }
        return result;
    }

    public static List<HL7SegmentGroup> getPatients(HL7Message msg) {
        return msg.getSegmentGroups("PID|MRG");
    }

    public static List<HL7SegmentGroup> getProcedureGroups(HL7Message msg) {
        List<HL7SegmentGroup> groups = msg.getSegmentGroups("PR1");
        List<HL7SegmentGroup> result = new ArrayList<HL7SegmentGroup>();
        for (HL7SegmentGroup group : groups) {
            group.removeAllSegmentsAfter("PID|PV1|IN1|DG1|FT1|OBR|ORC");
            result.add(new HL7SegmentGroup(group.get("PR1|ROL")));
        }
        return result;
    }

    public static List<HL7SegmentGroup> getProcedures(HL7Message msg) {
        List<HL7SegmentGroup> procedures = null;
        List<HL7SegmentGroup> visits = getVisits(msg);
        // Handle messages without PV1 segment
        if (visits.size() == 0) {
            visits = getPatients(msg);
            // Handle messages with neither PV1 nor PID segment
            if (visits.size() == 0) {
                visits = new ArrayList<HL7SegmentGroup>();
                visits.add(new HL7SegmentGroup(msg.getSegments()));
            }
        }
        for (HL7SegmentGroup visit : visits) {
            List<HL7Segment> visitData = visit.getAllSegmentsBeforeTypeExceptHeader("PR1");
            procedures = visit.getSegmentGroups("PR1");
            for (HL7SegmentGroup procedure : procedures) {
                // Remove guarantor and insurance info
                procedure.removeAllSegmentsAfter("GT1|IN1");
                // Added patient and visit info
                procedure.insertAtBeginning(visitData);
            }
        }
        return procedures;
    }

    public static List<HL7SegmentGroup> getVisitGroups(HL7Message msg) {
        List<HL7SegmentGroup> groups = msg.getSegmentGroups("PV1");
        List<HL7SegmentGroup> result = new ArrayList<HL7SegmentGroup>();
        for (HL7SegmentGroup group : groups) {
            group.removeAllSegmentsAfter("PID|PR1|IN1|DG1|FT1|OBR|ORC");
            result.add(new HL7SegmentGroup(group.get("PV1|PV2|ARV|ROL|DB1")));
        }
        return result;
    }

    public static List<HL7SegmentGroup> getVisits(HL7Message msg) {
        List<HL7SegmentGroup> visits = null;
        List<HL7SegmentGroup> patients = getPatients(msg);
        // Handle messages without PID segment
        if (patients.size() == 0) {
            patients = new ArrayList<HL7SegmentGroup>();
            patients.add(new HL7SegmentGroup(msg.getSegments()));
        }
        for (HL7SegmentGroup patient : patients) {
            List<HL7Segment> patientData = patient.getAllSegmentsBeforeTypeExceptHeader("PV1");
            visits = patient.getSegmentGroups("PV1");
            for (HL7SegmentGroup visit : visits) {
                visit.insertAtBeginning(patientData);
            }
        }
        return visits;
    }
}
