package org.jl7.mllp;

public class MLLPMetaData {
	public MLLPMetaData() {
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
	public byte startByte = 0x0B;
	public byte endByte = 0x1C;
}
