/**
 * 
 */
package org.jl7.test;

import junit.framework.TestCase;

import org.jl7.hl7.HL7MSHSegment;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;

/**
 * @author henribenoit
 * 
 */
public class TestHL7MSHSegment extends TestCase {
    private static String MSHSTRING = "MSH|^~\\&|APP1|GA0000|APP2|VAERS PROCESSOR|20010331605||ORU^R01|20010422GA03|T|2.5|5|CP|AL|ER|DEU|8859/1|de|profile1";
    private static final String DELIMITERS = "|^~\\&";

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
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
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#getAcceptAcknowledgementType()}.
     */
    public void testGetAcceptAcknowledgementType() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("AL", header.getAcceptAcknowledgementType());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#getApplicationAcknowledgementType()}.
     */
    public void testGetApplicationAcknowledgementType() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("ER", header.getApplicationAcknowledgementType());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getCharacterSet()}.
     */
    public void testGetCharacterSet() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("8859/1", header.getCharacterSet());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#getContinuationPointer()}.
     */
    public void testGetContinuationPointer() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("CP", header.getContinuationPointer());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getCountryCode()}.
     */
    public void testGetCountryCode() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("DEU", header.getCountryCode());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getEventType()}.
     */
    public void testGetEventType() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("R01", header.getEventType());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageControlId()}.
     */
    public void testGetMessageControlId() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("20010422GA03", header.getMessageControlId());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageDateTime()}.
     */
    public void testGetMessageDateTime() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("20010331605", header.getMessageDateTime());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageEventType()}.
     */
    public void testGetMessageEventType() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("ORU^R01", header.getMessageEventType());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageType()}.
     */
    public void testGetMessageType() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("ORU", header.getMessageType());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getPrincipalLanguage()}.
     */
    public void testGetPrincipalLanguage() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("de", header.getPrincipalLanguage());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getProcessingId()}.
     */
    public void testGetProcessingId() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("T", header.getProcessingId());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#getReceivingApplication()}.
     */
    public void testGetReceivingApplication() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("APP2", header.getReceivingApplication());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getReceivingFacility()}.
     */
    public void testGetReceivingFacility() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("VAERS PROCESSOR", header.getReceivingFacility());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getSendingApplication()}
     * .
     */
    public void testGetSendingApplication() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("APP1", header.getSendingApplication());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getSendingFacility()}.
     */
    public void testGetSendingFacility() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("GA0000", header.getSendingFacility());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getSequenceNumber()}.
     */
    public void testGetSequenceNumber() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals(5, header.getSequenceNumber());
    }

    /**
     * Test method for {@link org.jl7.hl7.HL7MSHSegment#getVersionId()}.
     */
    public void testGetVersionId() {
        HL7Message msg = HL7Parser.parseMessage(MSHSTRING, true);
        HL7MSHSegment header = msg.getHeader();
        assertNotNull(header);
        assertEquals("2.5", header.getVersionId());
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#setFields(java.lang.String[], java.lang.String, boolean)}
     * .
     */
    public void testSetFieldsStringArrayStringFalse() {
        HL7MSHSegment header = new HL7MSHSegment();
        String[] fields = { "MSH", "^~\\&", "APP1", "GA0000", "APP2", "VAERS PROCESSOR", "20010331605", "", "ORU^RO1",
                "20010422GA03", "T", "2.5", "5", "CP", "AL", "ER", "DEU", "8859/1", "de", "profile1\\F\\" };
        header.setFields(fields, DELIMITERS, false);
        for (int i = 0; i < fields.length; i++) {
            assertEquals(fields[i], header.get(i).getValue());
        }
    }

    /**
     * Test method for
     * {@link org.jl7.hl7.HL7MSHSegment#setFields(java.lang.String[], java.lang.String, boolean)}
     * .
     */
    public void testSetFieldsStringArrayStringTrue() {
        HL7MSHSegment header = new HL7MSHSegment();
        String[] fields = { "MSH", "^~\\&", "APP1", "GA0000", "APP2", "VAERS PROCESSOR", "20010331605", "", "ORU^R01",
                "20010422GA03", "T", "2.5", "5", "CP", "AL", "ER", "DEU", "8859/1", "de", "profile1\\F\\" };
        header.setFields(fields, DELIMITERS, true);
        for (int i = 0; i < fields.length - 1; i++) {
            assertEquals(fields[i], header.get(i).getValue());
        }
        assertEquals("profile1|", header.get(fields.length - 1).getValue());
    }

}
