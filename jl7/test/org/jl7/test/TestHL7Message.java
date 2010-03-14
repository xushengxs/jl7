/**
 * 
 */
package org.jl7.test;

import java.util.Enumeration;
import java.util.List;

import junit.framework.TestCase;

import org.jl7.hl7.HL7MSHSegment;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7Segment;
import org.jl7.hl7.HL7SegmentGroup;

/**
 * @author henribenoit
 * 
 */
public class TestHL7Message extends TestCase {
	private static final String MESSAGE = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII\rEVN|A08|20060705000000\rPID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE";
	private static final String MESSAGE_WO_MSH = "EVN|A08|20060705000000\rPID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE";
	private static final String SEG_GROUP = "PID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE";
	private static final String HEADER = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII";
	private static final String[] SEGMENTS = {
			"MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII",
			"EVN|A08|20060705000000",
			"PID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA",
			"PV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V",
			"PV2|||||||1||||||||||||||||^^^^^^^^^103",
			"ROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1", "PR1|1||1||20060705|1",
			"GT1|1||||||||||||||||||||NOT APPLICABLE" };
	private static final String PID_PV1_ROL = "PID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1";
	private static final String DELIMITERS = "|^~\\&";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
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
	 * Test method for {@link org.jl7.hl7.HL7Message#getDelimiters(),@link
	 * org.jl7.hl7.HL7Message#setDelimiters(java.lang.String)}.
	 */
	public void testSetGetDelimiters() {
		HL7Message msg = new HL7Message();
		msg.setDelimiters(DELIMITERS);
		String delimiters = msg.getDelimiters();
		assertEquals(DELIMITERS, delimiters);
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getMessageEventType()}.
	 */
	public void testGetMessageEventType() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		String type = msg.getMessageEventType();
		assertEquals("ADT^A08^ADT_A01", type);
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getMessageType()}.
	 */
	public void testGetMessageType() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		String type = msg.getMessageType();
		assertEquals("ADT", type);
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getEventType()}.
	 */
	public void testGetEventType() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		String type = msg.getEventType();
		assertEquals("A08", type);
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getHeader()}.
	 */
	public void testGetHeaderNoHeader() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE_WO_MSH, true);
		HL7MSHSegment header = message.getHeader();
		assertNull(header);
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getHeader()}.
	 */
	public void testGetHeader() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		HL7MSHSegment header = message.getHeader();
		assertNotNull(header);
		assertEquals(HEADER, header.getValue());
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7Message#getSegmentGroups(java.lang.String)}.
	 */
	public void testGetSegmentGroupsOneGroup() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		List<HL7SegmentGroup> groups = message.getSegmentGroups("PID");
		assertNotNull(groups);
		assertEquals(1, groups.size());
		HL7SegmentGroup group = groups.get(0);
		assertNotNull(group);
		assertEquals(SEG_GROUP, group.getValue());
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7Message#addSegment(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	public void testAddSegmentStringStringBoolean() {
		HL7Message msg = new HL7Message();
		for (String segment : SEGMENTS) {
			msg.addSegment(segment, DELIMITERS, true);
		}
		assertEquals(MESSAGE, msg.getValue());
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7.HL7Message#addSegment(org.jl7.hl7.HL7Message, org.jl7.hl7.HL7Segment)}
	 * .
	 */
	public void testAddSegmentHL7MessageHL7Segment() {
		HL7Message msg = new HL7Message();
		for (String segment : SEGMENTS) {
			HL7Message.addSegment(msg, HL7Parser.parseSegment(segment,
					DELIMITERS, true));
		}
		assertEquals(MESSAGE, msg.getValue());
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getCount()}.
	 */
	public void testGetCount() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		assertEquals(8, msg.getCount());
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getSegments()}.
	 */
	public void testGetSegments() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		List<HL7Segment> segments = msg.getSegments();
		assertNotNull(segments);
		assertEquals(SEGMENTS.length, segments.size());
		for (int i = 0; i < SEGMENTS.length; i++) {
			assertEquals(SEGMENTS[i], segments.get(i).getValue());
		}
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#get(int)}.
	 */
	public void testGetInt() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		for (int i = 0; i < msg.getCount(); i++) {
			assertEquals(SEGMENTS[i], msg.get(i).getValue());
		}
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#get(int)}.
	 */
	public void testGetIntReturnsNull() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		assertNull(msg.get(msg.getCount()));
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#get(java.lang.String)}.
	 */
	public void testGetString() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		List<HL7Segment> segments = msg.get("PID|PV1|ROL");
		HL7SegmentGroup group = new HL7SegmentGroup(segments);
		assertEquals(PID_PV1_ROL, group.getValue());
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#toString()}.
	 */
	public void testToString() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals(MESSAGE.replace("^~\\&", "\\S\\\\R\\\\E\\\\T\\"), msg
				.toString());
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getStructure()}.
	 */
	public void testGetStructure() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("MSH EVN PID PV1 PV2 ROL PR1 GT1", msg.getStructure());
	}

	/**
	 * Test method for {@link org.jl7.hl7.HL7Message#getEnumerator()}.
	 */
	public void testGetEnumerator() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		assertNotNull(msg);
		int i = 0;
		for (Enumeration<HL7Segment> e = msg.getEnumerator(); e
				.hasMoreElements();) {
			assertEquals(SEGMENTS[i], e.nextElement().getValue());
			i++;
		}
	}

}
