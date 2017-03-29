package seaBattle.model;

import seaBattle.controller.FieldController;

import java.util.Random;

/**
 * Класс игрового поля.
 */
public class Field implements GameConstants {
    private FieldController fieldController = new FieldController();
    private int shoots;

    /**
     * Отправляет запрос в Condole для инициализации поля.
     */
    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fieldController.fieldInit(i, j);
            }
        }
    }


    /**
     * Создаёт все игровые корабли.
     */
    public void createShips() {
        for (int i = 0; i < TEN; i++) {
            if (i == 0) {
                SHIPS[i] = setShipCoordinates(FOUR);
                drawShips();
            } else if (i > 0 && i < THREE) {
                SHIPS[i] = setShipCoordinates(THREE);
                drawShips();
            } else if (i > TWO && i < SIX) {
                SHIPS[i] = setShipCoordinates(TWO);
                drawShips();
            } else {
                SHIPS[i] = setShipCoordinates(ONE);
                drawShips();
            }
        }
    }

    /**
     * Отправялет координаты в Console для отрисовки кораблей.
     */
    private void drawShips() {
        for (Ship ship : SHIPS) {
            if (ship != null) {
                for (int i = 0; i < ship.getSections().length; i++) {
                    fieldController.drawShips(ship.getSections()[i].getPositionX(), ship.getSections()[i].getPositionY());
                }
            }
        }
    }


    /**
     * Делаем выстрел.
     *
     * @param coordinateX Координата выстрела по X.
     * @param coordinateY Координата выстрела по Y.
     */
    public void doShoot(final int coordinateX, final int coordinateY, final String name) {
        shoots++;

        if (fieldController.isHit(coordinateX, coordinateY)) {
            damageShip(coordinateX, coordinateY);

            if (isShipDestroyed(coordinateX, coordinateY)) {
                makeMissAroundShip(findShip(coordinateX, coordinateY));
            }

            fieldController.showShipStatus(isShipDestroyed(coordinateX, coordinateY));
        }
    }


    /**
     * Отправляет запрос в Console, чтобы отметить все клетки вокруг подбитого корабля знаком "Miss".
     *
     * @param shipNumber Номер подбитого корабля.
     */
    public void makeMissAroundShip(final int shipNumber) {
        for (Ship.Section section : SHIPS[shipNumber].getSections()) {
            int x = section.getPositionX();
            int y = section.getPositionY();

            missMaking(x, y);
        }
    }


    /**
     * Проверяет все корабли. Если все уничтожены, то вовзращает true.
     *
     * @return Возвращает true или false.
     */
    public boolean isGameOver(final String name) {
        for (Ship ship : SHIPS) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }

        fieldController.gameOver(shoots, name);
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
     *
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


    /**
     * Вызывается другим методом для отметки соседнихячеек подбитого корабля знаком "Мимо".
     *
     * @param x Координата по X.
     * @param y Координата по Y.
     */
    private void missMaking(int x, int y) {
        // TODO Подумать над тем, как можно оптимизировать и сократить этот метод.

        if (x == SIZE - 1 && y == 0) {
            for (int i = x; i > x - 2; i--) {
                for (int j = y; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (x == SIZE - 1 && y == SIZE - 1) {
            for (int i = x; i > x - 2; i--) {
                for (int j = y; j > y - 2; j--) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (x == 0 && y == SIZE - 1) {
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j > y - 2; j--) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (x == 0 && y == 0) {
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (x == 0) {
            for (int i = x; i < x + 1; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (y == 0) {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (x == SIZE - 1) {
            for (int i = x; i > x - 2; i--) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else if (y == SIZE - 1) {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 1; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        } else {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (i == x && j == y) continue;
                    fieldController.makeMiss(i, j);
                }
            }
        }
    }


    /**
     * Возвращает сумму сделанных выстрелов пользователем.
     *
     * @return Выстрелы пользователя.
     */
    private int getShoots() {
        return shoots;
    }
}
