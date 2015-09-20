package controller;

import javafx.concurrent.Task;
import model.SendingTargetCoordinate;

/**
 * Created by User on 01.07.2015.
 */
public class TaskSendingTargetCoordinate extends Task {

    private model.SendingTargetCoordinate sendingTargetCoordinate;

    public TaskSendingTargetCoordinate(model.Engine engine, model.ClientServerConnector clientServerConnector){
        this.sendingTargetCoordinate=new SendingTargetCoordinate(engine, clientServerConnector);


    }

    @Override
    protected Object call() throws Exception {
        this.call();

        return null;
    }
}
