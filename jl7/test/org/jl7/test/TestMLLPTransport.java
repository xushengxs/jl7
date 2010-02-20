package org.jl7.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.TestCase;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.mllp.MLLPMetaData;
import org.jl7.mllp.MLLPTransport;
import org.jl7.mllp.MLLPTransportable;

public class TestMLLPTransport extends TestCase {

	private static final String HOST = "localhost";
	private static final int PORT_SEND = 12345;
	private static final int PORT_RECEIVE = 12346;
	private static final String MESSAGE = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII\r\nEVN|A08|20060705000000\r\nPID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\r\nPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\r\nPV2|||||||1||||||||||||||||^^^^^^^^^103\r\nROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\r\nPR1|1||1||20060705|1\r\nGT1|1||||||||||||||||||||NOT APPLICABLE";
	private static final String RESPONSE = "MSH|^~\\&|RIS||KIS||200404011706||ACK^A08^ACK|RIS002|P|2.5^DEU|||AL|NE|DEU|8859/1|DEU^^HL70296||2.16.840.1.113883.2.6.9.5^^2.16.840.1.113883.2.6^ISO|";
	private MLLPMetaData metaDataSend;
	private MLLPMetaData metaDataReceive;
	private ServerSocket listener;
	private Socket client;
	protected boolean stop;

	protected void setUp() throws Exception {
		super.setUp();
		metaDataSend = new MLLPMetaData();
		metaDataSend.host = HOST;
		metaDataSend.port = PORT_SEND;
		metaDataReceive = new MLLPMetaData(HOST, PORT_RECEIVE);
		stop = false;
		new Thread(new Runnable() {
			public void run() {
				try {
					while (!stop) {
						if ((listener == null) || (listener.isClosed())) {
							listener = new ServerSocket(PORT_SEND);
						}
						client = listener.accept();
						OutputStream outputStream = client.getOutputStream();
						outputStream.write(metaDataReceive.startByte);
						outputStream.write(RESPONSE.getBytes());
						outputStream.write(metaDataReceive.endByte);
						outputStream.write('\n');
						outputStream.flush();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while (!stop) {
					try {
						client = new Socket(HOST, PORT_RECEIVE);
						OutputStream outputStream = client.getOutputStream();
						outputStream.write(metaDataReceive.startByte);
						outputStream.write(MESSAGE.getBytes());
						outputStream.write(metaDataReceive.endByte);
						outputStream.write('\n');
						outputStream.flush();
						outputStream.close();
						client.close();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		stop = true;
		if (listener != null) {
			listener.close();
		}
	}

	public void testIsConnected() {
		MLLPTransport transport = new MLLPTransport();
		assertEquals(false, transport.isConnected());
		try {
			transport.connect(metaDataSend);
		} catch (UnknownHostException e) {
			fail("UnknownHostException: " + e);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(true, transport.isConnected());
		try {
			transport.disconnect();
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(false, transport.isConnected());
		try {
			transport.connect(HOST, PORT_SEND);
		} catch (UnknownHostException e) {
			fail("UnknownHostException: " + e);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(true, transport.isConnected());
		try {
			transport.disconnect();
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(false, transport.isConnected());
		try {
			transport.connect(null);
		} catch (UnknownHostException e) {
			fail("UnknownHostException: " + e);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(true, transport.isConnected());
		try {
			transport.disconnect();
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertEquals(false, transport.isConnected());
	}

	public void testListen() {
		fail("Not yet implemented");
	}

	public void testReceiveMessage() {
		MLLPTransport transport = new MLLPTransport();
		MLLPTransportable transportable = null;
		try {
			transportable = transport.receiveMessage(metaDataReceive);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertNotNull(transportable);
		assertNotNull(transportable.Message);
		System.out.println(transportable.Message);
	}

	public void testSendMessageNoWait() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		MLLPTransportable transportable = new MLLPTransportable();
		transportable.Message = msg;
		transportable.MetaData = metaDataSend;
		MLLPTransport transport = new MLLPTransport();
		try {
			transport.sendMessage(transportable);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		try {
			transport.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testSendMessageWait() {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		MLLPTransportable transportable = new MLLPTransportable();
		transportable.Message = msg;
		transportable.MetaData = metaDataSend;
		MLLPTransport transport = new MLLPTransport();
		MLLPTransportable response = null;
		try {
			response = transport.sendMessage(transportable, true);
		} catch (IOException e) {
			fail("IOException: " + e);
		}
		assertNotNull(response);
		HL7Message resMsg = response.Message;
		assertNotNull(resMsg);
		String type = resMsg.getMessageType();
		assertEquals("ACK", type);
		try {
			transport.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
