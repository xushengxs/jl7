/**
 * 
 */
package org.jl7.test;

import java.util.ArrayList;
import java.util.Enumeration;

import junit.framework.TestCase;

import org.jl7.hl7.HL7Field;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;

/**
 * @author henribenoit
 * 
 */
public class TestHL7Segment extends TestCase {

    private static final String DELIMITERS = "|^~\\&";
    private HL7Segment segment;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        segment = new HL7Segment();
        segment.setDelimiters(DELIMITERS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#get(int)}.
     */
    public void testGet() {
        String[] fields = { "abc" + HL7Message.segmentTerminator, "\\|~^&def" };
        segment.setFields(fields, DELIMITERS, false);
        HL7Field field = segment.get(0);
        assertEquals("abc" + HL7Message.segmentTerminator, field.getValue());
        field = segment.get(1);
        assertEquals("\\|~^&def", field.getValue());
        field = segment.get(2);
        assertNull(field);
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getCount()}.
     */
    public void testGetCount() {
        assertEquals(0, segment.getCount());
        String[] fields = { "abc\\" + HL7Message.segmentTerminator, "\\E\\\\F\\\\R\\\\S\\\\T\\def" };
        segment.setFields(fields, DELIMITERS, true);
        assertEquals(2, segment.getCount());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getDelimiters()}.
     */
    public void testGetDelimiters() {
        String delimiters = segment.getDelimiters();
        assertEquals(DELIMITERS, delimiters);
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getEnumerator()}.
     */
    public void testGetEnumerator() {
        String[] fields = { "abc\\" + HL7Message.segmentTerminator, "\\E\\\\F\\\\R\\\\S\\\\T\\def" };
        segment.setFields(fields, DELIMITERS, true);
        Enumeration<HL7Field> enumerator = segment.getEnumerator();
        assertEquals(true, enumerator.hasMoreElements());
        assertEquals("abc\\" + HL7Message.segmentTerminator, enumerator.nextElement().toString());
        assertEquals(true, enumerator.hasMoreElements());
        assertEquals("\\E\\\\F\\\\R\\\\S\\\\T\\def", enumerator.nextElement().toString());
        assertEquals(false, enumerator.hasMoreElements());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getFields()}.
     */
    public void testGetFields() {
        String[] fields = { "abc" + HL7Message.segmentTerminator, "\\|~^&def" };
        segment.setFields(fields, DELIMITERS, false);
        ArrayList<HL7Field> values = segment.getFields();
        assertNotNull(values);
        assertEquals(2, values.size());
        assertEquals("abc" + HL7Message.segmentTerminator, values.get(0).getValue());
        assertEquals("\\|~^&def", values.get(1).getValue());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getSegmentType()}.
     */
    public void testGetSegmentType() {
        String[] fields = { "PID", "1" };
        segment.setFields(fields, DELIMITERS, true);
        assertEquals("PID", segment.getSegmentType());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#getValue()}.
     */
    public void testGetValue() {
        String[] fields = { "abc\\" + HL7Message.segmentTerminator, "\\E\\\\F\\\\R\\\\S\\\\T\\def" };
        segment.setFields(fields, DELIMITERS, true);
        String value = segment.getValue();
        assertEquals("abc" + HL7Message.segmentTerminator + "|\\|~^&def", value);
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7Segment#setDelimiters(java.lang.String)}.
     */
    public void testSetDelimiters() {
        assertEquals(segment.escapeCharacter, '\\');
        assertEquals(segment.subcomponentSeparator, '&');
        assertEquals(segment.componentSeparator, '^');
        assertEquals(segment.repetitionSeparator, '~');
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7Segment#setFields(java.util.ArrayList)}.
     */
    public void testSetFieldsArrayListOfHL7Field() {
        String hl7String1 = "1234^^^^SR~1234-12^^^^LR~00725^^^^MR";
        String hl7String2 = "1234567^Welby^Marcus^J^Jr^Dr.^MD^L";
        HL7Field field1 = HL7Parser.parseField(hl7String1, DELIMITERS, true);
        HL7Field field2 = HL7Parser.parseField(hl7String2, DELIMITERS, true);
        ArrayList<HL7Field> fields = new ArrayList<HL7Field>();
        fields.add(field1);
        fields.add(field2);
        segment.setFields(fields);
        ArrayList<HL7Field> fieldsReturned = segment.getFields();
        assertEquals(2, fieldsReturned.size());
        assertEquals(hl7String1, fieldsReturned.get(0).toString());
        assertEquals(hl7String2, fieldsReturned.get(1).toString());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7Segment#setFields(org.jl7.hl7.HL7Field[])}.
     */
    public void testSetFieldsHL7FieldArray() {
        String hl7String1 = "1234^^^^SR~1234-12^^^^LR~00725^^^^MR";
        String hl7String2 = "1234567^Welby^Marcus^J^Jr^Dr.^MD^L";
        HL7Field field1 = HL7Parser.parseField(hl7String1, DELIMITERS, true);
        HL7Field field2 = HL7Parser.parseField(hl7String2, DELIMITERS, true);
        HL7Field[] fields = { field1, field2 };
        segment.setFields(fields);
        ArrayList<HL7Field> fieldsReturned = segment.getFields();
        assertEquals(2, fieldsReturned.size());
        assertEquals(hl7String1, fieldsReturned.get(0).toString());
        assertEquals(hl7String2, fieldsReturned.get(1).toString());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7Segment#setFields(java.lang.String[], java.lang.String, boolean)}
     * .
     */
    public void testSetFieldsStringArrayStringTrue() {
        String hl7String1 = "1234^^^^SR~1234-12^^^^\\F\\LR~00725^^^^MR";
        String hl7String2 = "1234567^Welby^Marcus^\\|J^Jr^Dr.^MD^L";
        String[] fields = { hl7String1, hl7String2 };
        segment.setFields(fields, DELIMITERS, true);
        ArrayList<HL7Field> fieldsReturned = segment.getFields();
        assertEquals(2, fieldsReturned.size());
        assertEquals(hl7String1.replace("\\|", "\\F\\"), fieldsReturned.get(0).toString());
        assertEquals(hl7String2.replace("\\|", "\\F\\"), fieldsReturned.get(1).toString());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7Segment#toString()}.
     */
    public void testToString() {
        String[] fields = { "abc\\" + HL7Message.segmentTerminator, "\\E\\\\F\\\\R\\\\S\\\\T\\def" };
        segment.setFields(fields, DELIMITERS, true);
        String value = segment.toString();
        assertEquals("abc\\" + HL7Message.segmentTerminator + "|\\E\\\\F\\\\R\\\\S\\\\T\\def", value);
    }
}
