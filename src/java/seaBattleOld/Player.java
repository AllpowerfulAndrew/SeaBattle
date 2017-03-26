package seaBattleOld;

import java.util.Scanner;

public class Player {
    public static String name;

    public void about() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру Морской бой!");
        System.out.println("Напишите-ка, как к Вам обращаться.");
        name = scanner.nextLine();
        System.out.println("===================================");
        System.out.print("Первосходно, " + name + "! ");
    }
}