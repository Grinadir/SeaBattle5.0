package view;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Gui extends Application implements ObservableGuiSendingMessage, ObservableGuiSendingTargetCoord {


    private TextArea commonChat = new TextArea();
    private TextArea sendingMessage = new TextArea();
    private Button bStart = new Button("Connect");
    private Button bsendMessage = new Button("Send");
    private Button fireButton = new Button("Fire");
    private Button readyButton = new Button("Ready?");
    private ToggleGroup ships = new ToggleGroup();
    private RadioButton four = new RadioButton("Four 1 pcs.");
    private RadioButton three = new RadioButton("Three 2 pcs.");
    private RadioButton two = new RadioButton("Two 3 pcs.");
    private RadioButton one = new RadioButton("One 4 pcs.");
    private Label whomStep = new Label();

    private Task taskConnection;
    private Task taskSendMessage;
    private Task taskSendCoordinateOfAttack;
    private ArrayList observersOfSendingMessage = new ArrayList();
    private ArrayList observersOfSendingTarget = new ArrayList();
    private GridPane mySeaField = new GridPane();
    private GridPane myPane = new GridPane();
    private GridPane enemySeaField = new GridPane();
    private GridPane shipType = new GridPane();
    private Rects rects = new Rects(this);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public Rects getRects() {
        return rects;
    }

    public void setTaskConnection(Task taskConnection) {
        this.taskConnection = taskConnection;
    }



    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        commonChat.setEditable(false);
        commonChat.setPrefSize(200, 500);
        commonChat.setTooltip(new Tooltip("Чат Окно"));
        commonChat.setWrapText(true);
        commonChat.clear();

        four.setToggleGroup(ships);
        three.setToggleGroup(ships);
        two.setToggleGroup(ships);
        one.setToggleGroup(ships);
        one.setSelected(true);

        myPane.setAlignment(Pos.CENTER_LEFT);
        myPane.setHgap(10);
        myPane.setVgap(10);
        myPane.setPadding(new Insets(25, 25, 25, 25));

        mySeaField.setAlignment(Pos.CENTER_LEFT);
        mySeaField.setHgap(1);
        mySeaField.setVgap(1);
        mySeaField.setPadding(new Insets(5, 5, 5, 5));

        enemySeaField.setAlignment(Pos.CENTER_RIGHT);
        enemySeaField.setVgap(1);
        enemySeaField.setHgap(1);
        enemySeaField.setPadding(new Insets(5, 5, 5, 5));

        shipType.setAlignment(Pos.CENTER_LEFT);
        shipType.setPadding(new Insets(0, 0, 0, 0));
        shipType.setHgap(20);
        shipType.add(four, 0, 0, 1, 1);
        shipType.add(three, 1, 0, 1, 1);
        shipType.add(two, 2, 0, 1, 1);
        shipType.add(one, 3, 0, 1, 1);

        myPane.add(mySeaField, 0, 0, 1, 1);
        myPane.add(enemySeaField, 1, 0, 1, 1);
        myPane.add(shipType, 0, 2, 2, 1);
        myPane.add(readyButton, 0, 3, 1, 1);
        myPane.add(fireButton, 1, 3, 1, 1);
        myPane.add(whomStep, 2, 3, 1, 1);
        myPane.add(commonChat, 0, 11, 2, 1);
        myPane.add(sendingMessage, 0, 12, 2, 1);
        myPane.add(bsendMessage, 0, 13, 1, 1);
        myPane.add(bStart, 0, 14, 5, 1);


        bStart.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {


                taskConnection.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {

                                //String tempString = taskConnection.getMessage();
                                Gui.this.setTextInCommonChat(taskConnection.getMessage());
                                //new GuiWorkWithIncomingMessage(engine, connector).main(tempString);
                                //statusLabelOfStep();

                            }
                        });
                // Создание класса Task, существующий для работы с JavaFX
                //commonChat.setText("Begin connection");
                Service service = new Service<Void>() {


                    @Override
                    protected Task<Void> createTask() {
                        return taskConnection;
                    }

                };
                service.start();
            }

        });

        fireButton.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                notifySendingTarget();
            }
        });

        bsendMessage.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {


                notifySendingMessage(sendingMessage.getText());

            }
        });

        readyButton.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

            }
        });

        one.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                one.setSelected(true);

            }

        });
        two.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                two.setSelected(true);

            }

        });
        three.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                three.setSelected(true);

            }

        });
        four.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                four.setSelected(true);

            }

        });
        //rects.makeEnemyAndMyField();


        Scene scene = new Scene(myPane, 500, 600);

        scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void statusLabelOfStep() {

    }

    public void addMySeaField(MyRectangle rectangle, int i, int numline) {
        mySeaField.add(rectangle, i, numline);
    }

    public void addEnemySeaField(EnemyRectangle rectangle, int i, int numLine) {
        enemySeaField.add(rectangle, i, numLine);
    }

    public void setTextInCommonChat(String message) {

        commonChat.setText(commonChat.getText() + "\n"
                + message);
        commonChat.end();
    }

    public TextArea getSendingMessage() {
        return sendingMessage;
    }

    public Settings getSettings() {
        return new Settings() {

            @Override
            public boolean isOne() {
                return one.isSelected();
            }

            @Override
            public boolean isTwo() {
                return two.isSelected();
            }

            @Override
            public boolean isThree() {
                return three.isSelected();
            }

            @Override
            public boolean isFour() {
                return four.isSelected();
            }
        };
    }


    public Task getTaskSendCoordinateOfAttack() {
        return taskSendCoordinateOfAttack;
    }

    public void setTaskSendCoordinateOfAttack(Task taskSendCoordinateOfAttack) {
        this.taskSendCoordinateOfAttack = taskSendCoordinateOfAttack;
    }

    public Task getTaskSendMessage() {
        return taskSendMessage;
    }

    public void setTaskSendMessage(Task taskSendMessage) {
        this.taskSendMessage = taskSendMessage;
    }

    @Override
    public void registerObserver(ObserverOfGuiSendingMessage o) {
        observersOfSendingMessage.add(o);

    }

    @Override
    public void removeObserver(ObserverOfGuiSendingMessage o) {

    }

    @Override
    public void notifySendingMessage(String message) {
        for (int i = 0; i < observersOfSendingMessage.size(); i++) {
            ObserverOfGuiSendingMessage observer = (ObserverOfGuiSendingMessage) observersOfSendingMessage.get(i);
            observer.updateGuiSendingMessage(message);
        }

    }


    @Override
    public void registerObserver(ObserverOfGuiSendingTargetCoord o) {
        observersOfSendingTarget.add(o);

    }

    @Override
    public void removeObserver(ObserverOfGuiSendingTargetCoord o) {

    }

    @Override
    public void notifySendingTarget() {
        for (int i = 0; i < observersOfSendingTarget.size(); i++) {
            ObserverOfGuiSendingTargetCoord observer = (ObserverOfGuiSendingTargetCoord) observersOfSendingTarget.get(i);
            observer.updateGuiSendingTargetCoord();
        }

    }
}

