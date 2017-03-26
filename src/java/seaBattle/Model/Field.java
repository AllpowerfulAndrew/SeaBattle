package seaBattle;

/**
 * Класс игрового поля.
 */
public class Field implements GameConstants {

    /**
     * Инициализирует поле.
     */
    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                VISIBLE_CELLS[i][j] = "|";
                INVISIBLE_CELLS[i][j] = "|";
            }
        }
    }


}
