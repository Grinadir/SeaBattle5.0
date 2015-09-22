package view;

import javafx.scene.paint.Color;


public class Rects {

    private MyRectangle[] rectMY = new MyRectangle[100];
    private EnemyRectangle[] rectENEMY = new EnemyRectangle[100];
    private Gui gui;


    public Rects(Gui gui) {
        this.gui = gui;
        makeEnemyAndMyField();


    }

    private void makeOneIterationRectMY(int i) {
        rectMY[i] = new MyRectangle(15, 15, gui);
        rectMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectMY[i].setXinMyRect(i - numLine * 10);
        rectMY[i].setYinMyRect(numLine);
        gui.addMySeaField(rectMY[i], (i - numLine * 10), numLine);
    }

    private void makeOneIterationRectENEMY(int i) {
        rectENEMY[i] = new EnemyRectangle(15, 15);
        rectENEMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectENEMY[i].setXEnemyRect(i - numLine * 10);
        rectENEMY[i].setYEnemyRect(numLine);
        gui.addEnemySeaField(rectENEMY[i], (i - numLine * 10), numLine);
    }

    public void makeEnemyAndMyField() {
        for (int i = 0; i <= 99; ++i) {
            makeOneIterationRectMY(i);
            makeOneIterationRectENEMY(i);
        }
    }

    public MyRectangle getMyRect(int x, int y) {
        return rectMY[y * 10 + x];
    }

    public EnemyRectangle getRectENEMY(int i) {
        return rectENEMY[i];
    }


}