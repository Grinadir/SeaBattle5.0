package controller;

import javafx.concurrent.Task;


/**
 * Created by User on 22.06.2015.
 */
public class TaskSendingMessage extends Task {

    private model.SendingMessage sendingMessage;

    public TaskSendingMessage(model.ClientServerConnector clientServerConnector, String strSendMess) {
        this.sendingMessage = new model.SendingMessage(clientServerConnector, strSendMess);
    }

    @Override
    protected Object call() throws Exception {
        sendingMessage.call();
        return null;
    }

    public void updateMessage(String message) {
        super.updateMessage(message);
    }
}
