/**
 * 
 */
package org.jl7.comm;

import java.io.IOException;

import org.jl7.hl7.HL7Message;

// TODO: Auto-generated Javadoc
/**
 * The Interface HL7Reader.
 *
 * @author henribenoit
 */
public interface HL7Reader {

    /**
     * Closes the reader.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void close() throws java.io.IOException;

    /**
     * Gets the next message.
     *
     * @return the next message
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HL7Message getMessage() throws IOException;

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public Object[] getParameters();

    /**
     * Checks whether the reader is still open.
     *
     * @return true if the reader is still open
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public boolean isOpen() throws IOException;

    /**
     * Opens the reader.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void open() throws IOException;
}
