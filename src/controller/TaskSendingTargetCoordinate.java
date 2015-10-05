package controller;

import javafx.concurrent.Task;
import net.ClientServerConnector;
import net.SendingTargetCoordinate;

/**
 * Created by User on 01.07.2015.
 */
public class TaskSendingTargetCoordinate extends Task {

    private SendingTargetCoordinate sendingTargetCoordinate;

    public TaskSendingTargetCoordinate(model.Engine engine, ClientServerConnector clientServerConnector) {
        this.sendingTargetCoordinate = new SendingTargetCoordinate(engine, clientServerConnector);


    }

    @Override
    protected Object call() throws Exception {
        sendingTargetCoordinate.call();

        return null;
    }


}
