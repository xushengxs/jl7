/**
 * 
 */
package org.jl7.test;

import org.jl7.hl7.HL7MSHSegment;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;

import junit.framework.TestCase;

/**
 * @author henribenoit
 * 
 */
public class TestHL7MSHSegment extends TestCase {
	private static String MSHSTRING = "MSH|^~\\&|APP1|GA0000|APP2|VAERS PROCESSOR|20010331605||ORU^RO1|20010422GA03|T|2.3.1|||AL|";

	private HL7MSHSegment msh;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		msh = new HL7MSHSegment();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7MSHSegment#setFields(java.lang.String[], java.lang.String, boolean)}
	 * .
	 */
	public void testSetFieldsStringArrayStringBoolean() {
		fail("Not yet implemented");
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
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageDateTime()}.
	 */
	public void testGetMessageDateTime() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageType()}.
	 */
	public void testGetMessageType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getMessageControlId()}.
	 */
	public void testGetMessageControlId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getProcessingId()}.
	 */
	public void testGetProcessingId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getGetVersionId()}.
	 */
	public void testGetGetVersionId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getSequenceNumber()}.
	 */
	public void testGetSequenceNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7MSHSegment#getContinuationPointer()}.
	 */
	public void testGetContinuationPointer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7MSHSegment#getAcceptAcknowledgementType()}.
	 */
	public void testGetAcceptAcknowledgementType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7MSHSegment#getApplicationAcknowledgementType()}.
	 */
	public void testGetApplicationAcknowledgementType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getCountryCode()}.
	 */
	public void testGetCountryCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getCharacterSet()}.
	 */
	public void testGetCharacterSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7MSHSegment#getPrincipalLanguage()}.
	 */
	public void testGetPrincipalLanguage() {
		fail("Not yet implemented");
	}

}
