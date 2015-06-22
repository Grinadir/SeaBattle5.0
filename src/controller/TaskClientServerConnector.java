package controller;

import javafx.concurrent.Task;

/**
 * Created by User on 20.06.2015.
 */
public class TaskClientServerConnector extends Task implements model.ObserverOfMessage {
    private model.ClientServerConnector clientServerConnector;
    private view.Gui gui;

    public TaskClientServerConnector(model.ClientServerConnector clientServerConnector, view.Gui gui){
        this.clientServerConnector=clientServerConnector;
        this.gui=gui;
        //this.clientServerConnector.getInputMessage().registerObserver(this);
    }

    @Override
    protected Void call() throws Exception {
        clientServerConnector.call();
        return null;
    }

    @Override
    public void update(String message) {
        gui.setTextInCommonChat(message);
        System.out.println("DDDDDDDDDDDD");
    }


}
