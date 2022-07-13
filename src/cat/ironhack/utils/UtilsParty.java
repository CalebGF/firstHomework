package cat.ironhack.utils;

import cat.ironhack.character.Character;
import cat.ironhack.party.Party;
import cat.ironhack.warrior.Warrior;
import cat.ironhack.wizard.Wizard;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UtilsParty {

    public static Party generatePartyFromCSV(String csvfile) throws IOException {
        ArrayList<Character> characters = new ArrayList<Character>();
        File file = new File(csvfile);
        Scanner scannedFile = new Scanner(file);
        Path path = Paths.get(csvfile);
        String[] csvContent;
        String currentLine;

        for (int i = 0; i < Files.lines(path).count()-1; i++) {
            currentLine = scannedFile.nextLine();
            if (i > 0) {
                csvContent = currentLine.split(","); //divides the current line i an Array of strings
                String type = csvContent[0].replaceAll("\"", ""); //gets the character type value
                String wizard = "wizard";
                String warrior = "warrior";
                if (type.equals(wizard)) { //filters the Character type value to call apropriate constructor and add it to array
                    characters.add(createWizard(csvContent));
                } else if(type.equals(warrior)){
                    characters.add(createWarrior(csvContent));
                }else{
                    throw new Error("There is one or many character type not valid in your csv file.");
                }
            }
        }
        return new Party(characters);
    }
    private static Character createWizard(String[] csvContent){
        String name = csvContent[1].replaceAll("\"","");
        int hp = Integer.parseInt(csvContent[2].replaceAll("\"",""));
        boolean isAlive = (1 == Integer.parseInt(csvContent[3].replaceAll("\"","")));
        int Mana = Integer.parseInt(csvContent[4].replaceAll("\"",""));
        int Intelligence = Integer.parseInt(csvContent[5].replaceAll("\"",""));
        return new Wizard(name, hp, isAlive, Mana, Intelligence);
    }
    private static Character createWarrior(String[] csvContent){
        String name = csvContent[1].replaceAll("\"","");
        int hp = Integer.parseInt(csvContent[2].replaceAll("\"",""));
        boolean isAlive = (1 == Integer.parseInt(csvContent[3].replaceAll("\"","")));
        int stamina = Integer.parseInt(csvContent[4].replaceAll("\"",""));
        int strength = Integer.parseInt(csvContent[5].replaceAll("\"",""));
        return new Warrior(name, hp, isAlive, stamina, strength);
    }
}
