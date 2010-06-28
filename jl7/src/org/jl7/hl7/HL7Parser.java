package org.jl7.hl7;

import org.jl7.textutils.EscapedStringTokenizer;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7Parser.
 * 
 * @author henribenoit
 */
public class HL7Parser {

    /**
     * Parses the component.
     * 
     * @param string
     *            the string
     * @return the h l7 component
     */
    public static HL7Component parseComponent(String string) {
        return parseComponent(string, "|^~\\&", true);
    }

    /**
     * Parses the component.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 component
     */
    public static HL7Component parseComponent(String hl7String, String delimiters, boolean escapesInSubcomponents) {
        HL7Component component = new HL7Component();
        component.setDelimiters(delimiters);
        EscapedStringTokenizer tokenizer = new EscapedStringTokenizer(hl7String, component.subcomponentSeparator,
                component.escapeCharacter);
        String[] subcomponents = tokenizer.getTokens();
        component.setSubcomponents(subcomponents, delimiters, escapesInSubcomponents);
        return component;
    }

    /**
     * Parses the component without delimiters.
     * 
     * @param hl7String
     *            the hl7 string
     * @return the h l7 component
     */
    public static HL7Component parseComponentWithoutDelimiters(String hl7String) {
        HL7Component component = new HL7Component();
        component.setSubcomponentsWithoutDelimiters(hl7String);
        return component;
    }

    /**
     * Parses the field.
     * 
     * @param string
     *            the string
     * @return the h l7 field
     */
    public static HL7Field parseField(String string) {
        return parseField(string, "|^~\\&", true);
    }

    /**
     * Parses the field.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 field
     */
    public static HL7Field parseField(String hl7String, String delimiters, boolean escapesInSubcomponents) {
        HL7Field field = new HL7Field();
        field.setDelimiters(delimiters);
        EscapedStringTokenizer tokenizer = new EscapedStringTokenizer(hl7String, field.repetitionSeparator,
                field.escapeCharacter);
        String[] fields = tokenizer.getTokens();
        field.setRepetitions(fields, delimiters, escapesInSubcomponents);
        return field;
    }

    /**
     * Parses the field repetition.
     * 
     * @param string
     *            the string
     * @return the h l7 field repetition
     */
    public static HL7FieldRepetition parseFieldRepetition(String string) {
        return parseFieldRepetition(string, "|^~\\&", true);
    }

    /**
     * Parses the field repetition.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 field repetition
     */
    public static HL7FieldRepetition parseFieldRepetition(String hl7String, String delimiters,
            boolean escapesInSubcomponents) {
        HL7FieldRepetition repetition = new HL7FieldRepetition();
        repetition.setDelimiters(delimiters);
        EscapedStringTokenizer tokenizer = new EscapedStringTokenizer(hl7String, repetition.componentSeparator,
                repetition.escapeCharacter);
        String[] components = tokenizer.getTokens();
        repetition.setComponents(components, delimiters, escapesInSubcomponents);
        return repetition;
    }

    /**
     * Parses the field repetition without delimiters.
     * 
     * @param hl7String
     *            the hl7 string
     * @return the h l7 field repetition
     */
    public static HL7FieldRepetition parseFieldRepetitionWithoutDelimiters(String hl7String) {
        HL7FieldRepetition repetition = new HL7FieldRepetition();
        repetition.setComponentsWithoutDelimiters(hl7String);
        return repetition;
    }

    /**
     * Parses the message.
     * 
     * @param hl7String
     *            the hl7 string
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 message
     */
    public static HL7Message parseMessage(String hl7String, boolean escapesInSubcomponents) {
        HL7Message message = new HL7Message();
        String delimiters = hl7String.substring(3, 8);
        message.setDelimiters(delimiters);
        EscapedStringTokenizer tokenizer = new EscapedStringTokenizer(hl7String, HL7Message.segmentTerminator,
                message.escapeCharacter);
        String[] segments = tokenizer.getTokens();
        for (String segment : segments) {
            message.addSegment(segment, delimiters, escapesInSubcomponents);
        }
        return message;
    }

    /**
     * Parses the msh first field.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @return the h l7 field
     */
    public static HL7Field parseMSHFirstField(String hl7String, String delimiters) {
        HL7Field field = new HL7Field();
        field.setDelimiters(delimiters);
        field.setRepetitionsWithoutDelimiters(hl7String);
        return field;
    }

    /**
     * Parses the segment.
     * 
     * @param string
     *            the string
     * @return the h l7 segment
     */
    public static HL7Segment parseSegment(String string) {
        return parseSegment(string, "|^~\\&", true);
    }

    /**
     * Parses the segment.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 segment
     */
    public static HL7Segment parseSegment(String hl7String, String delimiters, boolean escapesInSubcomponents) {
        HL7Segment segment = null;
        if (hl7String.startsWith("MSH")) {
            segment = new HL7MSHSegment();
        }
        else {
            segment = new HL7Segment();
        }
        segment.setDelimiters(delimiters);
        EscapedStringTokenizer tokenizer = new EscapedStringTokenizer(hl7String, segment.fieldSeparator,
                segment.escapeCharacter);
        String[] fields = tokenizer.getTokens();
        segment.setFields(fields, delimiters, escapesInSubcomponents);
        return segment;
    }

    /**
     * Parses the subcomponent.
     * 
     * @param string
     *            the string
     * @return the h l7 subcomponent
     */
    public static HL7Subcomponent parseSubcomponent(String string) {
        return parseSubcomponent(string, "|^~\\&", true);
    }

    /**
     * Parses the subcomponent.
     * 
     * @param hl7String
     *            the hl7 string
     * @param delimiters
     *            the delimiters
     * @param escapesInSubcomponents
     *            the escapes in subcomponents
     * @return the h l7 subcomponent
     */
    public static HL7Subcomponent parseSubcomponent(String hl7String, String delimiters, boolean escapesInSubcomponents) {
        HL7Subcomponent subcomponent = new HL7Subcomponent();
        if (escapesInSubcomponents) {
            subcomponent.setEscapedValue(hl7String, delimiters);
        }
        else {
            subcomponent.setValue(hl7String);
        }
        return subcomponent;
    }

    /**
     * Parses the subcomponent without delimiters.
     * 
     * @param hl7String
     *            the hl7 string
     * @return the h l7 subcomponent
     */
    public static HL7Subcomponent parseSubcomponentWithoutDelimiters(String hl7String) {
        HL7Subcomponent subcomponent = new HL7Subcomponent();
        subcomponent.setValue(hl7String);
        return subcomponent;
    }
}
