package controller;

import javafx.concurrent.Task;
import model.ObserverOfModelIncomingMessage;

/**
 * Created by User on 20.06.2015.
 */
public class TaskClientServerConnector extends Task implements ObserverOfModelIncomingMessage {
    private model.ClientServerConnector clientServerConnector;
    private view.Gui gui;

    public TaskClientServerConnector(model.ClientServerConnector clientServerConnector, view.Gui gui) {
        this.clientServerConnector = clientServerConnector;

        //this.clientServerConnector.getInputMessage().registerObserver(this);
    }

    public void updateMess(String str) {
        this.updateMessage(str);
    }

    @Override
    protected Void call() throws Exception {
        clientServerConnector.call();
        return null;
    }

    @Override
    public void update(String message) {
        gui.setTextInCommonChat(message);
        System.out.println("??Must Missing?");
    }


}
