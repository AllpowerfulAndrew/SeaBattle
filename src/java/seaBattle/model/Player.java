package seaBattle.model;

import seaBattle.controller.PlayerController;

/**
 * Класс игрока.
 */
public class Player {
    private String name;

    /**
     * Объект контроллера для взаимодействия с Console.
     */
    PlayerController controller = new PlayerController();


    /**
     * Конструктор.
     * При инициализации отправляет запрос в Console, чтобы узнать имя игрока.
     */
    public Player() {
        this.name = controller.askPlayerName();
    }


    /**
     * Возвращает имя игрока.
     * @return Имя игрока.
     */
    public String getName() {
        return name;
    }
}
