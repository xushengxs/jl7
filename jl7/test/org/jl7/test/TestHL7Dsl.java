/**
 *
 */
package org.jl7.test;

import java.io.File;
import java.io.IOException;

import org.jl7.dsl.HL7DSL;
import org.jl7.dsl.HL7GroovyMessage;
import org.jl7.hl7.HL7Message;

import junit.framework.TestCase;

/**
 * @author henribenoit
 *
 */
public class TestHL7Dsl extends TestCase {

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.jl7.dsl.HL7DSL#processMessage(java.io.File, org.jl7.dsl.HL7GroovyMessage)}.
     * @throws IOException
     */
    public void testProcessMessage() throws IOException {
        File dsl = new File("scripts/test.parse.msg.txt");
        HL7GroovyMessage message = new HL7GroovyMessage(new HL7Message());
        HL7DSL.processMessage(dsl, message);
    }

    /**
     * Test method for {@link org.jl7.dsl.HL7DSL#convertMessage(java.io.File, org.jl7.dsl.HL7GroovyMessage)}.
     */
    public void testConvertMessage() {
        fail("Not yet implemented");
    }

}
