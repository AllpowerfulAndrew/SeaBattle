package seaBattle;

/**
 * Вспомогательный интерфейс.
 */
public interface GameConstants {

    /**
     * Игровые константы.
     */
    int SIZE = 10;
    String[][] VISIBLE_CELLS = new String[SIZE][SIZE];
    String[][] INVISIBLE_CELLS = new String[SIZE][SIZE];
    String VALID_CHARACTERS = "AaBbCcDdEeFfGgHhIiJj0123456789 ";
    String SMALL_LETTERS = "abcdefghij";
    String BIG_LETTERS = "ABCDEFGHIJ";
    String NUMBERS = "0123456789";
    char[] COLUMNS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    char[] LINES = new char[]{'⒈', '⒉', '⒊', '⒋', '⒌', '⒍', '⒎', '⒏', '⒐', '⒑'};

}
