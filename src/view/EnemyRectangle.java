package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class EnemyRectangle extends Rectangle implements ObservableGuiEnemyRectangle {
    private int x;
    private int y;
    private ArrayList observers;

    public EnemyRectangle(double width, double height) {
        observers=new ArrayList();


        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                notifyCoordinate(x, y);



            }
        });
    }

    public void setXEnemyRect(int x) {
        this.x = x;
    }

    public void setYEnemyRect(int y) {
        this.y = y;
    }

    @Override
    public void registerObserver(ObserverOfGuiEnemyRectangle o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverOfGuiEnemyRectangle o) {

    }

    @Override
    public void notifyCoordinate(int x, int y) {
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfGuiEnemyRectangle observer = (ObserverOfGuiEnemyRectangle) observers.get(i);
            System.out.println("x-"+x+ "; y-"+y);
            observer.updateGuiAttackCoordinate(x, y);
        }

    }
}

