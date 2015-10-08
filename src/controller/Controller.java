package controller;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Engine;
import model.ObserverOfModelIncomingMessage;
import net.ClientServerConnector;
import net.WorkWithIncomingMessage;
import view.ObserverOfGuiSendingMessage;
import view.ObserverOfGuiSendingTargetCoord;

import java.util.Date;


/**
 * Created by User on 17.06.2015.
 */
public class Controller extends Application implements model.ObserverOfMap, ObserverOfModelIncomingMessage, view.ObserverOfGuiSendingMessage, view.ObserverOfGuiMyRectangle, view.ObserverOfGuiEnemyRectangle, ObserverOfGuiSendingTargetCoord {
    private view.Gui gui;
    private ClientServerConnector clientServerConnector;
    private TaskClientServerConnector taskClientServerConnector;
    private Engine engine;
    private Date currentDate;


    public Controller() {
        clientServerConnector = new ClientServerConnector();
        engine = new Engine();
        this.gui = new view.Gui() {
            @Override
            public void workWithIncommingMessage(String message) {
                new WorkWithIncomingMessage(engine, clientServerConnector).main(message);
            }
        }
        ;


        taskClientServerConnector = new TaskClientServerConnector(clientServerConnector);

        gui.setTaskConnection(taskClientServerConnector);

        System.out.println(taskClientServerConnector.getValue());
        clientServerConnector.registerObserver(this);
        gui.registerObserver((ObserverOfGuiSendingMessage) this);
        gui.registerObserver((ObserverOfGuiSendingTargetCoord) this);


        engine.getMap().registerObserver(this);
        engine.getLogicMarked().registerObserver(this);
        gui.setTaskSendCoordinateOfAttack(new TaskSendingTargetCoordinate(engine, clientServerConnector));
        for (int i = 0; i <= 99; ++i) {
            makeOneMyRegister(i);
        }
        for (int i = 0; i <= 99; ++i) {
            makeOneEnemyRegister(i);
        }


    }

    public static void main(String[] args) {
        launch(args);

    }


    private void makeOneEnemyRegister(int i) {
        gui.getRects().
                getRectENEMY(i).
                registerObserver(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui.start(primaryStage);

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
        if (fettle.equals("undoAttack")) {
            System.out.println("undoAttack");
            gui.getRects().getRectENEMY(y * 10 + x).setFill(Color.GREEN);
        }
        if (fettle.equals("Attack")) {
            System.out.println("Attack");
            gui.getRects().getRectENEMY(y * 10 + x).setFill(Color.RED);
        }


        if (fettle.equals("damMyField")) {
            gui.getRects().getMyRect(x, y).setFill(Color.ORANGE);
        }

        if (fettle.equals("killMyField")) {
            gui.getRects().getMyRect(x, y).setFill(Color.BLACK);
        }

        if (fettle.equals("damageEnemy")) {
            gui.getRects().getRectENEMY(x + y * 10).setFill(Color.ORANGE);
        }

        if (fettle.equals("destroyEnemy")) {
            gui.getRects().getRectENEMY(x + y * 10).setFill(Color.BLACK);;
        }

        if (fettle.equals("missEnemy")) {
            gui.getRects().getRectENEMY(x + y * 10).setFill(Color.YELLOW);;
        }

    }


    @Override
    public void updateModelIncomingMessage(String message) {
        taskClientServerConnector.updateMessage(message);
    }

    @Override
    public void updateGuiSendingMessage(String sendingMessage) {
        currentDate = new Date();

        String message = String.format("%s (%s):%s", clientServerConnector.getWhoClientOrServer(), currentDate, sendingMessage);
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
    public void updateFromGuiCoordinate(int x, int y, String type) {
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

    private void makeOneMyRegister(int i) {
        int y = (int) (10 - (10 - i * 0.1));
        int x = i - y * 10;
        gui.getRects().
                getMyRect(x, y).
                registerObserver(this);
    }

    @Override
    public void updateGuiAttackCoordinate(int x, int y) {
        System.out.println("updateGuiAttackCoordinate");
        engine.getMap().selectTargetOfAttack(x, y);
    }

    @Override
    public void updateGuiSendingTargetCoord() {

        Service service = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new TaskSendingTargetCoordinate(engine, clientServerConnector);
            }

        };
        service.start();

    }


}
