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
        setPlayer();
        field.init();
        field.createShips();
        gameController.showVisibleField();

        do {
            doShoot();
        } while (!field.isGameOver(player.getName()));
    }


    /**
     * Делаем выстрел.
     */
    private void doShoot() {
        int x = gameController.askShootCoordinateX(player.getName()) - 1;
        int y = gameController.askShootCoordinateY(player.getName()) - 1;

        field.doShoot(x, y, player.getName());
    }


    /**
     * Создаёт игрока.
     */
    private void setPlayer() {
        player = new Player();
    }
}