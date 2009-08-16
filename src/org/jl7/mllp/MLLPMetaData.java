package org.jl7.mllp;

public class MLLPMetaData {
	public MLLPMetaData() {
		startByte = 0x0B;
		endByte = 0x1C;
	}

	public String host;
	public int port;
	public byte startByte;
	public byte endByte;
}
