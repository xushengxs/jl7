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
    String A01 = "MSH|^~\\&|AccMgr|1|||20050110045504||ADT^A01|599102|P|2.3|||\rEVN|A01|20050110045502|||||\rPID|1||10006579^^^1^MRN^1||DUCK^DONALD^D||19241010|M||1|111 DUCK ST^^FOWL^CA^999990000^^M|1|8885551212|8885551212|1|2||40007716^^^AccMgr^VN^1|123121234|||||||||||NO\rNK1|1|DUCK^HUEY|SO|3583 DUCK RD^^FOWL^CA^999990000|8885552222||Y||||||||||||||\rPV1|1|I|PREOP^101^1^1^^^S|3|||37^DISNEY^WALT^^^^^^AccMgr^^^^CI|||01||||1|||37^DISNEY^WALT^^^^^^AccMgr^^^^CI|2|40007716^^^AccMgr^VN|4|||||||||||||||||||1||G|||20050110045253||||||\rGT1|1|8291|DUCK^DONALD^D||111^DUCK ST^^FOWL^CA^999990000|8885551212||19241010|M||1|123121234||||#Cartoon Ducks Inc|111^DUCK ST^^FOWL^CA^999990000|8885551212||PT|\rDG1|1|I9|71596^OSTEOARTHROS NOS-L/LEG ^I9|OSTEOARTHROS NOS-L/LEG ||A|\rIN1|1|MEDICARE|3|MEDICARE|||||||Cartoon Ducks Inc|19891001|||4|DUCK^DONALD^D|1|19241010|111^DUCK ST^^FOWL^CA^999990000|||||||||||||||||123121234A||||||PT|M|111 DUCK ST^^FOWL^CA^999990000|||||8291\rIN2|1||123121234|Cartoon Ducks Inc|||123121234A|||||||||||||||||||||||||||||||||||||||||||||||||||||||||8885551212\rIN1|2|NON-PRIMARY|9|MEDICAL MUTUAL CALIF.|PO BOX 94776^^HOLLYWOOD^CA^441414776||8003621279|PUBSUMB|||Cartoon Ducks Inc||||7|DUCK^DONALD^D|1|19241010|111 DUCK ST^^FOWL^CA^999990000|||||||||||||||||056269770||||||PT|M|111^DUCK ST^^FOWL^CA^999990000|||||8291\rIN2|2||123121234|Cartoon Ducks Inc||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||8885551212\rIN1|3|SELF PAY|1|SELF PAY|||||||||||5||1";
    String P01 = "MSH|^~\\&|ENDOBASE IV^ ENDOBASE IV^GUID||AHC_HIS_Port^AHC_HIS_Port^GUID||20000908064603||BAR^P01|L1BD-JB-1C-B4-022AE4|P|2.3|AL|NE\rEVN|P03|20000908084603\rPID|1||1235||JONES^SARAH^A|WILLIAMS|19610615000000|F||AMERICAN|1200 N ELM STREET^^GREENSBORO^^27401-1020||(919)379-1212|(919)271-3434|||||123456789||||GREENSBORO|||||^AMERICAN|20011231063000\rPV1|1|||||||001^Miller^Gary^^^Dr.|||||||||002^Webster^Daniel^^^Dr.||20310\rDG1|1|EBM|1^Ordinationsgebühr je Beh.sfall (0)|Ordinationsgebühr je Beh.sfall (0)|20000726000000\rDG1|2|EBM|741^Gastroskopie (1400)|Gastroskopie (1400)|20000726000000\rDG1|3|EBM|7026^3-D-Technik Zuschlag (600)|3-D-Technik Zuschlag (600)|20000726000000\rPR1|1|GoÄ|1^Beratung [auch telefonisch]||20000726000000||10\rPR1|2|GoÄ|253^Injektion, i.v.||20000726000000||2\rPR1|3|GoÄ|683^Gastro- + Ösophagoskopie [vollflexible Instr.]||20000726000000||20\rPR1|4|GoÄ|4071^5-HIES||20000726000000||10";
    String P03 = "MSH|^~\\&|EB^ EB^GUID||DPS^DPS^GUID||20000908064603||DFT^P03|L1BD-JB-1C-B4-022AE4|P|2.3|AL|NE\rEVN|P03|20000908084603\rPID|1||1235||JONES^BETTY^A|SMITH|19610615000000|F||American|1200 N ELM STREET^^GREENSBORO^^27401-1020||(919)379-1212|(919)271-3434|||||123456789||||New York|||||^American|20010131\rPV1|1|||||||003^Lance^William^^^Dr.|||||||||004^Smith^Willma^^^Dr.||1234532\rFT1|1|1234545||20010116000000||Billing|Bill^Test Biling Code|||1.00||123.00|ENDOBASE IV|||004||||U200^Ima^Rin^^^Dr.|112233^Hausarzt^Paul^^^Dr.^^^Privat\rFT1|2|1234545||20010116000000||Billing|B123^Billing 2|||2.00||23.23|ENDOBASE IV|||004||||U200^Ima^Rin^^^Dr.|112233^Hausarzt^Paul^^^Dr.^^^Privat\rFT1|3||||| |||||||ENDOBASE IV|||004||||U200^Ima^Rin^^^Dr.|112233^Hausarzt^Paul^^^Dr.^^^Privat\rPR1|1|GoÄ|1^Beratung [auch telefonisch]||20000726000000||1.00\rPR1|2|GoÄ|253^Injektion, i.v.||20000726000000||1.00\rPR1|3|GoÄ|683^Gastro- + Ösophagoskopie [vollflexible Instr.]||20000726000000||1.00\rPR1|4|GoÄ|4071^5-HIES||20000726000000||1.00\rDG1|1|EBM|1^Ordinationsgebühr je Beh.sfall (0)|Ordinationsgebühr je Beh.sfall00(0)|20000726000000\rDG1|2|EBM|741^Gastroskopie (1400)|Gastroskopie (1400)|20000726000000\rDG1|3|EBM|7026^3-D-Technik Zuschlag (600)|3-D-Technik Zuschlag (600)|20000726000000\rIN1|1||1234|AnyInsurance||||||||||||||||||||||||||||||||456667878";
    String O01 = "MSH|^~\\&|SPC|M||M|20040503223716||ORM^O01|176201653|P|2.2|\rPID|1||0000307656^^^M&FEE&&FIE&&FOO&&FUM^MR~0000858462^^^P&FOO&BAR^MR\rOBR|1||3844834|2035^NM HEPATOBILIARY DUCT^MRD|||200405030939||\rOBR|2||44834|21493^RADIATION THERAPY PLANNING|||200406020800||";

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
        HL7Message msg = HL7Parser.parseMessage(A01, true);
        List<HL7SegmentGroup> insurances = HL7MessageSplitter.GetInsurances(msg);
        assertNotNull(insurances);
        assertEquals(3, insurances.size());
        assertEquals("PID NK1 PV1 GT1 DG1 IN1 IN2", insurances.get(0).getStructure());
        assertEquals("PID NK1 PV1 GT1 DG1 IN1 IN2", insurances.get(1).getStructure());
        assertEquals("PID NK1 PV1 GT1 DG1 IN1", insurances.get(2).getStructure());
        assertEquals("MEDICARE", Hl7MessageExtractor.ExtractString(insurances.get(0), "IN1|4"));
        assertEquals("MEDICAL MUTUAL CALIF.", Hl7MessageExtractor.ExtractString(insurances.get(1), "IN1|4"));
        assertEquals("SELF PAY", Hl7MessageExtractor.ExtractString(insurances.get(2), "IN1|4"));
    }

    /**
     * Test method for
     * {@link org.jl7.hl7proc.HL7MessageSplitter#GetOrders(org.jl7.hl7.HL7Message)}
     * .
     */
    public void testGetOrders() {
        HL7Message msg = HL7Parser.parseMessage(O01, true);
        List<HL7SegmentGroup> orders = HL7MessageSplitter.GetOrders(msg);
        assertEquals(2, orders.size());
        assertEquals(
                "PID|1||0000307656^^^M&FEE&&FIE&&FOO&&FUM^MR~0000858462^^^P&FOO&BAR^MR\rOBR|1||3844834|2035^NM HEPATOBILIARY DUCT^MRD|||200405030939||",
                orders.get(0).getValue());
        assertEquals(
                "PID|1||0000307656^^^M&FEE&&FIE&&FOO&&FUM^MR~0000858462^^^P&FOO&BAR^MR\rOBR|2||44834|21493^RADIATION THERAPY PLANNING|||200406020800||",
                orders.get(1).getValue());
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
     * {@link org.jl7.hl7proc.HL7MessageSplitter#GetProcedures(org.jl7.hl7.HL7Message)}
     * .
     */
    public void testGetProcedures() {
        HL7Message msg = HL7Parser.parseMessage(A19, true);
        List<HL7SegmentGroup> procedures = HL7MessageSplitter.GetProcedures(msg);
        assertNotNull(procedures);
        assertEquals(3, procedures.size());
        assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(0).getStructure());
        assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(1).getStructure());
        assertEquals("PID NK1 PV1 DG1 DG1 DG1 PR1", procedures.get(2).getStructure());
        assertEquals("1-502.6", Hl7MessageExtractor.ExtractString(procedures.get(0), "PR1|3^1"));
        assertEquals("5-940", Hl7MessageExtractor.ExtractString(procedures.get(1), "PR1|3^1"));
        assertEquals("5-900", Hl7MessageExtractor.ExtractString(procedures.get(2), "PR1|3^1"));
    }

    /**
     * Test method for
     * {@link org.jl7.hl7proc.HL7MessageSplitter#GetVisits(org.jl7.hl7.HL7Message)}
     * .
     */
    public void testGetVisits() {
        fail("Not yet implemented");
    }

}
