package cat.ironhack.game;

import cat.ironhack.battle.Battle;
import cat.ironhack.party.Party;
import cat.ironhack.utils.UtilsGame;
import cat.ironhack.utils.UtilsParty;

import java.io.IOException;
import java.util.Scanner;

import static cat.ironhack.utils.UtilsMenu.*;
import static cat.ironhack.utils.UtilsParty.*;

public class Game {
    private String text;
    private String options;
    private Battle battle;

    private String idCharacter1;
    private String idCharacter2;

    public Game() {
    }

    public String showMenu(String menu) throws IOException {
        if (battle !=null) refreshBattleTxt(battle);
        String option = "";
        setText(readMenu(menu));
        printMenu();
        setOptions(readOption(menu + "-options"));
        printOption();
        String key;
        switch (menu) {
            case "start-welcome":
                key = new Scanner(System.in).nextLine();
                while (!key.equals("Y")) {
                    printOption();
                    key = new Scanner(System.in).nextLine();
                }
                break;

            case "start-menu":
                key = new Scanner(System.in).nextLine();
                while (!key.equals("1") && !key.equals("2") && !key.equals("3") && !key.equals("EXIT") && !key.equals("BACK")) {
                    printOption();
                    key = new Scanner(System.in).nextLine();
                }
                option = key.equals("EXIT") ? "EXIT" : key.equals("BACK") ? "BACK" : menu + "-options" + key;
                break;

            case "battleRound":
                UtilsGame utilsGame = new UtilsGame();


                System.out.println("Choose a valid character from Party1: ");
                key = new Scanner(System.in).nextLine();
                //While the given key is invalid, it will ask again until it fit the size of the character array
                while (!utilsGame.validCharacter(battle.getParty1().getCharacters(), key) && !key.equals("EXIT") && !key.equals("BACK")) {
                    System.out.println("Choose a valid character from Party1: ");
                    key = new Scanner(System.in).nextLine();
                }
                idCharacter1 = key;
                if (!key.equals("BACK") && !key.equals("EXIT")){
                    System.out.println("Choose a valid character from Party2: ");
                    key = new Scanner(System.in).nextLine();
                    //While the given key is invalid, it will ask again until it fit the size of the character array
                    while (!utilsGame.validCharacter(battle.getParty2().getCharacters(), key) && !key.equals("EXIT") && !key.equals("BACK")) {
                        System.out.println("Choose a valid character from Party2: ");
                        key = new Scanner(System.in).nextLine();
                    }
                    if (!key.equals("BACK") && !key.equals("EXIT")){
                        idCharacter2 = key;
                        battle.battleRound(idCharacter1, idCharacter2);
                    }
                    //This is a string with the id of both characters chosen by the user
                }
                option = key.equals("EXIT") ? "EXIT" : key.equals("BACK") ? "BACK" : menu + "-options" + key;

                break;
        }
        return option;
    }

    public void showOptions(String option) throws IOException {
        Party party1 = null;
        Party party2 = null;
        setOptions(readOption(option));
        printOption();
        switch (option) {
            case "start-menu-options1":
                //manual
                party1 = generatePartyManual("Team 1");
                party2 = generatePartyManual("Team 2");
                battle = new Battle(party1, party2);
                break;

            case "start-menu-options2":
                //random
                party1 = generatePartyRandom();
                party2 = generatePartyRandom();
                battle = new Battle(party1, party2);
                break;

            case "start-menu-options3":
                //CSV
                String file1 = "src/resources/parties/party1.csv";
                String file2 = "src/resources/parties/party2.csv";
                party1 = generatePartyFromCSV(file1);
                party2 = generatePartyFromCSV(file2);
                battle = new Battle(party1, party2);
                break;
        }
    }

    private void startBattle() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    private void printMenu() {
        for (String s : getText().split("\n")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
    }

    private void printOption() {
        for (String s : getOptions().split("\n")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
    }

    public Battle getBattle() {
        return battle;
    }
}
