package controller;

import javafx.concurrent.Task;


/**
 * Created by User on 22.06.2015.
 */
public class TaskSendingMessage extends Task {

    private model.SendingMessage sendingMessage;
    private model.ClientServerConnector clientServerConnector;
    String message;
    public TaskSendingMessage(model.ClientServerConnector clientServerConnector){
        this.sendingMessage=new model.SendingMessage(clientServerConnector);
    }

    @Override
    protected Object call() throws Exception {
        sendingMessage.call();
        return null;
    }
}
