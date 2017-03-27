package seaBattle.model;

import org.junit.Test;
import seaBattle.view.Console;

/**
 * Тестируем класс Field.
 */
public class FieldTest {
    Field field = new Field();
    Console console = new Console();

    @Test
    public void createShips() throws Exception {
        console.fieldInit();
        field.createShips();
    }

}