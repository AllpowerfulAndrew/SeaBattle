package seaBattleFirstVersion;

import seaBattleFirstVersion.view.GameProcess;

import java.io.IOException;

public class GameStart {
    public static void main(String[] args) throws IOException {
        GameProcess gameProcess = new GameProcess();
        gameProcess.play();
    }
}