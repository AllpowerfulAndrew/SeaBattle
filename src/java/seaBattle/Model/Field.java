package seaBattle.Model;

/**
 * Класс игрового поля.
 */
public class Field implements GameConstants {

    /**
     * Конструктор.
     */
    public Field() {
        init();
    }


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
