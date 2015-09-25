package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SystemOfIncomingMessage {

    private ClientServerConnector connector;
    private InputMessage inputMessage;

    SystemOfIncomingMessage(ClientServerConnector connector) {
        this.connector = connector;
    }

    public InputMessage getInputMessage() {
       return inputMessage;
    }

    public void mainFunctionOfIncomingMessage() throws IOException {
        if (connector.getServer().isClosed()) {
            waitingForIncomingMessage(connector.getClient().getInputClientStream());
        } else {
            waitingForIncomingMessage(connector.getServer().getInputServerStream());
        }
    }

    private void waitingForIncomingMessage(InputStream inputStream) throws IOException {
        DataInputStream stream = new DataInputStream(inputStream);
        while (true) {
            inputMessage = new InputMessage(connector, stream);
            inputMessage.inputMessageHandler();
        }
    }
}
