package controller;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ClientServerConnector;
import model.Engine;
import model.ObserverOfModelIncomingMessage;


/**
 * Created by User on 17.06.2015.
 */
public class Controller extends Application implements model.ObserverOfMap, ObserverOfModelIncomingMessage, view.ObserverOfGuiSendingMessage, view.ObserverOfGuiMyRectangle {
    private view.Gui gui;
    private model.ClientServerConnector clientServerConnector;
    private TaskClientServerConnector taskClientServerConnector;
    private Engine engine;

    public Controller() {
        this.gui = new view.Gui();
        clientServerConnector = new ClientServerConnector();
        taskClientServerConnector = new TaskClientServerConnector(clientServerConnector);
        gui.setTaskConnection(taskClientServerConnector);
        System.out.println(taskClientServerConnector.getValue());
        clientServerConnector.registerObserver(this);
        gui.registerObserver(this);
        engine = new Engine();
        engine.getMap().registerObserver(this);
        engine.getLogicMarked().registerObserver(this);


        System.out.println(gui.getRects().getMyRect(1, 1) == null);
        for (int i = 0; i <= 99; ++i) {
            makeOneRegister(i);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        gui.start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void updateModelMap(int x, int y, String fettle) {
        System.out.println("updateModelMap");

        if (fettle.equals("non")) {
            gui.getRects().getMyRect(x, y).setFill(Color.GREEN);
        }

        if (fettle.equals("nearship")) {
            gui.getRects().getMyRect(x, y).setFill(Color.YELLOW);
        }

        if (fettle.equals("ship")) {
            gui.getRects().getMyRect(x, y).setFill(Color.BLUE);
        }

    }


    @Override
    public void updateModelIncomingMessage(String message) {
        taskClientServerConnector.updateMessage(message);
    }

    @Override
    public void updateGuiSendingMessage(String sendingMessage) {

        String message = String.format("%s :%s", clientServerConnector.getWhoClientOrServer(), sendingMessage);
        gui.setTextInCommonChat(message);
        Service service = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                // TODO Auto-generated method stub
                return new TaskSendingMessage(clientServerConnector, message);
            }

        };
        service.start();

    }

    @Override
    public void updateGuiCoordinate(int x, int y, String type) {
        System.out.println("updateGuiCoordinate");
        if (type.equals("one")) {
            engine.getMap().getChoose().chooseOne();
        } else if (type.equals("two")) {
            engine.getMap().getChoose().chooseTwo();
        } else if (type.equals("three")) {
            engine.getMap().getChoose().chooseThree();
        } else if (type.equals("four")) {
            engine.getMap().getChoose().chooseFour();
        }
        engine.getMap().mainFunctionInMap(x, y);



    }

    private void makeOneRegister(int i) {
        int y = (int) (10 - (10 - i * 0.1));
        int x= i - y * 10;
        gui.getRects().
                getMyRect(x, y).
                registerObserver(this);
    }

}
