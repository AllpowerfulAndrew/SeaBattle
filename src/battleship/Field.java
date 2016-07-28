package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Field {

    final static int SIZE = 10;
    static String[][] visibleCells = new String[SIZE][SIZE];
    static String[][] invisibleCells = new String[SIZE][SIZE];
    final static char[] COLUMNS = new char[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К'};
    final static char[] LINES = new char[]{'⒈', '⒉', '⒊', '⒋', '⒌', '⒍', '⒎', '⒏', '⒐', '⒑'};
    final static String CHECK = "АаБбВвГгДдЕеЖжЗзИиКк0123456789 ";
    final static String SMALL_LETTERS = "абвгдежзик";
    final static String BIG_LETTERS = "АБВГДЕЖЗИК";
    final static String NUMBERS = "0123456789";

    public void init() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                visibleCells[i][j] = "|";
                invisibleCells[i][j] = "|";
            }
        }

        System.out.println();
        System.out.println("Да начнётся игра! Теперь вводите координаты выстрела, используя русскую раскладку.");
    }

    public void show() {
        System.out.println();
        System.out.print("   ");

        for (int i = 0; i < SIZE; i++) {
            System.out.print(COLUMNS[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(LINES[i] + " ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(visibleCells[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int checkShipLifes(final String n) {

        int l = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (n.equals(invisibleCells[i][j])) {
                    l++;
                }
            }
        }

        return l;
    }

    public void doShoot(final int x, final int y) {

        if (!invisibleCells[x][y].equals("X") && !invisibleCells[x][y].equals("|") && !invisibleCells[x][y].equals("*")) {
            int s = Integer.parseInt(invisibleCells[x][y]);
            visibleCells[x][y] = "X";
            invisibleCells[x][y] = "X";

            if (Ship.isShipAlive(s)) {
                System.out.println("Ранен!");
            } else {
                System.out.println("Убит!");
            }
        } else if (invisibleCells[x][y].equals("X") || invisibleCells[x][y].equals("*")) {
            System.out.println("Сюда уже стреляли.");
        } else if (invisibleCells[x][y].equals("|")) {
            invisibleCells[x][y] = "*";
            visibleCells[x][y] = "*";
            System.out.println("Мимо!");
        }

        show();
    }

    public void locateShips() {
        Random random = new Random();
        init();

        for (Ship SHIP : Ship.SHIPS) {

            int size = SHIP.getSize();
            String s = String.valueOf(SHIP.getNumber());
            int life;

            do {
                int x = random.nextInt(SIZE);
                int y = random.nextInt(SIZE);
                int r = random.nextInt(4);
                life = 0;
                int a = 0;
                int b = 0;

                switch (r) {
                    case 0:
                        a = 1;
                        break;
                    case 1:
                        b = 1;
                        break;
                    case 2:
                        a = -1;
                        break;
                    case 3:
                        b = -1;
                        break;
                }

                if (shipSetting(size, s, life, x, y, a, b)) break;
            } while (true);
        }
    }

    private boolean shipSetting(final int size, final String s, int life, final int x, final int y, final int a, final int b) {
        if (size > 0 && x < SIZE && x >= 0 && y < SIZE && y >= 0 && checkPossibilityOfShipLocating(x, y, s)) {
            life++;

            if (size == life) {
                invisibleCells[x][y] = s;
                return true;
            }

            if (size > 1 && x + a < SIZE && x + a >= 0 && y + b < SIZE && y + b >= 0 && checkPossibilityOfShipLocating(x + a, y + b, s)) {
                life++;

                if (size == life) {
                    invisibleCells[x][y] = s;
                    invisibleCells[x + a][y + b] = s;
                    return true;
                }

                if (size > 2 && x + a + a < SIZE && x + a + a >= 0 && y + b + b < SIZE && y + b + b >= 0 && checkPossibilityOfShipLocating(x + a + a, y + b + b, s)) {
                    life++;

                    if (size == life) {
                        invisibleCells[x][y] = s;
                        invisibleCells[x + a][y + b] = s;
                        invisibleCells[x + a + a][y + b + b] = s;
                        return true;
                    }

                    if (size > 3 && x + a + a + a < SIZE && x + a + a + a >= 0 && y + b + b + b < SIZE && y + b + b + b >= 0 && checkPossibilityOfShipLocating(x + a + a + a, y + b + b + b, s)) {
                        life++;

                        if (size == life) {
                            invisibleCells[x][y] = s;
                            invisibleCells[x + a][y + b] = s;
                            invisibleCells[x + a + a][y + b + b] = s;
                            invisibleCells[x + a + a + a][y + b + b + b] = s;
                            return true;
                        }
                    } else if (size > 3 && x - a < SIZE && x - a >= 0 && y - b < SIZE && y - b >= 0 && checkPossibilityOfShipLocating(x - a, y - b, s)) {
                        life++;

                        if (size == life) {
                            invisibleCells[x][y] = s;
                            invisibleCells[x + a][y + b] = s;
                            invisibleCells[x + a + a][y + b + b] = s;
                            invisibleCells[x - a][y - b] = s;
                            return true;
                        }
                    }
                } else if (size > 2 && x - a < SIZE && x - a >= 0 && y - b < SIZE && y - b >= 0 && checkPossibilityOfShipLocating(x - a, y - b, s)) {
                    life++;

                    if (size == life) {
                        invisibleCells[x][y] = s;
                        invisibleCells[x + a][y + b] = s;
                        invisibleCells[x - a][y - b] = s;
                        return true;
                    }

                    if (size > 3 && x - a - a < SIZE && x - a - a >= 0 && y - b - b < SIZE && y - b - b >= 0 && checkPossibilityOfShipLocating(x - a - a, y - b - b, s)) {
                        life++;

                        if (size == life) {
                            invisibleCells[x][y] = s;
                            invisibleCells[x + a][y + b] = s;
                            invisibleCells[x - a][y - b] = s;
                            invisibleCells[x - a - a][y - b - b] = s;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPossibilityOfShipLocating(final int x, final int y, final String s) {

        if (x + 1 < SIZE && y + 1 < SIZE && x - 1 >= 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x + 1][y + 1].equals("|") && invisibleCells[x - 1][y - 1].equals("|") &&
                    invisibleCells[x + 1][y - 1].equals("|") && invisibleCells[x - 1][y + 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                        if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x - 1][y].equals(s)) {
                            if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else if (x + 1 >= SIZE && y + 1 < SIZE && x - 1 >= 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x - 1][y - 1].equals("|") && invisibleCells[x - 1][y + 1].equals("|")) {
                if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                    if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                        if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x - 1][y].equals(s)) {
                            return true;
                        }
                    }
                }

            }
        } else if (x + 1 < SIZE && y + 1 >= SIZE && x - 1 >= 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x - 1][y - 1].equals("|") && invisibleCells[x + 1][y - 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x - 1][y].equals(s)) {
                        if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                            return true;
                        }
                    }
                }
            }
        } else if (x + 1 < SIZE && y + 1 >= SIZE && x - 1 >= 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x + 1][y + 1].equals("|") && invisibleCells[x + 1][y - 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                        if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                            return true;
                        }
                    }
                }
            }
        } else if (x + 1 < SIZE && y + 1 < SIZE && x - 1 >= 0 && y - 1 < 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x + 1][y + 1].equals("|") && invisibleCells[x - 1][y + 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x - 1][y].equals(s)) {
                        if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                            return true;
                        }
                    }
                }
            }
        } else if (x + 1 >= SIZE && y + 1 >= SIZE && x - 1 >= 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x - 1][y - 1].equals("|")) {
                if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x - 1][y].equals(s)) {
                    if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                        return true;
                    }
                }
            }
        } else if (x + 1 < SIZE && y + 1 < SIZE && x - 1 < 0 && y - 1 < 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x + 1][y + 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                        return true;
                    }
                }
            }
        } else if (x + 1 >= SIZE && y + 1 < SIZE && x - 1 >= 0 && y - 1 < 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x - 1][y + 1].equals("|")) {
                if (invisibleCells[x - 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x][y + 1].equals("|") || invisibleCells[x][y + 1].equals(s)) {
                        return true;
                    }
                }
            }
        } else if (x + 1 < SIZE && y + 1 >= SIZE && x - 1 < 0 && y - 1 >= 0) {
            if (invisibleCells[x][y].equals("|") && invisibleCells[x + 1][y - 1].equals("|")) {
                if (invisibleCells[x + 1][y].equals("|") || invisibleCells[x + 1][y].equals(s)) {
                    if (invisibleCells[x][y - 1].equals("|") || invisibleCells[x][y - 1].equals(s)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void askShootCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String coordinates;

        while (Ship.getShipsCount() != 0) {
            boolean isTrueCoordinates = false;

            do {
                System.out.println("===================================");
                System.out.print("Введите координаты: ");
                coordinates = reader.readLine();
                System.out.println("===================================");
                System.out.println();
                char[] chars = coordinates.toCharArray();
                String numberX = "0";
                int x;
                int y = 0;
                boolean isRightCoordinates = false;
                int rightCoordinates = 0;

                for (char c : chars) {
                    String s = "" + c;
                    if (!CHECK.contains(s)) {
                        break;
                    } else {
                        if (NUMBERS.contains(s)) {
                            numberX = numberX + s;
                            isRightCoordinates = true;
                        } else if (BIG_LETTERS.contains(s)) {
                            char[] let = BIG_LETTERS.toCharArray();
                            for (int i = 0; i <= let.length; i++) {
                                if (let[i] == c) {
                                    y = i;
                                    rightCoordinates++;
                                    break;
                                }
                            }
                        } else if (SMALL_LETTERS.contains(s)) {
                            char[] let = SMALL_LETTERS.toCharArray();
                            for (int i = 0; i <= let.length; i++) {
                                if (let[i] == c) {
                                    y = i;
                                    rightCoordinates++;
                                    break;
                                }
                            }
                        }
                    }
                }

                x = Integer.parseInt(numberX);

                if (x <= Field.SIZE && x > 0 && y <= Field.SIZE && y >= 0 && rightCoordinates == 1 && isRightCoordinates) {
                    doShoot(x - 1, y);
                    GameProcess.shoots++;
                    isTrueCoordinates = true;
                } else {
                    System.out.println("Введены ошибочные координаты. Попробуйте ещё раз. (например: а4)");
                    show();
                }
            } while (!isTrueCoordinates);
        }
    }
}