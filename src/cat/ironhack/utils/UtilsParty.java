package cat.ironhack.utils;

import cat.ironhack.character.Character;
import cat.ironhack.party.Party;
import cat.ironhack.character.Warrior;
import cat.ironhack.character.Wizard;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UtilsParty {
    /**
     *@param characters will store all the characters from the party, wizards and warriors
     *@param names Have some names to assign randomly to the random chars
     *@param randomNum will call the method from utilsRandom and generate a random number
     */

    static ArrayList<Character> characters = new ArrayList<Character>();
    static ArrayList<String> names = new ArrayList<String>(Arrays.asList("Adams", "Baker", "Clark", "Davis", "Evans", "Frank", "Ghosh"));
    private static UtilsRandom randomNum;

    /**
     * This method will generate random values given some limits
     * @return  A full party with random characters
     */
    public static Party generatePartyRandom() {
        //To generate the partySize randomly
        int partySize = randomNum.getRandomNum(2,15);
        for (int i = 0; i < partySize; i++) {
            String name = names.get(randomNum.getRandomNum(0, 5));
            int healthPoints = randomNum.getRandomNum(50, 100);
            int staminaOrMana = randomNum.getRandomNum(10,50);
            int strengthOrIntelligence = randomNum.getRandomNum(1,50);
            boolean isAlive = true;

            //Having all the random values now we can randomly decide if it's going to be a Warrior or Wizard
            if (randomNum.getRandomNum(0,1) == 0){
                characters.add(new Wizard(name, healthPoints, isAlive, staminaOrMana, strengthOrIntelligence));
            }else{
                characters.add(new Warrior(name, healthPoints, isAlive, staminaOrMana, strengthOrIntelligence));
            }
        }
        return new Party(characters);
    }

    public static Party generatePartyFromCSV(String csvfile) throws IOException {
        File file = new File(csvfile);
        Scanner scannedFile = new Scanner(file);
        Path path = Paths.get(csvfile);

        for (int i = 0; i < Files.lines(path).count()-1; i++) {
            if (i > 0) {
                String[] values = scannedFile.nextLine().split(","); //divides the current line i an Array of strings
                switch (values[0].replaceAll("\"", "")){
                    case "wizard":
                        characters.add(new Wizard(values[1],Integer.parseInt(values[2]),Boolean.parseBoolean(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5])));
                        break;
                    case "warrior":
                        characters.add(new Warrior(values[1],Integer.parseInt(values[2]),Boolean.parseBoolean(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5])));
                        break;
                    default:
                        //aqui un print? con un newError y/o generar un character random?
                }
            }
        }
        return new Party(characters);
    }

    public static Party generatePartyManual() {
        return null;
    }
}
