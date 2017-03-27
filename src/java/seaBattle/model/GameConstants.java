package seaBattle.model;

/**
 * Вспомогательный интерфейс.
 */
public interface GameConstants {

    /**
     * Игровые константы.
     */
    int SIZE = 10;
    int TEN = 10;
    int NINE = 9;
    int SIX = 6;
    int FOUR = 4;
    int THREE = 3;
    int TWO = 2;
    int ONE = 1;

    String[][] VISIBLE_CELLS = new String[SIZE][SIZE];
    String[][] INVISIBLE_CELLS = new String[SIZE][SIZE];
    Ship[] SHIPS = new Ship[SIZE];
    String[] VALID_LETTERS = {"A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f", "G", "g", "H", "h", "I", "i", "J", "j"};
    String[] SMALL_LETTERS = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    String[] BIG_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String NUMBERS = "0123456789";
    char[] COLUMNS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    char[] LINES = new char[]{'⒈', '⒉', '⒊', '⒋', '⒌', '⒍', '⒎', '⒏', '⒐', '⒑'};

}
