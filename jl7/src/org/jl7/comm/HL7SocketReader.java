/**
 * 
 */
package org.jl7.comm;

import java.io.IOException;

import org.jl7.hl7.HL7Message;
import org.jl7.mllp.MLLPMetaData;
import org.jl7.mllp.MLLPTransport;

/**
 * @author henribenoit
 * 
 */
public class HL7SocketReader implements HL7Reader {
	private MLLPMetaData metaData;
	private MLLPTransport transport;

	public HL7SocketReader(MLLPMetaData metaData) {
		super();
		this.metaData = metaData;
	}

	/**
	 * @param host
	 * @param port
	 */
	public HL7SocketReader(String host, int port) {
		super();
		metaData = new MLLPMetaData();
		metaData.host = host;
		metaData.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#close()
	 */
	public void close() throws IOException {
		transport.disconnect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#getMessage()
	 */
	public HL7Message getMessage() throws IOException {
		return transport.receiveMessage(metaData).Message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#isOpen()
	 */
	public boolean isOpen() throws IOException {
		return ((transport != null) && (transport.isConnected()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#open()
	 */
	public void open() throws IOException {
		transport = new MLLPTransport();
		transport.connect(metaData);
	}

}
