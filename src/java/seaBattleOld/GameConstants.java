package seaBattleOld;

public interface GameConstants {
    int SIZE = 10;
    String[][] visibleCells = new String[SIZE][SIZE];
    String[][] invisibleCells = new String[SIZE][SIZE];
    String CHECK = "АаБбВвГгДдЕеЖжЗзИиКк0123456789 ";
    String SMALL_LETTERS = "абвгдежзик";
    String BIG_LETTERS = "АБВГДЕЖЗИК";
    String NUMBERS = "0123456789";
    char[] COLUMNS = new char[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К'};
    char[] LINES = new char[]{'⒈', '⒉', '⒊', '⒋', '⒌', '⒍', '⒎', '⒏', '⒐', '⒑'};

}
