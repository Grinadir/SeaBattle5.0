package controller;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ClientServerConnector;
import model.ObserverOfModelIncomingMessage;


/**
 * Created by User on 17.06.2015.
 */
public class Controller extends Application implements model.ObserverOfMap, ObserverOfModelIncomingMessage, view.ObserverOfGuiSendingMessage {
    private view.Gui gui;
    private model.ClientServerConnector clientServerConnector;
    private TaskClientServerConnector taskClientServerConnector;

    public Controller() {
        this.gui = new view.Gui();
        clientServerConnector = new ClientServerConnector();
        taskClientServerConnector = new TaskClientServerConnector(clientServerConnector, gui);
        gui.setTaskConnection(taskClientServerConnector);
        System.out.println(taskClientServerConnector.getValue());
        clientServerConnector.registerObserver(this);
        gui.registerObserver(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        gui.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void update(int x, int y, String fettle) {

        if (fettle.equals("non")) {
            gui.getRects().getMyRect(x, y).setFill(Color.GREEN);
        }

        if (fettle.equals("nearship")) {
            gui.getRects().getMyRect(x, y).setFill(Color.YELLOW);
        }

        if (fettle.equals("ship")) {
            gui.getRects().getMyRect(x, y).setFill(Color.YELLOW);
        }

    }


    @Override
    public void update(String string) {
        taskClientServerConnector.updateMess(string);
    }

    @Override
    public void updateGuiSendingMessage(String string) {

        Service service = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                // TODO Auto-generated method stub
                return new TaskSendingMessage(clientServerConnector, string);
            }

        };
        service.start();

    }
}
