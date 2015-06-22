package model;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class InputMessage implements ObservableMessage {



    private ClientServerConnector connector;
    private DataInputStream dataInputStreamFromInputMessage;
    private ArrayList observers;


    public InputMessage(ClientServerConnector connector, DataInputStream in) {
        this.connector = connector;
        this.dataInputStreamFromInputMessage = in;
    }

    public void inputMessageHandler() throws IOException {
        String line;
        line = dataInputStreamFromInputMessage.readUTF();

    }


    @Override
    public void registerObserver(ObserverOfMessage o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(ObserverOfMessage o) {

    }

    @Override
    public void notify(String message) {
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfMessage observer = (ObserverOfMessage) observers.get(i);
            observer.update(message);
        }

    }
}
