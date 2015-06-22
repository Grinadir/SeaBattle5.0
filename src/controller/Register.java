package controller;

import sample.ClientServerConnector;

/**
 * Created by User on 21.06.2015.
 */
public class Register implements model.ObserverOfMessage {
    model.ClientServerConnector clientServerConnector;

    public Register(model.ClientServerConnector clientServerConnector) {
        this.clientServerConnector=clientServerConnector;
        clientServerConnector.registerObserver(this);

    }

        @Override
    public void update(String string) {

    }
}
