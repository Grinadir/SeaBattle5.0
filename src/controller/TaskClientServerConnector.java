package controller;

import javafx.concurrent.Task;
import model.ObserverOfModelIncomingMessage;

/**
 * Created by User on 20.06.2015.
 */
public class TaskClientServerConnector extends Task  {
    private model.ClientServerConnector clientServerConnector;
    private view.Gui gui;

    public TaskClientServerConnector(model.ClientServerConnector clientServerConnector, view.Gui gui) {
        this.clientServerConnector = clientServerConnector;
    }

    public void updateMess(String message) {
        this.updateMessage(message);
    }

    @Override
    protected Void call() throws Exception {
        clientServerConnector.call();
        return null;
    }




}
