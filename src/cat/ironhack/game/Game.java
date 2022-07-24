package cat.ironhack.game;

import cat.ironhack.battle.Battle;
import cat.ironhack.party.Party;

import java.io.IOException;
import java.util.Scanner;

import static cat.ironhack.utils.UtilsMenu.readMenu;
import static cat.ironhack.utils.UtilsMenu.readOption;
import static cat.ironhack.utils.UtilsParty.*;

public class Game {
    private String text;
    private String options;
    private Battle battle;
    public Game() {
    }

    public String showMenu(String menu) throws IOException {
        String option = "";
        setText(readMenu(menu));
        printMenu();
        setOptions(readOption(menu+"-options"));
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
                while (!key.equals("1") && !key.equals("2") && !key.equals("3")&& !key.equals("EXIT") &&!key.equals("BACK")) {
                    printOption();
                    key = new Scanner(System.in).nextLine();
                }
                option = key.equals("EXIT") ? "EXIT" : key.equals("BACK") ? "BACK" : menu+"-options"+key;
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
                party1 = generatePartyManual();
                party2 = generatePartyManual();
                battle = new Battle(party1, party2);
                break;

            case "start-menu-options2":
                //random
                party1 = generatePartyRandom();
                party2 = generatePartyRandom();
                battle = new Battle(party1, party2);
                break;

            case "start-menu-options3":
                //TODO
                //CSV
                String file1 = "src/resources/parties/party1.csv";
                String file2 = "src/resources/parties/party2.csv";
                party1 = generatePartyFromCSV(file1);
                party2 = generatePartyFromCSV(file2);
                battle = new Battle(party1, party2);
                break;
        }


    }

    private void startBattle(){
        //TODO
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
        for (String s: getText().split("\n")){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
    }

    private void printOption() {
        for (String s: getOptions().split("\n")){
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
