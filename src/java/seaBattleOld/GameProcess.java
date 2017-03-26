import java.io.IOException;

public class GameProcess {
    public static int shoots;

    public void play() throws IOException {

        Field field = new Field();
        Player player = new Player();

        player.about();
        field.locateShips();
        field.show();
        field.askShootCoordinates();

        System.out.println();
        System.out.println("Конец игры! Поздравляем, " + Player.name + "!");

        statistic();

        System.out.println();
        System.out.println("Заходите ещё!");
    }

    public void statistic() {
        System.out.print("Количество ходов: " + shoots + "! ");

        if (shoots == 20) {
            System.out.println("Это.. это же просто потрясающая победа! Вы явно либо читер, либо везунчик, ведь это же идеальный результат!");
        } else if (shoots <= 30){
            System.out.println("Ого! Это быстрая победа! Мало кто так может... Мои поздравления!");
        } else if (shoots <= 50) {
            System.out.println("В хорошо постарались. Но помните, нет предела совершенству!");
        } else if (shoots <= 70) {
            System.out.println(shoots + "!? Серьёзно!? Нельзя было результат получше? Хотя что я, тупой компьютер, понимаю, правда? (Это риторический вопрос)");
        } else if (shoots <= 100){
            System.out.println("Похоже, прежде чем победить, Вы напрочь изрешетили поле! Да Вы настоящий маньяк, скажу я Вам! За столько выстрелов можно было целый флот потопить.");
        }
    }
}