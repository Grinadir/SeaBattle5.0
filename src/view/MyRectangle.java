package view;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.scene.shape.Rectangle;


/*
 * Need to create single rectangle
 * with its own parameters
 */

public class MyRectangle extends Rectangle {

    private int x;
    private int y;
    private int veto = 0;

    public MyRectangle(double width, double height, int e) {
        setWidth(width);
        setHeight(height);

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
}