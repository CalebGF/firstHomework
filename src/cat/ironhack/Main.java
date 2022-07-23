package cat.ironhack;

import cat.ironhack.game.Game;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.showMenu("start-welcome");
        String option = game.showMenu("start-menu");
        while (option.equals("EXIT") || option.equals("BACK")) {
            if (option.equals("EXIT")) System.exit(0);
            else {
                game.showMenu("start-welcome");
                option = game.showMenu("start-menu");
            }
        }
        game.showOptions(option);
        //TODO
    }
}
