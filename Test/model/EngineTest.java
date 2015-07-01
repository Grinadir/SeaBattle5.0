package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTest {

    @Test
    public void testOneSingleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();

        // when
        map.makeSingleShipInMap(1, 1);

        // then
        assertEquals(
                "----------\n" +
                        "-S--------\n" +
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
    public void testTwoSingleShip() throws Exception {
        // given
        Engine engine = new Engine();
        Map map = engine.getMap();

        // when
        map.makeSingleShipInMap(1, 1);
        map.makeSingleShipInMap(3, 3);

        // then
        assertEquals(
                "----------\n" +
                        "-S--------\n" +
                        "----------\n" +
                        "---S------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n" +
                        "----------\n", mapToString(map));
    }

    private String mapToString(Map map) {
        StringBuffer butter = new StringBuffer();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Cell cell = map.getCellMY(x, y);
                InterfaceShip ship = cell.getShip();
                if (ship != null) {
                    String shipType = ship.getClass().getSimpleName();
                    char type = shipType.charAt(4);
                    butter.append(type);
                } else {
                    butter.append('-');
                }
            }
            butter.append("\n");
        }
        return butter.toString();
    }
}

