package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EngineTest {

    @Test
    public void testAllSingleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();

        // when

        map.mainFunctionInMap(1,1);
        map.mainFunctionInMap(2,1);
        map.mainFunctionInMap(3,1);
        map.mainFunctionInMap(4,1);
        map.mainFunctionInMap(5,1);
        map.mainFunctionInMap(6,1);
        map.mainFunctionInMap(7,1);
        map.mainFunctionInMap(8,1);
        map.mainFunctionInMap(9,1);



        // then
        assertEquals(
                "----------\n" +
                        "-S-S-S-S--\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));
    }

    @Test
    public void testOneDoubleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseTwo();

        // when

        map.mainFunctionInMap(1,1);
        map.mainFunctionInMap(2,1);
        map.mainFunctionInMap(3,1);
        map.mainFunctionInMap(4,1);
        map.mainFunctionInMap(5,1);
        map.mainFunctionInMap(6,1);
        map.mainFunctionInMap(7,1);
        map.mainFunctionInMap(8,1);
        map.mainFunctionInMap(9,1);

        // then
        assertEquals(
                "----------\n" +
                        "-DD-DD-DD-\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));
    }

    @Test
         public void testTripleDoubleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseThree();

        // when


        map.mainFunctionInMap(1,1);
        map.mainFunctionInMap(2,1);
        map.mainFunctionInMap(3,1);
        map.mainFunctionInMap(4,1);
        map.mainFunctionInMap(5,1);
        map.mainFunctionInMap(6,1);
        map.mainFunctionInMap(7,1);
        map.mainFunctionInMap(8,1);
        map.mainFunctionInMap(9,1);

        // then
        assertEquals(
                "----------\n" +
                        "-TTT-TTT--\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));
    }

    @Test
    public void testQuadrupleDoubleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseFour();

        // when

        map.mainFunctionInMap(1,1);
        map.mainFunctionInMap(2,1);
        map.mainFunctionInMap(3,1);
        map.mainFunctionInMap(4,1);
        map.mainFunctionInMap(5,1);
        map.mainFunctionInMap(6,1);
        map.mainFunctionInMap(7,1);
        map.mainFunctionInMap(8,1);
        map.mainFunctionInMap(9,1);

        // then
        assertEquals(
                "----------\n" +
                        "-QQQQ-----\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));
    }

    @Test
    public void testChooseOne() {
        Engine engine=new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseOne();
        assertTrue(map.getChoose().isOne());
        assertFalse(map.getChoose().isTwo());
        assertFalse(map.getChoose().isThree());
        assertFalse(map.getChoose().isFour());

    }

    @Test
    public void testChooseTwo() {
        Engine engine=new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseTwo();
        assertFalse(map.getChoose().isOne());
        assertTrue(map.getChoose().isTwo());
        assertFalse(map.getChoose().isThree());
        assertFalse(map.getChoose().isFour());

    }

    @Test
    public void testChooseThree() {
        Engine engine = new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseThree();
        assertFalse(map.getChoose().isOne());
        assertFalse(map.getChoose().isTwo());
        assertTrue(map.getChoose().isThree());
        assertFalse(map.getChoose().isFour());
    }

    @Test
    public void testChooseFour() {
        Engine engine = new Engine();
        Map map = engine.getMap();
        map.getChoose().chooseFour();
        assertFalse(map.getChoose().isOne());
        assertFalse(map.getChoose().isTwo());
        assertFalse(map.getChoose().isThree());
        assertTrue(map.getChoose().isFour());
    }



    private String mapToString(Map map) {
        StringBuffer buffer = new StringBuffer();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell cell = map.getCellMY(x, y);
                InterfaceShip ship = cell.getShip();
                if (ship != null) {
                    String shipType = ship.getClass().getSimpleName();
                    char type = shipType.charAt(4);
                    buffer.append(type);
                } else {
                    buffer.append('-');
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}

