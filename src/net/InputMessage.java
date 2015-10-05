package net;


import model.ObservableModelIncomingMessage;
import model.ObserverOfModelIncomingMessage;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class InputMessage implements ObservableModelIncomingMessage {

    private ClientServerConnector connector;
    private DataInputStream dataInputStreamFromInputMessage;
    private ArrayList observers;
    private String strForTest;

    public InputMessage(ClientServerConnector connector, DataInputStream in) {
        this.connector = connector;
        this.dataInputStreamFromInputMessage = in;
        observers = new ArrayList();
    }

    public String getStrForTest() {
        return strForTest;
    }

    public void inputMessageHandler() throws IOException {
        String line;
        line = dataInputStreamFromInputMessage.readUTF();
        System.out.println(line);
        //notify(line);
        connector.updateMessage(line);

        strForTest = line;

    }


    @Override
    public void registerObserver(ObserverOfModelIncomingMessage o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(ObserverOfModelIncomingMessage o) {

    }

    @Override
    public void notify(String message) {
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfModelIncomingMessage observer = (ObserverOfModelIncomingMessage) observers.get(i);
            observer.updateModelIncomingMessage(message);
        }

    }
}
