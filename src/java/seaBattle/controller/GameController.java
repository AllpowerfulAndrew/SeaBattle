package seaBattle.controller;

import seaBattle.view.Console;

/**
 * Класс для взаимодействия классов GameSession и Console.
 */
public class GameController {
    Console console = new Console();

    /**
     * Инициализирует игровое поле.
     */
    public void fieldInit() {
        console.fieldInit();
    }


    /**
     * Отображает в консоли игровое поле.
     */
    public void showVisibleField() {
        console.showVisibleField();
    }


    /**
     * Отображает в консоли не игровое поле со всеми кораблями.
     */
    public void showInvisibleField() {
        console.showInvisibleField();
    }


    /**
     * Пересылает запрос консоли, чтобы узнать у игрока координату выстрела.
     * @return Возвращает координату X.
     */
    public int askShootCoordinateX() {
        String coordinateX = "X";
        return console.askShootCoordinate(coordinateX);
    }


    /**
     * Пересылает запрос консоли, чтобы узнать у игрока координату выстрела.
     * @return Возвращает координату Y.
     */
    public int askShootCoordinateY() {
        String coordinateY = "Y";
        return console.askShootCoordinate(coordinateY);
    }
}
