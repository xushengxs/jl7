package org.jl7.mllp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;

public class MLLPTransport {

    private Socket client = null;
    private String host = null;
    private int port = -1;

    private void connect() throws UnknownHostException, IOException {
        if (!isConnected()) {
            if ((host != null) && (port != -1)) {
                client = new Socket(host, port);
            }
        }
    }

    public void connect(MLLPMetaData metaData) throws UnknownHostException, IOException {
        if (metaData != null) {
            connect(metaData.host, metaData.port);
        }
        else {
            connect();
        }
    }

    public void connect(String host, int port) throws UnknownHostException, IOException {
        if (!host.equals(this.host)) {
            this.host = host;
        }
        if (port != this.port) {
            this.port = port;
        }
        connect();
    }

    public void disconnect() throws IOException {
        try {
            if (client != null) {
                client.close();
            }
        }
        finally {
            client = null;
        }
    }

    public boolean isConnected() {
        return (client == null ? false : client.isConnected());
    }

    public void listen(MLLPMetaData metaData) throws IOException {
        ServerSocket listener = new ServerSocket(metaData.port);
        disconnect();
        client = listener.accept();
    }

    public MLLPTransportable receiveMessage(MLLPMetaData metaData) throws IOException {
        listen(metaData);
        InputStream networkStream = client.getInputStream();
        int i;
        StringBuilder builder = new StringBuilder();
        boolean started = false;
        while ((i = networkStream.read()) != -1) {
            if (!started) {
                if (i == metaData.startByte) {
                    started = true;
                }
            }
            else if (i == metaData.endByte) {
                MLLPTransportable transportable = new MLLPTransportable();
                transportable.metaData = metaData;
                transportable.message = HL7Parser.parseMessage(builder.toString(), true);
                return transportable;
            }
            else {
                builder.append((char) i);
            }
        }
        return null;
    }

    public MLLPTransportable receiveResponse(MLLPMetaData metaData) throws IOException {
        InputStream networkStream = client.getInputStream();
        int i;
        StringBuilder builder = new StringBuilder();
        boolean started = false;
        while ((i = networkStream.read()) != -1) {
            if (!started) {
                if (i == metaData.startByte) {
                    started = true;
                }
            }
            else if (i == metaData.endByte) {
                MLLPTransportable transportable = new MLLPTransportable();
                transportable.metaData = metaData;
                transportable.message = HL7Parser.parseMessage(builder.toString(), true);
                return transportable;
            }
            else {
                builder.append((char) i);
            }
        }
        return null;
    }

    public void sendMessage(MLLPTransportable transportable) throws IOException {
        sendMessage(transportable, false);
    }

    public MLLPTransportable sendMessage(MLLPTransportable transportable, boolean waitForResponse) throws IOException {
        connect(transportable.metaData);
        OutputStream networkStream = client.getOutputStream();
        try {
            MLLPMetaData metaData = transportable.metaData;
            networkStream.write(metaData.startByte);
            OutputStreamWriter streamWriter = new OutputStreamWriter(networkStream);
            HL7Message message = transportable.message;
            // String characterSet = Hl7MessageExtractor.ExtractString(message,
            // "MSH|18");
            // streamWriter.Encoding = ... characterSet
            streamWriter.write(message.getValue());
            streamWriter.flush();
            networkStream.write(metaData.endByte);
            networkStream.write((byte) HL7Message.segmentTerminator);
            networkStream.flush();
            if (waitForResponse) {
                return receiveResponse(metaData);
            }
        }
        finally {
            networkStream.close();
        }
        return null;
    }
}
