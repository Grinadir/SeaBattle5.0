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

    @Test
    public void test(){
        Engine engine = new Engine();
        Map map = engine.getMap();

        map.getChoose().chooseOne();
        map.mainFunctionInMap(0,0);
        map.mainFunctionInMap(2,0);
        map.mainFunctionInMap(4,0);
        map.mainFunctionInMap(6,0);

        map.getChoose().chooseTwo();
        map.mainFunctionInMap(0,2);
        map.mainFunctionInMap(1,2);
        map.mainFunctionInMap(3,2);
        map.mainFunctionInMap(4,2);
        map.mainFunctionInMap(6,2);
        map.mainFunctionInMap(7,2);

        map.getChoose().chooseThree();
        map.mainFunctionInMap(0,4);
        map.mainFunctionInMap(1,4);
        map.mainFunctionInMap(2,4);
        map.mainFunctionInMap(4,4);
        map.mainFunctionInMap(5,4);
        map.mainFunctionInMap(6,4);

        map.getChoose().chooseFour();
        map.mainFunctionInMap(1,7);
        map.mainFunctionInMap(2,7);
        map.mainFunctionInMap(3,7);
        map.mainFunctionInMap(4,7);



        assertEquals(
                "S-S-S-S---\n" +
                        "----------\n" +
                        "DD-DD-DD--\n" +
                        "----------\n" +
                        "TTT-TTT---\n" +
                        "----------\n" +
                        "----------\n" +
                        "-QQQQ-----\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                map.checkAndMarkMyField(x, y);

                }
            }

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                map.checkAndMarkMyField(x, y);

            }
        }

        assertEquals(
                "X0X0X0X000\n" +
                        "0000000000\n" +
                        "#X0#X0#X00\n" +
                        "0000000000\n" +
                        "##X0##X000\n" +
                        "0000000000\n" +
                        "0000000000\n" +
                        "0###X00000\n" +
                        "0000000000\n" +
                        "0000000000\n", mapToStringCheckFettle(map));



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

    private String mapToStringCheckFettle(Map map) {
        StringBuffer buffer = new StringBuffer();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cell cell = map.getCellMY(x, y);
                InterfaceShip ship = cell.getShip();
                if (cell.getFettle().equals("ship")) {
                    String shipType = ship.getClass().getSimpleName();
                    char type = shipType.charAt(4);
                    buffer.append(type);
                }

                else if (cell.getFettle().equals("dam")) {
                    buffer.append('#');
                }
                else if (cell.getFettle().equals("kill")){
                    buffer.append('X');
                }
                else {
                    buffer.append('0');
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}

