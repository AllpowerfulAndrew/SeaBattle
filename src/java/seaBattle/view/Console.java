package seaBattle.view;

import seaBattle.model.GameConstants;
import seaBattle.model.userExceptions.IncorrectInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс консоли. Отвечает за всё отображение игры в консоли.
 */
public class Console implements GameConstants {

    /**
     * Инициализирует поле.
     * @param x Координата по X.
     * @param y Коордната по Y.
     */
    public void fieldInit(final int x, final int y) {
        VISIBLE_CELLS[x][y] = "|";
        INVISIBLE_CELLS[x][y] = "|";
    }


    /**
     * Отображает видимое игровое поле.
     */
    public void showVisibleField() {
        System.out.println("==============================");
        show(VISIBLE_CELLS);
    }


    /**
     * Отображает невидимое игровое поле.
     */
    public void showInvisibleField() {
        System.out.println("==============================");
        show(INVISIBLE_CELLS);
    }


    /**
     * Вычисляет, можно ли установить отсек по координатам X и Y.
     *
     * @param x Координата X.
     * @param y Координата Y.
     * @return Воззвращает true, если можно установить отсек.
     */
    public boolean isPossibleToLocate(final int x, final int y) {

        //TODO Сократить метод (не разбить на несколько, а сократить). Разработать более компактный алгоритм вычислений.

        /**
         * Проверяет ячейку, находящуюся не на краю карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        if (x + 1 < SIZE && y + 1 < SIZE && x > 0 && y > 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся на ПРАВОМ краю карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x == SIZE - 1 && y + 1 < SIZE && y > 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся на НИЖНЕМ краю карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x + 1 < SIZE && y == SIZE - 1 && x > 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся на ЛЕВОМ краю карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (y + 1 < SIZE && x == 0 && y > 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся на ВЕРХНЕМ краю карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x + 1 < SIZE && x > 0 && y == 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся в НИЖНЕМ ПРАВОМ углу карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x == SIZE - 1 && y == SIZE - 1) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся в ВЕХНЕМ ЛЕВОМ углу карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x == 0 && y == 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся в ВЕРХНЕМ ПРАВОМ углу карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (x == SIZE - 1 && y == 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y + 1].equals("|") &&
                    INVISIBLE_CELLS[x - 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y + 1].equals("|")) {
                return true;
            }
        }

        /**
         * Проверяет ячейку, находящуюся в НИЖНЕМ ЛЕВОМ углу карты и ячейки вокруг неё.
         * Если все ячейки пусты, возвращает true.
         */
        else if (y == SIZE - 1 && x == 0) {
            if (INVISIBLE_CELLS[x][y].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y - 1].equals("|") &&
                    INVISIBLE_CELLS[x + 1][y].equals("|") &&
                    INVISIBLE_CELLS[x][y - 1].equals("|")) {
                return true;
            }
        }

        return false;
    }


    /**
     * Отрисовывает корабли в невидимое для игрока поле.
     */
    public void drawShips(final int x, final int y) {
        INVISIBLE_CELLS[x][y] = "S";
    }


    /**
     * Делаем выстрел.
     * Если попали, то вохвращаем true.
     *
     * @param x Координата выстрела по X.
     * @param y Координата выстрела по Y.
     */
    public boolean doShoot(final int x, final int y) {
        System.out.println("==============================");

        if (INVISIBLE_CELLS[x][y].equals("S")) {
            VISIBLE_CELLS[x][y] = "X";
            INVISIBLE_CELLS[x][y] = "X";

            return true;

        } else if (INVISIBLE_CELLS[x][y].equals("|")) {
            INVISIBLE_CELLS[x][y] = "*";
            VISIBLE_CELLS[x][y] = "*";
            System.out.println("Miss!");
            showVisibleField();

            return false;
        } else if (INVISIBLE_CELLS[x][y].equals("*") || INVISIBLE_CELLS[x][y].equals("X")) {
            System.out.println("Miss!");
            showVisibleField();

            return false;
        }

        return false;
    }


    /**
     * Выводит в консоль сообщение про состояние корабля.
     * Если true, значит корабль уничтожен.
     * Если false, значит корабль ранен.
     *
     * @param isDestroyed Может значить true либо false.
     */
    public void showShipStatus(final boolean isDestroyed) {
        if (isDestroyed) {
            System.out.println("Killed!");
        } else {
            System.out.println("Injured!");
        }

        showVisibleField();
    }


    /**
     * Выводит в консоль сообщение об окончании игры и статистику.
     */
    public void gameOver(final int shoots, final String name) {
        System.out.println("==============================");
        System.out.println("CONGRATULATIONS, "+ name +"! YOU WIN!");
        System.out.println("==============================");
        System.out.println();
        System.out.println("STATISTIC:");
        System.out.println("Shoots: " + shoots);
        System.out.println();
        System.out.println("RESULT:");

        if (shoots < 21) {
            System.out.println("Excellent!");
        } else if (shoots < 31) {
            System.out.println("Good!");
        } else if (shoots < 41) {
            System.out.println("Normal.");
        } else if (shoots < 51) {
            System.out.println("Bad!");
        } else if (shoots > 50) {
            System.out.println("Awful!");
        }
        System.out.println();
        System.out.println("==============================");
    }


    /**
     * Спрашивает у пользователя имя, чтобы знать, как обращаться.
     *
     * @return Возвращает имя.
     */
    public String askPlayerName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name, please:");

        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.err.println("Incorrect value. Enter again:");
            } finally {
                System.out.println("==============================");
            }
        }
    }


    /**
     * Спрашивает у пользователя координату выстрела.
     *
     * @param coordinate Строчное значение. Должно значить либо X либо Y.
     * @return Возвращает координату по X или Y.
     */
    public int askShootCoordinate(final String coordinate, final String name) {
        if (coordinate.equals("X")) {
            System.out.println("==============================");
        }

        System.out.println("Enter shoot coordinate " + coordinate + ":");

        while (true) {
            try {
                if (coordinate.equals("X")) {
                    return getInputX(name);
                } else if (coordinate.equals("Y")) {
                    return getInputY(name);
                }
            } catch (IncorrectInput e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                System.err.println("Incorrect value, "+ name + ". Enter correct " + coordinate + " coordinate:");
            }
        }
    }

    /**
     * Всопмогательный метод. Циклично запрашивает у пользователя координату X пока она не пройдёт валидацию.
     *
     * @return Возвращает координату X введённую пользователем.
     * @throws IOException    Системное исклчючение.
     * @throws IncorrectInput Исключение возникает, если введы не правильные данные.
     */
    private int getInputX(final String name) throws IOException, IncorrectInput {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                int x = Integer.parseInt(reader.readLine());

                if (x > 10 || x < 1) {
                    throw new IncorrectInput("Incorrect value, "+ name +". Please enter number from 1 to 10:");
                }

                return x;
            } catch (NumberFormatException e) {
                System.err.println("Incorrect value, "+ name +". Please enter number from 1 to 10:");
            }
        }
    }


    /**
     * Всопмогательный метод. Циклично запрашивает у пользователя координату Y пока она не пройдёт валидацию.
     *
     * @return Возвращает координату Y введённую пользователем.
     * @throws IOException    Системное исклчючение.
     * @throws IncorrectInput Исключение возникает, если введы не правильные данные.
     */
    private int getInputY(final String name) throws IOException, IncorrectInput {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String y = reader.readLine();

            for (int i = 0; i < VALID_LETTERS.length; i++) {
                if (VALID_LETTERS[i].equals(y)) {
                    return i / TWO + ONE;
                }
            }

            throw new IncorrectInput("Incorrect value, "+ name +". Please enter letter from A(a) to J(j):");
        }
    }

    /**
     * Отмечет все пустые соседние ячейки отсека знаком "Мимо".
     *
     * @param x Координата ячейки по X.
     * @param y Координата ячейки по Y.
     */
    public void makeMiss(final int x, final int y) {
        if (!INVISIBLE_CELLS[x][y].equals("X")) VISIBLE_CELLS[x][y] = "*";
    }


    /**
     * Отображает поле в консоли.
     */
    private void show(String[][] field) {
        System.out.println();
        System.out.print("   ");

        /**
         * Выводим в консоль все буквы.
         */
        for (int i = 0; i < SIZE; i++) {
            System.out.print(COLUMNS[i] + " ");
        }

        System.out.println();

        /**
         * Выводим в консоль все строки по очереди поля по очереди.
         * Перед каждой строкой выводим цифру.
         */
        for (int i = 0; i < SIZE; i++) {
            System.out.print(LINES[i] + " ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}