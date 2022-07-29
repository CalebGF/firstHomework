package cat.ironhack;

import cat.ironhack.game.Game;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        //Show start menu, at this point, user can only press Y to continue
        game.showMenu("start-welcome");

        //User can select different options (create characters manually, randomly or imported from CSV; Also BACK and EXIT)
        String option = game.showMenu("start-menu");

        //In case press EXIT or BACK, enter loop and handle previous screen
        while (option.equals("EXIT") || option.equals("BACK")) {
            if (option.equals("EXIT")) System.exit(0);
            else {
                game.showMenu("start-welcome");
                option = game.showMenu("start-menu");
            }
        }
        //show menu-option for each type of character's creation
        game.showOptions(option);

        boolean battleIsOver = game.getBattle().isBattleOver();
        System.out.println("ssdsa"+ battleIsOver);
        //show fight results and user can choose EXIT or CONTINUE
        String optionRoundScreen = "";
        //Repeat showing battleRound and roundScreenMenu until battle is over
        while (optionRoundScreen.equals("EXIT") || !battleIsOver) {
            if (optionRoundScreen.equals("EXIT")) System.exit(0);

            //Load characters created
            //At this moment the user can choose the characters that will fight, BACK or EXIT
            String optionBattleRound = game.showMenu("battleRound");

            //In case press EXIT or BACK, enter loop and handle previous screen
            while (optionBattleRound.equals("EXIT") || optionBattleRound.equals("BACK")) {
                if (optionBattleRound.equals("EXIT")) System.exit(0);
                else {
                    option = game.showMenu("start-menu");
                    //In case press EXIT or BACK, enter loop and handle previous screen
                    while (option.equals("EXIT") || option.equals("BACK")) {
                        if (option.equals("EXIT")) System.exit(0);
                        else {
                            game.showMenu("start-welcome");
                            option = game.showMenu("start-menu");
                        }
                    }
                    game.showOptions(option);
                    optionBattleRound = game.showMenu("battleRound");
                }
            }

            optionRoundScreen = game.showMenu("roundScreen");// inside call to "roundScreen-options"


        }


    }
}
