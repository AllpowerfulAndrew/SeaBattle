package seaBattle.model;

/**
 * Класс корабля.
 */
public class Ship {
    private final int size;

    /**
     * Массив отсеков корабля.
     */
    private Section[] sections;

    /**
     * Переменная, которая хранит в себе состояние всего корабля.
     * По умолчанию значение false.
     * Если все отсеки корабля изменят своё значение isDamaged на true,
     * то это значение автоматически станет true.
     */
    private boolean isDestroyed;


    /**
     * Конструктор.
     * @param size Размер корабля.
     */
    public Ship(int size, int[] positionsX, int[]positionY) {
        this.size = size;
        this.sections = new Section[size];

        setSectionsPosition(positionsX, positionY);
    }


    /**
     * Класс отсека корабля.
     */
    public class Section {
        private final int positionX;
        private final int positionY;

        /**
         * Переменная, которая хранит всебе состояние отсека.
         * По умолчанию отсек не повреждён (целый).
         */
        private boolean isDamaged;


        /**
         * Конструктор.
         * @param positionX Позиция отсека по Х.
         * @param positionY Позиция отсека по Y.
         */
        public Section(int positionX, int positionY) {
            this.positionX = positionX;
            this.positionY = positionY;
        }


        /**
         * Возвращает позицию отсека по Х.
         * @return Позиция по Х.
         */
        public int getPositionX() {
            return positionX;
        }


        /**
         * Возвращает позициюотсека по Y.
         * @return Позиция по Y.
         */
        public int getPositionY() {
            return positionY;
        }


        /**
         * Возвращает состояние отсека.
         * @return Повреждён или нет.
         */
        public boolean isDamaged() {
            return isDamaged;
        }


        /**
         * Устанавливает состояние отсека корабля, как подбитое (повреждённое).
         */
        public void damage() {
            if (!isDamaged) {
                isDamaged = true;
                checkShipStatus();
            }
        }
    }


    /**
     * Устанавливает всем отсекам их позицию.
     * @param positionX Позиция всех отсеков по Х.
     * @param positionY Позиция всех отсеков по Y.
     */
    private void setSectionsPosition(final int[] positionX, final int[] positionY) {
        for (int i = 0; i < size; i++) {
            sections[i] = new Section(positionX[i], positionY[i]);
        }
    }


    /**
     * Проверяет все отсеки коробля.
     * Если все уничтожены, устанавливает кораблю статус: Уничтожен.
     */
    private void checkShipStatus() {

        isDestroyed = true;

        for (Section section : sections) {
            if (!section.isDamaged()) {
                isDestroyed = false;
                break;
            }
        }
    }


    /**
     * Возвращает размер корабля.
     * @return Размер коробля.
     */
    public int getSize() {
        return size;
    }


    /**
     * Возвращает состояние корабля.
     * @return Уничтожен или нет.
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }


    /**
     * Возвращает массив всех отсеков корабля.
     * @return Отсеки корабля.
     */
    public Section[] getSections() {
        return sections;
    }
}