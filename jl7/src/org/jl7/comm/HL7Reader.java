/**
 * 
 */
package org.jl7.comm;

import java.io.IOException;

import org.jl7.hl7.HL7Message;

/**
 * @author henribenoit
 * 
 */
public interface HL7Reader {

	/**
	 * Gets the next message
	 * 
	 * @return the next message
	 * @throws IOException
	 */
	public HL7Message getMessage() throws IOException;

	/**
	 * Opens the reader
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException;

	/**
	 * Closes the reader
	 * 
	 * @throws java.io.IOException
	 */
	public void close() throws java.io.IOException;

	/**
	 * Checks whether the reader is still open
	 * 
	 * @return true if the reader is still open
	 * @throws IOException
	 */
	public boolean isOpen() throws IOException;
}
