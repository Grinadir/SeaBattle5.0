package view;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


/*
 * Need to create single rectangle
 * with its own parameters
 */

public class MyRectangle extends Rectangle implements ObservableGuiMyRectangle {

    private int x;
    private int y;
    private int veto = 0;
    private Gui gui;
    private ArrayList observers;

    public MyRectangle(double width, double height, Gui gui) {
        this.gui = gui;
        setWidth(width);
        setHeight(height);
        observers = new ArrayList();
        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (gui.getSettings().isOne()) {
                    notifyCoordinate(x, y, "one");
                } else if (gui.getSettings().isTwo()) {
                    notifyCoordinate(x, y, "two");
                } else if (gui.getSettings().isThree()) {
                    notifyCoordinate(x, y, "three");
                } else if (gui.getSettings().isFour()){
                    notifyCoordinate(x, y, "four");
                }
            }
        });
    }


    public void setXinMyRect(int x) {
        this.x = x;
    }

    public void setYinMyRect(int y) {
        this.y = y;
    }

    public int getVeto() {
        return this.veto;
    }

    public void setVeto(int veto) {
        this.veto = veto;
    }


    @Override
    public void registerObserver(ObserverOfGuiMyRectangle o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverOfGuiMyRectangle o) {

    }

    @Override
    public void notifyCoordinate(int x, int y, String type) {
        for (int i = 0; i < observers.size(); i++) {
            System.out.println("notifyCoordinate");
            ObserverOfGuiMyRectangle observer = (ObserverOfGuiMyRectangle) observers.get(i);
            observer.updateGuiCoordinate(x, y, type);
        }

    }
}