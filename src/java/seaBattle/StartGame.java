package seaBattle;

import seaBattle.Model.Field;
import seaBattle.Model.Ship;
import seaBattle.View.Console;

public class StartGame {
    public static void main(String[] args) {
        int[] positionsX = {3, 3, 3};
        int[] positionsY = {1, 2, 3};
        Ship ship = new Ship(3, positionsX, positionsY);
        System.out.println(ship.isDestroyed());
        ship.getSections()[0].damage();
        ship.getSections()[1].damage();
        ship.getSections()[2].damage();
        System.out.println(ship.isDestroyed());

        Field field = new Field();
        Console console = new Console();
        console.show();
    }
}
