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
     * Запрос в Console для прорисовки кораблей на поле и их отображение.
     */
    public void drawShips() {
        console.drawShips();
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
     * Отмечет все соседние ячейки потопленного корабля, знаком "Мимо".
     * @param shipNumber Номер подбитого корабля.
     */
    public void makeMissAroundShip(final int shipNumber) {
        console.makeMissAroundShip(shipNumber);
    }


    /**
     * Выводит на экран сообщение об окончании игры.
     */
    public void gameOver() {
        console.gameOver();
    }
}
