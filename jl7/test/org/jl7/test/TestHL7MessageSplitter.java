/**
 * 
 */
package org.jl7.test;

import java.util.List;

import junit.framework.TestCase;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.hl7.HL7SegmentGroup;
import org.jl7.hl7proc.HL7MessageSplitter;
import org.jl7.hl7proc.Hl7MessageExtractor;

/**
 * @author henribenoit
 * 
 */
public class TestHL7MessageSplitter extends TestCase {
	String A19 = "MSH|^~\\&|CommServer||KIS||200811111017||ADR^A19||P|2.2\rMSA|AA\rQRD|200811111016|R|I|Q1004|||1^RD|10000437363|DEM\rPID|||10000437363|508003|Bauer^Fritz^^^||19631101|M|||Mercedesstr 12^^Bergheim^^68123^D|||||M|\rNK1|1|Bauer^Karin|Ehefrau\rPV1||S|CHI1^2W^1^CHI|R||||20 56 344^Antonius^ Markus^^^Dr.med.^^^Königstr. 112^69939^Haarheim/M.^06146^61011|20 56 344^Antonius^Markus^^^Dr.med.^^^Königstr. 112^69939^Haarheim/M.^06146^61011|N|||||||||9800703||K|||||||||||||||||||||||200311110928\rDG1|1||355.9^355.9 Neuropathie onA^I9|||EL|||||||||1\rDG1|2||386.-^386.- Schwindel^I9|||EL|||||||||2\rDG1|3||087.9^087.9 Borreliose^I9|||EL|||||||||3\rPR1|1||1-502.6^1-502.6 Biopsie durch Inzision am Unterschenkel^ICPM||20031107|P\rPR1|2||5-940^5-940 Operationslagerung^ICPM|||P\rPR1|3||5-900^5-900 Einfache Wiederherstellung der Kontinuität an Haut und Unterhaut^ICPM|||P\rIN1|1|0||NAK|Innenstr. 52 ^^Hannover^^30014||||||||207714 ||10035|Bauer^Fritz||19631101|Mercedesstr 12^^Bergheim^^68123";

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
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetPatients(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetPatients() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetVisits(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetVisits() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetProcedures(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetProcedures() {
		HL7Message msg = HL7Parser.parseMessage(A19, true);
		List<HL7SegmentGroup> procedures = HL7MessageSplitter
				.GetProcedures(msg);
		assertNotNull(procedures);
		assertEquals(3, procedures.size());
		assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(0)
				.getStructure());
		assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(1)
				.getStructure());
		assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(2)
				.getStructure());
		assertEquals("1-502.6", Hl7MessageExtractor.ExtractString(procedures
				.get(0), "PR1|3^1"));
		assertEquals("5-940", Hl7MessageExtractor.ExtractString(procedures
				.get(1), "PR1|3^1"));
		assertEquals("5-900", Hl7MessageExtractor.ExtractString(procedures
				.get(2), "PR1|3^1"));
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetGuarantors(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetGuarantors() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetInsurances(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetInsurances() {
		HL7Message msg = HL7Parser.parseMessage(A19, true);
		List<HL7SegmentGroup> insurances = HL7MessageSplitter
				.GetInsurances(msg);
		assertNotNull(insurances);
		assertEquals(1, insurances.size());
		assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1 PR1 PR1 IN1", insurances.get(
				0).getStructure());
		assertEquals("Hannover", Hl7MessageExtractor.ExtractString(insurances
				.get(0), "IN1|5^3"));
	}

	/**
	 * Test method for
	 * {@link org.jl7.hl7proc.HL7MessageSplitter#GetOrders(org.jl7.hl7.HL7Message)}
	 * .
	 */
	public void testGetOrders() {
		fail("Not yet implemented");
	}

}
