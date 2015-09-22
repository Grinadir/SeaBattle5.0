package model;

/**
 * Created by User on 22.09.2015.
 */

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChooseTest {
    @Test
    public void testChooseOne() {
        Choose choose = new Choose();
        choose.chooseOne();
        assertTrue(choose.isOne());
        assertFalse(choose.isTwo());
        assertFalse(choose.isThree());
        assertFalse(choose.isFour());

    }

    @Test
    public void testChooseTwo() {
        Choose choose = new Choose();
        choose.chooseTwo();
        assertFalse(choose.isOne());
        assertTrue(choose.isTwo());
        assertFalse(choose.isThree());
        assertFalse(choose.isFour());

    }

    @Test
    public void testChooseThree() {
        Choose choose = new Choose();
        choose.chooseThree();
        assertFalse(choose.isOne());
        assertFalse(choose.isTwo());
        assertTrue(choose.isThree());
        assertFalse(choose.isFour());
    }

    @Test
    public void testChooseFour() {
        Choose choose = new Choose();
        choose.chooseFour();
        assertFalse(choose.isOne());
        assertFalse(choose.isTwo());
        assertFalse(choose.isThree());
        assertTrue(choose.isFour());
    }

}
