package seaBattle;

import org.junit.Test;
import seaBattle.Model.Ship;

import static org.junit.Assert.assertEquals;

/**
 * Тестируем вложенный класс Section класса Ship.
 */
public class SectionTest {

    /**
     * Создание координатов отсеков корабля.
     */
    private int[] positionsX = {1};
    private int[] positionsY = {2};

    /**
     * Создаём корабль.
     */
    private Ship ship = new Ship(1, positionsX, positionsY);


    /**
     * Тестируем метод getPositionX().
     * @throws Exception
     */
    @Test
    public void getPositionX() throws Exception {
        assertEquals(1,  ship.getSections()[0].getPositionX());
    }


    /**
     * Тестируем метод getPositionY().
     * @throws Exception
     */
    @Test
    public void getPositionY() throws Exception {
        assertEquals(2,  ship.getSections()[0].getPositionY());
    }


    /**
     * Тестируем метод damage();
     * @throws Exception
     */
    @Test
    public void damage() throws Exception {
        assertEquals(false, ship.getSections()[0].isDamaged());
    }


    /**
     * Тестируем метод damage();
     * @throws Exception
     */
    @Test
    public void setDamaged() throws Exception {
        ship.getSections()[0].damage();
        assertEquals(true, ship.getSections()[0].isDamaged());
    }
}