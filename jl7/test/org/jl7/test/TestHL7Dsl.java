/**
 *
 */
package org.jl7.test;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.jl7.dsl.HL7DSL;
import org.jl7.dsl.HL7GroovyMessage;
import org.jl7.hl7.HL7Message;
import org.junit.Test;

/**
 * @author henribenoit
 * 
 */
public class TestHL7Dsl extends TestCase {

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
     * {@link org.jl7.dsl.HL7DSL#convertMessage(java.io.File, org.jl7.dsl.HL7GroovyMessage)}
     * .
     */
    @Test
    public void testConvertMessage() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link org.jl7.dsl.HL7DSL#processMessage(java.io.File, org.jl7.dsl.HL7GroovyMessage)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testProcessMessage() throws IOException {
        HL7GroovyMessage message = new HL7GroovyMessage(new HL7Message());
        message.leftShift("MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII");
        message.leftShift("EVN|A08|20060705000000");
        message.leftShift("PID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA");
        message.leftShift("PV1|1|a|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V");
        message.leftShift("PV2|||||||1||||||||||||||||^^^^^^^^^103");
        message.leftShift("ROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1");
        message.leftShift("PR1|1||1||20060705|1");
        message.leftShift("GT1|1||||||||||||||||||||NOT APPLICABLE");
        File dsl = new File("scripts/test.parse.msg.txt");
        HL7DSL.processMessage(dsl, message.getMsg());
    }

}
