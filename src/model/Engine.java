package model;


/**
 * Created by User on 23.04.2015.
 */
public class Engine {

    private Status status;
    private Map map;
    private LogicMarked logicMarked;

    private InterfaceShip[] shipSingle = new ShipSingle[5];
    private InterfaceShip[] shipDouble = new ShipDouble[4];
    private InterfaceShip[] shipTriple = new ShipTriple[3];
    private InterfaceShip shipQuadruple = new ShipQuadruple(this);

    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;

    private int oneAmount = 4;
    private int twoAmount = 3;
    private int threeAmount = 2;
    private int fourAmount = 1;

    private int saveX;
    private int saveY;
    private int saveX1;
    private int saveY1;
    private int saveX2;
    private int saveY2;

    private int targetX = 440;
    private int targetY = 440;

    private int targetIndex;

    public Engine() {
        this.map = new Map(this);
        this.logicMarked = new LogicMarked(map);
        this.status = new Status();
    }

    public InterfaceShip[] getShipSingle() {
        return shipSingle;
    }

    public InterfaceShip[] getShipDouble() {
        return shipDouble;
    }

    public InterfaceShip[] getShipTriple() {
        return shipTriple;
    }

    public InterfaceShip getShipQuadruple() {
        return shipQuadruple;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getCount3() {
        return count3;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public int getOneAmount() {
        return oneAmount;
    }

    public int getTwoAmount() {
        return twoAmount;
    }

    public int getThreeAmount() {
        return threeAmount;
    }

    public int getFourAmount() {
        return fourAmount;
    }

    public int getSaveX() {
        return saveX;
    }

    public void setSaveX(int saveX) {
        this.saveX = saveX;
    }

    public int getSaveY() {
        return saveY;
    }

    public void setSaveY(int saveY) {
        this.saveY = saveY;
    }

    public int getSaveX1() {
        return saveX1;
    }

    public void setSaveX1(int saveX1) {
        this.saveX1 = saveX1;
    }

    public int getSaveY1() {
        return saveY1;
    }

    public void setSaveY1(int saveY1) {
        this.saveY1 = saveY1;
    }

    public int getSaveX2() {
        return saveX2;
    }

    public void setSaveX2(int saveX2) {
        this.saveX2 = saveX2;
    }

    public int getSaveY2() {
        return saveY2;
    }

    public void setSaveY2(int saveY2) {
        this.saveY2 = saveY2;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int x) {
        this.targetX = x;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int y) {
        this.targetY = y;
    }

    public void increaseAmountByOne(String type) {
        switch (type) {
            case "one":
                oneAmount++;
                break;
            case "two":
                twoAmount++;
                break;
            case "three":
                threeAmount++;
                break;
            case "four":
                fourAmount++;
                break;
        }
    }

    public void decreaseAmountByOne(String type) {
        switch (type) {
            case "one":
                oneAmount--;
                break;
            case "two":
                twoAmount--;
                break;
            case "three":
                threeAmount--;
                break;
            case "four":
                fourAmount--;
                break;
        }
    }

    public boolean isAllShipInstall() {
        boolean a = oneAmount == 0;
        boolean b = twoAmount == 0;
        boolean c = threeAmount == 0;
        boolean d = fourAmount == 0;
        return a && b && c && d;
    }

    //public Rects getRects() {
    //    return rects;
    //}

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Status getStatus() {
        return status;
    }

    //public Gui getGui() {
    //   return gui;
    //}

    public Map getMap() {
        return map;
    }

    public LogicMarked getLogicMarked() {
        return logicMarked;
    }
}
