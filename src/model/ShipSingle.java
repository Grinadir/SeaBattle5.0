package model;


/**
 * Created by User on 29.04.2015.
 */
public class ShipSingle implements InterfaceShip {
    private final int NONEXISTENT_COORDINATE = 400;
    private IEngine engine;
    private int countOfAliveRect = 1;
    private int xNO1;
    private int yNO1;

    public ShipSingle(IEngine engine) {
        this.engine = engine;
    }

    @Override
    public boolean make(int x, int y) {
        xNO1 = x;
        yNO1 = y;
        engine.decreaseAmountByOne("one");
        return true;
    }

    @Override
    public void clean() {
        engine.increaseAmountByOne("one");
    }

    @Override
    public boolean isValidShip() {
        if (countOfAliveRect != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void impairment() {
        countOfAliveRect--;
    }

    @Override
    public int getX1() {
        return xNO1;
    }

    @Override
    public int getY1() {
        return yNO1;
    }

    @Override
    public int getX2() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY2() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getX3() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY3() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getX4() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY4() {
        return NONEXISTENT_COORDINATE;
    }
}
