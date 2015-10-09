package model;


import java.util.ArrayList;

/**
 * Created by User on 08.06.2015.
 */
public class LogicMarked {

    private ObservableMap map;


    public LogicMarked(ObservableMap map) {
        this.map = map;
    }

    public void setColorYellowRect(int x, int y) {
        if (!(map.getCellMY(x, y).getFettle().equals("ship") && !(map.getCellMY(x, y).getFettle().equals("nearship")))) {
            map.getCellMY(x, y).setFettle("nearship");
            map.notify(x, y, "nearship");
        }
    }

    public void setVeto(int x, int y) {

        if (map.getCellMY(x, y).getVeto() == 1) {
            map.getCellMY(x, y).setFettle("non");
            map.getCellMY(x, y).setVeto(map.getCellMY(x, y).getVeto() - 1);
            map.notify(x, y, "non");
        } else {

            map.getCellMY(x, y).setVeto(map.getCellMY(x, y).getVeto() - 1);
        }
    }

    public void marketYellow(int x, int y) {
        if (!(map.getCellMY(x, y).getFettle().equals("nearship"))) {
            for (int xCircle = x - 1; xCircle <= x + 1; xCircle++) {
                innerCircleForMarkYellow(y, xCircle);
            }
        }
    }

    private void innerCircleForMarkYellow(int y, int xCircle) {
        if (0 <= xCircle && xCircle <= 9) {
            for (int yC = y - 1; yC <= y + 1; yC++) {
                if (0 <= yC && yC <= 9) {
                    setColorYellowRect(xCircle, yC);
                    map.getCellMY(xCircle, yC).setVeto(map.getCellMY(xCircle, yC).getVeto() + 1);

                }
            }
        }
    }

    public void marketGreen(int x, int y) {
        for (int xCircle = x - 1; xCircle <= x + 1; xCircle++) {
            innerCircleForVeto(y, xCircle);

        }
    }

    private void innerCircleForVeto(int y, int xCircle) {
        if (0 <= xCircle && xCircle <= 9) {
            for (int yCircle = y - 1; yCircle <= y + 1; yCircle++) {
                if (0 <= yCircle && yCircle <= 9) {
                    setVeto(xCircle, yCircle);
                }
            }
        }

    }
}
