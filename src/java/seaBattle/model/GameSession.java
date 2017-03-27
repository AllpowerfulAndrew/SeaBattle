package seaBattle.model;

import seaBattle.controller.GameController;

/**
 * Класс игровой сессии.
 * Здесь прописан весь процесс игры.
 */
public class GameSession implements GameConstants {

    /**
     * Создаём игровое поле и класс консоли для отображения поля.
     */
    private Field field = new Field();
    private GameController gameController = new GameController();
    private Player player;


    /**
     * Игровой процесс.
     */
    public void gameStart() {
        gameController.fieldInit();
        field.createShips();
        gameController.showInvisibleField();

        do {
            doShoot();
        } while (!field.isGameOver());
    }


    /**
     * Делаем выстрел.
     */
    private void doShoot() {
        int x = gameController.askShootCoordinateX() - 1;
        int y = gameController.askShootCoordinateY() - 1;

        field.doShoot(x, y);
    }


    /**
     * Создаёт игрока.
     */
    private void setPlayer() {
        player = new Player();
    }
}
