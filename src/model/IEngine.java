package model;

/**
 * Created by User on 09.10.2015.
 */
public interface IEngine {
    ObservableMap getMap();
    int getOneAmount();
    int getTwoAmount();
    int getThreeAmount();
    int getFourAmount();
    int getSaveX();
    int getSaveX1();
    int getSaveX2();
    int getSaveY();
    int getSaveY1();
    int getSaveY2();
    void setSaveX(int x);
    void setSaveX1(int x);
    void setSaveX2(int x);
    void setSaveY(int y);
    void setSaveY1(int y);
    void setSaveY2(int y);
    int getCountOfRectangleForDouble();
    int getCountOfRectangleForTriple();
    int getCountOfRectangleForQuadruple();

    void setCountOfRectangleForDouble(int countOfRectangleForDouble);
    void setCountOfRectangleForTriple(int countOfRectangleForTriple);
    void setCountOfRectangleForQuadruple(int countOfRectangleForQuadruple);
    void increaseAmountByOne(String type);
    void decreaseAmountByOne(String type);
    InterfaceShip[] getShipSingle();
    InterfaceShip[] getShipDouble();
    InterfaceShip[] getShipTriple();
    InterfaceShip getShipQuadruple();


}
