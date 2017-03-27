package seaBattle.model.userExceptions;

/**
 * Класс исключения, для предупреждения игрока о неверно введённых данных.
 */
public class IncorrectInput extends Exception {

    /**
     * Конструктор.
     * Передаёт сообщение предку.
     * @param message Сообщение.
     */
    public IncorrectInput(String message) {
        super(message);
    }
}
