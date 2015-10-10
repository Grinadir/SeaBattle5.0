package net;


import java.io.DataInputStream;
import java.io.IOException;


public class InputMessage {

    private ObservableClientServerConnector connector;
    private DataInputStream dataInputStreamFromInputMessage;
    private String strForTest;

    public InputMessage(ObservableClientServerConnector connector, DataInputStream in) {
        this.connector = connector;
        this.dataInputStreamFromInputMessage = in;
    }

    public String getStrForTest() {
        return strForTest;
    }

    public void inputMessageHandler() throws IOException {
        String line;
        line = dataInputStreamFromInputMessage.readUTF();
        System.out.println(line);
        connector.updateMessage(line);
        strForTest = line;

    }
}
