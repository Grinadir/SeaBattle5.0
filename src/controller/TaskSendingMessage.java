package controller;

import javafx.concurrent.Task;
import net.ClientServerConnector;
import net.SendingMessage;


/**
 * Created by User on 22.06.2015.
 */
public class TaskSendingMessage extends Task {

    private SendingMessage sendingMessage;

    public TaskSendingMessage(ClientServerConnector clientServerConnector, String strSendMess) {
        this.sendingMessage = new SendingMessage(clientServerConnector, strSendMess);
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
