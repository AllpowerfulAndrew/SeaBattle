package seaBattle.controller;

import seaBattle.view.Console;

/**
 * Класс дял взаимодействия классов Player и Console.
 */
public class PlayerController {
    Console console = new Console();

    /**
     * Отправляет запрос в Console, чтобы узнать имя пользователя.
     * @return Возвращает имя.
     */
    public String askPlayerName() {
        return console.askPlayerName();
    }
}
