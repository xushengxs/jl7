package org.jl7.test;

import junit.framework.TestCase;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7SegmentGroup;
import org.jl7.hl7proc.Hl7MessageExtractor;

public class TestHl7MessageExtractor extends TestCase {
	private static final String MESSAGE = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII\rEVN|A08|20060705000000\rPID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE";

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNullMessage() {
		assertEquals(null, Hl7MessageExtractor.ExtractString((HL7Message) null,
				""));
	}

	public void testExtractMessage() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals(MESSAGE, Hl7MessageExtractor.ExtractString(message, ""));
	}

	public void testExtractSegment() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals(
				"PID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA",
				Hl7MessageExtractor.ExtractString(message, "PID"));
	}

	public void testExtractField() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("0000112234^^1&2&3^100^A", Hl7MessageExtractor
				.ExtractString(message, "PID|3"));
	}

	public void testExtractRepetition() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("YYYY^^A&B&C^^^^F", Hl7MessageExtractor.ExtractString(
				message, "PID|5~2"));
	}

	public void testExtractComponentInRepetition() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("F", Hl7MessageExtractor.ExtractString(message,
				"PID|5~2^7"));
	}

	public void testExtractComponent() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("100", Hl7MessageExtractor.ExtractString(message,
				"PID|3^4"));
	}

	public void testExtractSubcomponent() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("2", Hl7MessageExtractor.ExtractString(message,
				"PID|3^3&2"));
	}

	public void testExtractSubcomponentInRepetition() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(message,
				"PID|5~2^3&3"));
	}

	public void testExtractSubcomponentInRepetitionFromGroup() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(
				new HL7SegmentGroup(message.get("PID|PV1|PV2")), "PID|5~2^3&3"));
	}

	public void testExtractSubcomponentInRepetitionFromSegment() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(
				message.get("PID").get(0), "|5~2^3&3"));
	}

	public void testExtractSubcomponentInRepetitionFromField() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(
				message.get("PID").get(0).get(5), "~2^3&3"));
	}

	public void testExtractSubcomponentFromRepetition() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(
				message.get("PID").get(0).get(5).get(1), "^3&3"));
	}

	public void testExtractSubcomponentFromComponent() {
		HL7Message message = HL7Parser.parseMessage(MESSAGE, true);
		assertEquals("C", Hl7MessageExtractor.ExtractString(
				message.get("PID").get(0).get(5).get(1).get(2), "&3"));
	}
}
