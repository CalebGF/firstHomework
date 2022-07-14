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
                characters.add(setCharacterStats(csvContent));
            }
        }
        return new Party(characters);
    }
    private static Character setCharacterStats(String[] csvContent){
        String wizard = "wizard";
        String warrior = "warrior";
        String name = csvContent[1].replaceAll("\"","");
        int hp = Integer.parseInt(csvContent[2].replaceAll("\"",""));
        boolean isAlive = (1 == Integer.parseInt(csvContent[3].replaceAll("\"","")));
        int manaStamina = Integer.parseInt(csvContent[4].replaceAll("\"",""));
        int intelligenceStrength = Integer.parseInt(csvContent[5].replaceAll("\"",""));
        if (csvContent[0].replaceAll("\"", "").equals(wizard)){
            return new Wizard(name, hp, isAlive, manaStamina, intelligenceStrength);
        }else if(csvContent[0].replaceAll("\"", "").equals(warrior)){
            return new Warrior(name, hp, isAlive, manaStamina, intelligenceStrength);
        }else{
            throw new Error("There is one or many character type not valid in your csv file.");
        }
    }

}
