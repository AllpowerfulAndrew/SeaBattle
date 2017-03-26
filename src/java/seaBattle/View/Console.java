package seaBattle.View;

import seaBattle.Model.GameConstants;

public class Console implements GameConstants{

    /**
     * Отображает поле в консоли.
     */
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
                System.out.print(VISIBLE_CELLS[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
