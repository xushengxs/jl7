package org.jl7.mllp;

public class MLLPMetaData {
	public MLLPMetaData() {
		startByte = 0x0B;
		endByte = 0x1C;
	}

	public MLLPMetaData(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public MLLPMetaData(String host, int port, byte startByte, byte endByte) {
		super();
		this.host = host;
		this.port = port;
		this.startByte = startByte;
		this.endByte = endByte;
	}

	public String host;
	public int port;
	public byte startByte;
	public byte endByte;
}
