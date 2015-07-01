package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;


public class EnemyRectangle extends Rectangle {
    private int x;
    private int y;

    public EnemyRectangle(double width, double height) {


        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

            }
        });
    }

    public void setXEnemyRect(int x) {
        this.x = x;
    }

    public void setYEnemyRect(int y) {
        this.y = y;
    }
}

