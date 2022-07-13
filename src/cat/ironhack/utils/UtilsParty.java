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

    public ArrayList<Party> generatePartiesFromCSV(String csvfile) throws IOException {
        ArrayList<Character> characters = new ArrayList<Character>();
        ArrayList<Party> parties = new ArrayList<Party>();
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter your csv file name");
        String csvFileName= sc.next();
        parties.add(createPartyFromcsv(characters, csvFileName));
        characters.clear();
        System.out.print("Please enter the second csv file name");
        String csvFileName2= sc.next();
        parties.add(createPartyFromcsv(characters, csvFileName2));
        return parties;
    }

    private Party createPartyFromcsv(ArrayList<Character> characters, String csvfile) throws IOException {
        File file = new File("oscar_age_female.csv");
        Scanner scannedFile = new Scanner(file);
        Path path = Paths.get("oscar_age_female.csv");
        String[] csvContent;
        String currentLine;

        for (int i = 0; i < Files.lines(path).count(); i++) {
            currentLine = scannedFile.nextLine();
            if (i > 0) {
                csvContent = currentLine.split(", ");

                if (csvContent[6].replaceAll("^\"|\"$", "") == "wizard") {
                    characters.add(createWizard(csvContent));
                } else {
                    characters.add(createWarrior(csvContent));
                }
            }
        }
        return new Party(characters);
    }
    private Character createWizard(String[] csvContent){
        String name;
        int hp;
        boolean isAlive;
        int staminaMana;
        int strengthIntelligence;
        name = csvContent[1].replaceAll("^\"|\"$", "");
        hp = Integer.valueOf(csvContent[2].replaceAll("^\"|\"$", ""));
        isAlive = (1 == Integer.valueOf(csvContent[3].replaceAll("^\"|\"$", "")));
        staminaMana = Integer.valueOf(csvContent[4].replaceAll("^\"|\"$", ""));
        strengthIntelligence = Integer.valueOf(csvContent[5].replaceAll("^\"|\"$", ""));
        return new Wizard(name, hp, isAlive, staminaMana, strengthIntelligence);
    }
    private Character createWarrior(String[] csvContent){
        String name;
        int hp;
        boolean isAlive;
        int staminaMana;
        int strengthIntelligence;
        name = csvContent[1].replaceAll("^\"|\"$", "");
        hp = Integer.valueOf(csvContent[2].replaceAll("^\"|\"$", ""));
        isAlive = (1 == Integer.valueOf(csvContent[3].replaceAll("^\"|\"$", "")));
        staminaMana = Integer.valueOf(csvContent[4].replaceAll("^\"|\"$", ""));
        strengthIntelligence = Integer.valueOf(csvContent[5].replaceAll("^\"|\"$", ""));
        return new Warrior(name, hp, isAlive, staminaMana, strengthIntelligence);
    }
}
