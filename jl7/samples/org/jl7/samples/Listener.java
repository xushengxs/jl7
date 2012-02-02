package org.jl7.samples;

import java.io.IOException;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.mllp.MLLPMetaData;
import org.jl7.mllp.MLLPTransport;
import org.jl7.mllp.MLLPTransportable;

public class Listener {
    private static final String HOST = "localhost";
    private static int PORT = 9991;
    private static MLLPMetaData metaData = new MLLPMetaData(HOST, PORT);

    /**
     * @param args
     */
    public static void main(String[] args) {
        MLLPTransport transport = new MLLPTransport();
        MLLPTransportable transportable = null;
        for (int i = 0; i < 10; i++) {
            try {
                transportable = transport.receiveMessageReconnectOnError(metaData);
                HL7Message msg = transportable.message;
                System.out.println(msg);
                HL7Message ack = HL7Parser.parseMessage("MSH|^~\\&|GC APP|GC FAC|ACME APP|ACME FAC|20071016055244||ACK^A01|20071016055244131|P|2.3.1|\r\n" + "MSA|AA|13463136|MSG Received Successfully|", true);
                MLLPTransportable response = new MLLPTransportable();
                response.message = ack;
                response.metaData = new MLLPMetaData(HOST, PORT);
                transport.sendMessage(response);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
