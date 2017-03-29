package seaBattle.model;

import org.junit.Test;

/**
 * Тестируем класс Field.
 */
public class FieldTest {
    Field field = new Field();

    @Test
    public void createShips() throws Exception {
        field.init();
        field.createShips();
    }

}