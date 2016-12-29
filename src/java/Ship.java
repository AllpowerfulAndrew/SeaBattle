package model;

public class Ship {

    public final static Ship[] SHIPS = new Ship[]{new Ship(4, 0), new Ship(3, 1), new Ship(3, 2), new Ship(2, 3), new Ship(2, 4),
            new Ship(2, 5), new Ship(1, 6), new Ship(1, 7), new Ship(1, 8), new Ship(1, 9)};
    private static int shipsCount;
    private int size;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Ship(int size, int number) {
        this.size = size;
        this.number = number;
    }

    public static boolean isShipAlive(final int s) {
        for (Ship ship : SHIPS) {
            if (s == ship.getNumber()) {
                setShipLife(s);
                return ship.getSize() != 0;
            }
        }

        return false;
    }

    public static int getShipsCount() {

        setShipsCount(SHIPS);
        return shipsCount;
    }

    private static void setShipsCount(final Ship[] ships) {

        shipsCount = 0;

        for (Ship ship : ships) {
            if (isShipAlive(ship.getNumber())) {
                shipsCount++;
            }
        }
    }

    private static void setShipLife(final int n) {
        for (Ship ship : SHIPS) {
            if (n == ship.getNumber()) {
                String s = String.valueOf(n);
                ship.setSize(Field.checkShipLives(s));
            }
        }
    }
}