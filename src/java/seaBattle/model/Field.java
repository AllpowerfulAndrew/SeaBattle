package seaBattle.model;

import seaBattle.controller.FieldController;

import java.util.Random;

/**
 * Класс игрового поля.
 */
public class Field implements GameConstants {
    FieldController fieldController = new FieldController();

    /**
     * Создаёт все игровые корабли.
     */
    public void createShips() {
        for (int i = 0; i < TEN; i++) {
            if (i == 0) {
                SHIPS[i] = setShipCoordinates(FOUR);
                fieldController.drawShips();
            } else if (i > 0 && i < THREE) {
                SHIPS[i] = setShipCoordinates(THREE);
                fieldController.drawShips();
            } else if (i > TWO && i < SIX) {
                SHIPS[i] = setShipCoordinates(TWO);
                fieldController.drawShips();
            } else {
                SHIPS[i] = setShipCoordinates(ONE);
                fieldController.drawShips();
            }
        }
    }


    /**
     * Делаем выстрел.
     *
     * @param coordinateX Координата выстрела по X.
     * @param coordinateY Координата выстрела по Y.
     */
    public void doShoot(final int coordinateX, final int coordinateY) {
        if (fieldController.isHit(coordinateX, coordinateY)) {
            damageShip(coordinateX, coordinateY);

            if (isShipDestroyed(coordinateX, coordinateY)) {
                fieldController.makeMissAroundShip(findShip(coordinateX, coordinateY));
            }

            fieldController.showShipStatus(isShipDestroyed(coordinateX, coordinateY));
        }
    }


    /**
     * Проверяет все корабли. Если все уничтожены, то вовзращает true.
     *
     * @return Возвращает true или false.
     */
    public boolean isGameOver() {
        for (Ship ship : SHIPS) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }

        fieldController.gameOver();
        return true;
    }


    /**
     * Генерирует корабль с корректными координатами
     * (то есть, чтобы не пересекался с другими кораблями и имел расстояне от других кораблей минимум на одну клетку).
     *
     * @param size Размер корабля.
     * @return Возвращает готовый объект корабля.
     */
    private Ship setShipCoordinates(int size) {
        int[] coordinatesX = new int[size];
        int[] coordinatesY = new int[size];
        boolean isCorrectCoordinates = false;

        /**
         * Цикл работает, пока не сгенерируются верные координаты.
         */
        do {
            int x = generateInt();
            int y = generateInt();

            if (generateBoolean()) {
                for (int i = 0; i < size; i++) {
                    coordinatesX[i] = x;
                }

                coordinatesY = setDirectionCoordinates(size, coordinatesY, generateBoolean(), y);
            } else {
                for (int i = 0; i < size; i++) {
                    coordinatesY[i] = y;
                }

                coordinatesX = setDirectionCoordinates(size, coordinatesX, generateBoolean(), x);
            }

            isCorrectCoordinates = checkCoordinates(coordinatesX, coordinatesY);
        } while (!isCorrectCoordinates);

        return new Ship(size, coordinatesX, coordinatesY);
    }


    /**
     * Определяет координаты корабля по одной случайной оси координат.
     *
     * @param size        Величина корабля (количество отсеков).
     * @param coordinates Координаты по одной случайно оси.
     * @param isIncrease  Если true, то координаты генерируются по возрастанию и наоборот.
     * @param value       Значение координат.
     * @return Возвращает массив координат.
     */
    private int[] setDirectionCoordinates(int size, int[] coordinates, boolean isIncrease, int value) {
        if (isIncrease) {

            for (int i = 0; i < size; i++) {
                coordinates[i] = value;
                value++;
            }
        } else {

            for (int i = 0; i < size; i++) {
                coordinates[i] = value;
                value--;
            }
        }

        return coordinates;
    }


    /**
     * Генерирует случайное значение типа boolean.
     *
     * @return Возвращает true или false.
     */
    private boolean generateBoolean() {
        return new Random().nextBoolean();
    }


    /**
     * Генерирует случайное значение типа int.
     *
     * @return Возвращает число в пределах константы SIZE.
     */
    private int generateInt() {
        return new Random().nextInt(SIZE);
    }


    /**
     * Проверяет координаты корабля на корректное расположение.
     *
     * @param coordinatesX Координаты расположения по X.
     * @param coordinatesY Координаты расположения по Y.
     * @return Возвращает true, если координаты правильные.
     */
    private boolean checkCoordinates(int[] coordinatesX, int[] coordinatesY) {
        for (int i = 0; i < coordinatesX.length; i++) {
            if (!fieldController.isPossibleToLocate(coordinatesX[i], coordinatesY[i])) {
                return false;
            }
        }

        return true;
    }


    /**
     * Проверяет жив ли корабль, в отсек которого попали.
     *
     * @param coordinateX Координата отсека по Х.
     * @param coordinateY Координата отсека по Y.
     */
    private boolean isShipDestroyed(final int coordinateX, final int coordinateY) {
        return SHIPS[findShip(coordinateX, coordinateY)].isDestroyed();
    }


    /**
     * Установить отсеку корабля статус повреждённого.
     *
     * @param coordinateX Координата отсека по X.
     * @param coordinateY Координата отсека по Y.
     */
    private void damageShip(final int coordinateX, final int coordinateY) {
        for (Ship ship : SHIPS) {
            for (int i = 0; i < ship.getSections().length; i++) {
                if (ship.getSections()[i].getPositionX() == coordinateX) {
                    if (ship.getSections()[i].getPositionY() == coordinateY) {
                        ship.getSections()[i].damage();
                    }
                }
            }
        }
    }


    /**
     * Определяет корабль по координатам отсека.
     * @param coordinateX Координата отсека по X.
     * @param coordinateY Координата отсека по Y.
     * @return Возвращает номер корабля в массиве кораблей.
     */
    private int findShip(final int coordinateX, final int coordinateY) {
        for (int j = 0; j < SHIPS.length; j++) {
            for (int i = 0; i < SHIPS[j].getSections().length; i++) {
                if (SHIPS[j].getSections()[i].getPositionX() == coordinateX) {
                    if (SHIPS[j].getSections()[i].getPositionY() == coordinateY) {
                        return j;
                    }
                }
            }
        }

        // -1 написан просто так. Хотя до этого по логике не должно дойти никогда.
        return -1;
    }
}
