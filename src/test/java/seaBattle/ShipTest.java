package seaBattle;

import org.junit.Test;
import seaBattle.Model.Ship;

import static org.junit.Assert.assertEquals;

/**
 * Тестируем класс Ship.
 */
public class ShipTest {

    /**
     * Создание координатов отсеков корабля.
     */
    private int[] positionsX = {3};
    private int[] positionsY = {2};

    /**
     * Создание корабля.
     */
    private Ship ship = new Ship(1, positionsX, positionsY);


    /**
     * Тестируем метод isDestroyed();
     * @throws Exception
     */
    @Test
    public void isDestroyed() throws Exception {
        assertEquals(false, ship.isDestroyed());
    }


    /**
     * Тестируем метод getSections();
     * @throws Exception
     */
    @Test
    public void getSections() throws Exception {
        assertEquals(3, ship.getSections()[0].getPositionX());
        assertEquals(2, ship.getSections()[0].getPositionY());
    }


    /**
     * Тестируем метод getSize();
     * @throws Exception
     */
    @Test
    public void getSize() throws Exception {
        assertEquals(1, ship.getSize());
    }
}