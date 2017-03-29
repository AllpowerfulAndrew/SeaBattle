package seaBattle.controller;

import seaBattle.view.Console;

/**
 * Класс контроллера, который соединяет консоль с командами из класса Field.
 */
public class FieldController {
    Console console = new Console();

    /**
     * Посылает запрос в класс Console на проверку координат для установки секции корабля на поле.
     * @param x Координата по X.
     * @param y Координата по Y.
     * @return Возвращает true, если установка секции возможна.
     */
    public boolean isPossibleToLocate(final int x, final int y) {
        return console.isPossibleToLocate(x, y);
    }


    /**
     * Запрос в Condole для инициализации поля.
     * @param x Координата по X.
     * @param y Коордната по Y.
     */
    public void fieldInit(final int x, final int y) {
        console.fieldInit(x, y);
    }

    /**
     * Запрос в Console для прорисовки кораблей на поле и их отображение.
     */
    public void drawShips(final int x, final int y) {
        console.drawShips(x, y);
    }


    /**
     * Отправляет запрос в Console, чтобы проверить, есть ли попадание после выстрела.
     * @param x Координата выстрела по X.
     * @param y Координата выстрела по Y.
     * @return Возвращает true, если есть попадание.
     */
    public boolean isHit(final int x, final int y) {
        return console.doShoot(x, y);
    }


    /**
     * Отправляет в консоль значение boolean о состоянии корабля.
     * @param isDestroyed Может значить true или false
     */
    public void showShipStatus(final boolean isDestroyed) {
        console.showShipStatus(isDestroyed);
    }


    /**
     * Отмечет все пустые соседние ячейки отсека знаком "Мимо".
     * @param x Координата ячейки по X.
     * @param y Координата ячейки по Y.
     */
    public void makeMiss(final int x, final int y) {
        console.makeMiss(x, y);
    }


    /**
     * Выводит на экран сообщение об окончании игры.
     */
    public void gameOver(final int shoots, final String name) {
        console.gameOver(shoots, name);
    }
}
