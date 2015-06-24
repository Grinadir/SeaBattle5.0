package model;

/**
 * Created by User on 16.06.2015.
 */
public class Choose implements Settings {

    boolean one;
    boolean two;
    boolean three;
    boolean four;

    public Choose() {
        one = true;
        two = false;
        three = false;
        four = false;
    }

    void chooseOne() {
        one = true;
        two = false;
        three = false;
        four = false;
    }

    void chooseTwo() {
        one = false;
        two = true;
        three = false;
        four = false;
    }

    void chooseThree() {
        one = false;
        two = false;
        three = true;
        four = false;
    }

    void chooseFour() {
        one = false;
        two = false;
        three = false;
        four = true;
    }


    @Override
    public boolean isOne() {
        return false;
    }

    @Override
    public boolean isTwo() {
        return false;
    }

    @Override
    public boolean isThree() {
        return false;
    }

    @Override
    public boolean isFour() {
        return false;
    }
}
