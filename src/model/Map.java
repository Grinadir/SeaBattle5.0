package model;


import java.util.ArrayList;

/**
 * Created by User on 04.05.2015.
 */
public class Map implements ObservableMap {


    private final int INDEX_END_SHIP = 4400;
    private final Engine engine;
    private final int OUT_OF_FIELD = 18;
    private Cell[] cellMY = new Cell[100];
    private Cell[] cellENEMY = new Cell[100];
    private ArrayList observers;
    private int coordOfAttackX;
    private int coordOfAttackY;
    private Choose choose;

    public Map(Engine engine) {
        this.engine = engine;
        makeEnemyAndMyCells();
        observers = new ArrayList();
        this.choose = new Choose();
    }

    public int getCoordOfAttackX() {
        return coordOfAttackX;
    }

    public int getCoordOfAttackY() {
        return coordOfAttackY;
    }

    public Choose getChoose() {
        return choose;
    }

    @Override
    public void registerObserver(ObserverOfMap o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverOfMap o) {

    }

    @Override
    public void notify(int x, int y, String fettle) {
        System.out.println("notifyMap");
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfMap observer = (ObserverOfMap) observers.get(i);
            observer.updateModelMap(x, y, fettle);
        }
    }


    private void makeOneIterationCellMY(int i) {
        int numLine = (int) (10 - (10 - i * 0.1));
        cellMY[i] = new Cell(i - numLine * 10, numLine, "non");
    }

    private void makeOneIterationCellENEMY(int i) {
        int numLine = (int) (10 - (10 - i * 0.1));
        cellENEMY[i] = new Cell(i - numLine * 10, numLine, "non");
    }

    public void makeEnemyAndMyCells() {
        for (int i = 0; i <= 99; ++i) {
            makeOneIterationCellMY(i);
            makeOneIterationCellENEMY(i);
        }
    }

    public void mainFunctionInMap(int x, int y) {
        System.out.println("mainfunction");
        int i = x + y * 10;
        if (engine.getOneAmount() == 0
                && (!cellMY[i].getFettle().equals("nearship") && !cellMY[i].getFettle().equals("non"))) {
            if (cellMY[i].getFettle().equals("ship")) {
                cellMY[i].getFettle().equals("non");
                engine.increaseAmountByOne("one");
                engine.getLogicMarked().marketGreen(x, y);
                notify(x, y, "non");
            }
        } else if (isSelectedSingleShip(x, y)) {
            if (engine.getOneAmount() <= 4 && engine.getOneAmount() >= 1) {
                makeSingleShipInMap(x, y);
            }
        } else if (isSelectedDoubleShip(x, y)) {
            makeDoubleShipForThreeInMap(x, y);
        } else if (isSelectedTripleShip(x, y)) {
            makeTripleShipForBothInMap(x, y);
        } else if (isSelectedQuadrupleShip(x, y)) {
            makeQuadrupleShipInMap(x, y);
        }
    }

    private boolean isSelectedSingleShip(int x, int y) {
        return (choose.isOne() && !cellMY[x + 10 * y].getFettle().equals("nearship"))
                && engine.getOneAmount() != 0;
    }

    private boolean isSelectedDoubleShip(int x, int y) {
        return (choose.isTwo() && !cellMY[x + 10 * y].getFettle().equals("nearship"))
                && engine.getTwoAmount() != 0;
    }

    private boolean isSelectedTripleShip(int x, int y) {
        return (choose.isThree() && !cellMY[x + 10 * y].getFettle().equals("nearship"))
                && engine.getThreeAmount() != 0;
    }

    private boolean isSelectedQuadrupleShip(int x, int y) {
        return (choose.isFour() && !cellMY[x + 10 * y].getFettle().equals("nearship"))
                && engine.getFourAmount() != 0;
    }


    //Extract-function for foundation ship
    private void makeSingleShipInMap(int x, int y) {
        engine.getShipSingle()[engine.getOneAmount()] = new ShipSingle(engine);
        cellMY[y * 10 + x].setShip(engine.getShipSingle()[engine.getOneAmount()]);

        if (cellMY[x + 10 * y].getFettle() == "non" && engine.getShipSingle()[engine.getOneAmount()].make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            engine.getLogicMarked().marketYellow(x, y);
            notify(x, y, "ship");
        } else {
            engine.getShipSingle()[engine.getOneAmount()].clean();
            engine.getLogicMarked().marketGreen(x, y);
            cellMY[x + 10 * y].setFettle("non");
            notify(x, y, "non");
            cellMY[x + 10 * y].setShip(null);
        }
    }

    private void makeDoubleShipForThreeInMap(int x, int y) {
        if (engine.getCountOfRectangleForDouble() == 0) {
            engine.getShipDouble()[engine.getTwoAmount()] = new ShipDouble(engine);
            cellMY[x + 10 * y].setShip(engine.getShipDouble()[engine.getTwoAmount()]);
            if (cellMY[x + 10 * y].getShip().make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCountOfRectangleForDouble() == 1 && engine.getTwoAmount() != -1) {
            cellMY[x + 10 * y].setShip(engine.getShipDouble()[engine.getTwoAmount()]);
            if (cellMY[x + 10 * y].getShip().make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
                engine.getLogicMarked().marketYellow(x, y);
                if (engine.getSaveX() == x) {
                    engine.getLogicMarked().marketYellow(x, y - 1);
                    engine.getLogicMarked().marketYellow(x, y + 1);
                } else if (engine.getSaveY() == y) {
                    engine.getLogicMarked().marketYellow(x - 1, y);
                    engine.getLogicMarked().marketYellow(x + 1, y);
                }
            }
        }
    }

    private void makeTripleShipForBothInMap(int x, int y) {

        if (engine.getCountOfRectangleForTriple() == 0 && engine.getThreeAmount() != -1) {
            engine.getShipTriple()[engine.getThreeAmount()] = new ShipTriple(engine);
            cellMY[x + 10 * y].setShip(engine.getShipTriple()[engine.getThreeAmount()]);
            if (cellMY[x + 10 * y].getShip().make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCountOfRectangleForTriple() == 1 && engine.getThreeAmount() != -1) {
            cellMY[x + 10 * y].setShip(engine.getShipTriple()[engine.getThreeAmount()]);
            if (cellMY[x + 10 * y].getShip().make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCountOfRectangleForTriple() == 2 && engine.getThreeAmount() != -1) {
            cellMY[x + 10 * y].setShip(engine.getShipTriple()[engine.getThreeAmount()]);
            if (cellMY[x + 10 * y].getShip().make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
                engine.getLogicMarked().marketYellow(engine.getSaveX(), engine.getSaveY());
                engine.getLogicMarked().marketYellow(engine.getSaveX1(), engine.getSaveY1());
                engine.getLogicMarked().marketYellow(x, y);
            }
        }
    }

    private void makeQuadrupleShipInMap(int x, int y) {
        cellMY[x + 10 * y].setShip(engine.getShipQuadruple());
        if (engine.getCountOfRectangleForQuadruple() == 0 && cellMY[x + 10 * y].getShip().make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCountOfRectangleForQuadruple() == 1 && cellMY[x + 10 * y].getShip().make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCountOfRectangleForQuadruple() == 2 && cellMY[x + 10 * y].getShip().make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCountOfRectangleForQuadruple() == 3 && cellMY[x + 10 * y].getShip().make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
            engine.getLogicMarked().marketYellow(engine.getSaveX(), engine.getSaveY());
            engine.getLogicMarked().marketYellow(engine.getSaveX1(), engine.getSaveY1());
            engine.getLogicMarked().marketYellow(engine.getSaveX2(), engine.getSaveY2());
            engine.getLogicMarked().marketYellow(x, y);
        }
    }

    public void checkAndMarkMyField(int x, int y) {
        if (cellMY[y * 10 + x].getFettle().equals("ship")) {
            cellMY[y * 10 + x].getShip().impairment();
            if (cellMY[x + 10 * y].getShip().isValidShip()) {
                cellMY[y * 10 + x].setFettle("dam");
                notify(x, y, "damMyField");
            } else if (!(cellMY[x + 10 * y].getShip().isValidShip())) {
                cellMY[y * 10 + x].setFettle("kill");
                notify(x, y, "killMyField");
                int lX = cellMY[x + 10 * y].getShip().getX1();
                int lY = cellMY[x + 10 * y].getShip().getY1();
                if (lX + lY <= OUT_OF_FIELD) {
                    cellMY[y * 10 + x].setFettle("kill");
                    notify(x, y, "killMyField");
                }
                lX = cellMY[x + 10 * y].getShip().getX2();
                lY = cellMY[x + 10 * y].getShip().getY2();
                if (lX + lY <= OUT_OF_FIELD) {
                    cellMY[y * 10 + x].setFettle("kill");
                    notify(x, y, "killMyField");
                }
                lX = cellMY[x + 10 * y].getShip().getX3();
                lY = cellMY[x + 10 * y].getShip().getY3();
                if (lX + lY <= OUT_OF_FIELD) {
                    cellMY[y * 10 + x].setFettle("kill");
                    notify(x, y, "killMyField");
                }
                lX = cellMY[x + 10 * y].getShip().getX4();
                lY = cellMY[x + 10 * y].getShip().getY4();
                if (lX + lY <= OUT_OF_FIELD) {
                    cellMY[y * 10 + x].setFettle("kill");
                    notify(x, y, "killMyField");
                }
            }
        }
    }

    public void checkAndMarkEnemyField(int x, int y, String str, int ind1, int ind2, int ind3, int ind4) {
        if (str.equals("DAM")) {
            cellMY[y * 10 + x].setFettle("dam");
        } else if (str.equals("DESTROY")) {
            cellMY[y * 10 + x].setFettle("kill");
            if (ind1 != INDEX_END_SHIP) {
                cellMY[y * 10 + x].setFettle("kill");
            }
            if (ind2 != INDEX_END_SHIP) {
                cellMY[y * 10 + x].setFettle("kill");
            }
            if (ind3 != INDEX_END_SHIP) {
                cellMY[y * 10 + x].setFettle("kill");
            }
            if (ind4 != INDEX_END_SHIP) {
                cellMY[y * 10 + x].setFettle("kill");
            }
        } else if (str.equals("MISS")) {
            cellMY[y * 10 + x].setFettle("miss");
        }
    }

    public void selectTargetOfAttack(int x, int y) {
        if ((coordOfAttackX != x) || (coordOfAttackY != y)) {
            notify(coordOfAttackX, coordOfAttackY, "undoAttack");
            coordOfAttackX = x;
            coordOfAttackY = y;
            notify(coordOfAttackX, coordOfAttackY, "Attack");
        } else if ((coordOfAttackX == x) && (coordOfAttackY == y)) {
            if (coordOfAttackY == 440 && coordOfAttackY == 440) {
                coordOfAttackX = x;
                coordOfAttackY = y;
                notify(coordOfAttackX, coordOfAttackY, "Attack");
            } else {
                notify(coordOfAttackX, coordOfAttackY, "undoAttack");
                coordOfAttackX = 440;
                coordOfAttackY = 440;
            }
        }


    }

    public int getTargetIndexOfAttack() {
        return coordOfAttackX + 10 * coordOfAttackY;
    }

    public Cell getCellMY(int x, int y) {
        return cellMY[y * 10 + x];
    }

    public Cell getCellENEMY(int i) {
        return cellENEMY[i];
    }


}
