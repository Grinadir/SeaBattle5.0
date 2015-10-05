package controller;

import javafx.concurrent.Task;
import net.ClientServerConnector;

/**
 * Created by User on 20.06.2015.
 */
public class TaskClientServerConnector extends Task {
    private ClientServerConnector clientServerConnector;

    public TaskClientServerConnector(ClientServerConnector clientServerConnector) {
        this.clientServerConnector = clientServerConnector;
    }

    public void updateMessage(String message) {
        super.updateMessage(message);
    }

    @Override
    protected Void call() throws Exception {
        clientServerConnector.call();
        return null;
    }


}
