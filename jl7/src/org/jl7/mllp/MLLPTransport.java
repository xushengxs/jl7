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

// TODO: Auto-generated Javadoc
/**
 * The Class MLLPTransport.
 */
public class MLLPTransport {

    /** The client. */
    private Socket client = null;

    /** The host. */
    private String host = null;

    /** The port. */
    private int port = -1;

    private ServerSocket listener;

    /**
     * Connect.
     * 
     * @throws UnknownHostException
     *             the unknown host exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void connect() throws UnknownHostException, IOException {
        if (!isConnected()) {
            if ((host != null) && (port != -1)) {
                client = new Socket(host, port);
            }
        }
    }

    /**
     * Connect.
     * 
     * @param metaData
     *            the meta data
     * @throws UnknownHostException
     *             the unknown host exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void connect(MLLPMetaData metaData) throws UnknownHostException, IOException {
        if (metaData != null) {
            connect(metaData.host, metaData.port);
        }
        else {
            connect();
        }
    }

    /**
     * Connect.
     * 
     * @param host
     *            the host
     * @param port
     *            the port
     * @throws UnknownHostException
     *             the unknown host exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void connect(String host, int port) throws UnknownHostException, IOException {
        if (!host.equals(this.host)) {
            this.host = host;
        }
        if (port != this.port) {
            this.port = port;
        }
        connect();
    }

    /**
     * Disconnect.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void disconnect() throws IOException {
        try {
            if (client != null) {
                client.close();
            }
        }
        finally {
            client = null;
        }
        try {
            if (listener != null) {
                listener.close();
            }
        }
        finally {
            listener = null;
        }
    }

    /**
     * Checks if is connected.
     * 
     * @return true, if is connected
     */
    public boolean isConnected() {
        return (client == null ? false : client.isConnected());
    }

    /**
     * Listen.
     * 
     * @param port
     * @throws IOException
     */
    private void listen(int port) throws IOException {
        disconnect();
        listener = new ServerSocket(port);
        client = listener.accept();
    }

    /**
     * Listen.
     * 
     * @param metaData
     *            the meta data
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void listen(MLLPMetaData metaData) throws IOException {
        listen(metaData.port);
    }

    /**
     * Receive message.
     * 
     * @param metaData
     *            the meta data
     * @return the MLLP transportable
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public MLLPTransportable receiveMessage(MLLPMetaData metaData) throws IOException {
        listen(metaData);
        return receiveMessageOnExistingConnection(metaData);
    }

    /**
     * @param metaData
     * @return
     * @throws IOException
     */
    public MLLPTransportable receiveMessageOnExistingConnection(MLLPMetaData metaData) throws IOException {
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

    /**
     * Receive message and reconnect on IO error.
     * 
     * @param metaData
     *            the meta data
     * @return the MLLP transportable
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public MLLPTransportable receiveMessageReconnectOnError(MLLPMetaData metaData) throws IOException {
        while (true) {
            try {
                if (client == null) {
                    listen(metaData);
                }
                MLLPTransportable message = receiveMessageOnExistingConnection(metaData);
                if (message != null) {
                    return message;
                }
                else {
                    disconnect();
                }

            }
            catch (IOException e) {
                disconnect();
                listen(metaData);
            }
        }
    }

    /**
     * Receive response.
     * 
     * @param metaData
     *            the meta data
     * @return the mLLP transportable
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
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

    /**
     * Send message.
     * 
     * @param transportable
     *            the transportable
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void sendMessage(MLLPTransportable transportable) throws IOException {
        sendMessage(transportable, false);
    }

    /**
     * Send message.
     * 
     * @param transportable
     *            the transportable
     * @param waitForResponse
     *            the wait for response
     * @return the mLLP transportable
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public MLLPTransportable sendMessage(MLLPTransportable transportable, boolean waitForResponse) throws IOException {
        connect(transportable.metaData);
        OutputStream networkStream = client.getOutputStream();
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
        return null;
    }
}
