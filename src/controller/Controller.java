package controller;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ClientServerConnector;
import view.EventGuiOn;


/**
 * Created by User on 17.06.2015.
 */
public class Controller extends Application implements model.ObserverOfMap, model.ObserverOfMessage {
    view.Gui gui;

    public Controller(){
        this.gui=new view.Gui();
        model.ClientServerConnector clientServerConnector=new ClientServerConnector();
        TaskClientServerConnector taskClientServerConnector=new TaskClientServerConnector(clientServerConnector, gui);
        gui.setTaskConnection(taskClientServerConnector);
        gui.setTaskSendMessage(new model.SendingMessage(clientServerConnector));
        System.out.println(taskClientServerConnector.getValue());
        clientServerConnector.registerObserver(this);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        //clientServerConnector.registerObserver(taskClientServerConnector);


        gui.onAppearance(new EventGuiOn() {
            @Override
            public void eventGui(String string) {

            }
        });

        gui.start(primaryStage);

    }
    public static void main(String[]args){
        launch(args);

    }

    @Override
    public void update(int x, int y, String fettle) {

        if (fettle.equals("non")) {
            gui.getRects().getMyRect(x,y).setFill(Color.GREEN);
        }

        if (fettle.equals("nearship")) {
            gui.getRects().getMyRect(x,y).setFill(Color.YELLOW);
        }

        if (fettle.equals("ship")) {
            gui.getRects().getMyRect(x,y).setFill(Color.YELLOW);
        }

    }


    @Override
    public void update(String string) {
        gui.setTextInCommonChat(string);
        System.out.println("HHHHHHHHHH");
    }
}
