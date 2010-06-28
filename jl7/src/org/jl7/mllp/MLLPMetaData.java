package org.jl7.mllp;

// TODO: Auto-generated Javadoc
/**
 * The Class MLLPMetaData.
 */
public class MLLPMetaData {
    
    /** The host. */
    public String host;

    /** The port. */
    public int port;

    /** The start byte. */
    public byte startByte = 0x0B;

    /** The end byte. */
    public byte endByte = 0x1C;

    /**
     * Instantiates a new mLLP meta data.
     */
    public MLLPMetaData() {
    }

    /**
     * Instantiates a new mLLP meta data.
     *
     * @param host the host
     * @param port the port
     */
    public MLLPMetaData(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    /**
     * Instantiates a new mLLP meta data.
     *
     * @param host the host
     * @param port the port
     * @param startByte the start byte
     * @param endByte the end byte
     */
    public MLLPMetaData(String host, int port, byte startByte, byte endByte) {
        super();
        this.host = host;
        this.port = port;
        this.startByte = startByte;
        this.endByte = endByte;
    }
}
