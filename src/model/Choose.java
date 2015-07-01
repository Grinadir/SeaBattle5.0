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

    public void chooseOne() {
        one = true;
        two = false;
        three = false;
        four = false;
    }

    public void chooseTwo() {
        one = false;
        two = true;
        three = false;
        four = false;
    }

    public void chooseThree() {
        one = false;
        two = false;
        three = true;
        four = false;
    }

    public void chooseFour() {
        one = false;
        two = false;
        three = false;
        four = true;
    }


    @Override
    public boolean isOne() {
        return one;
    }

    @Override
    public boolean isTwo() {
        return two;
    }

    @Override
    public boolean isThree() {
        return three;
    }

    @Override
    public boolean isFour() {
        return four;
    }
}
