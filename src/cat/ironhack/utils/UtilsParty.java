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

    public static Party generatePartyManual(String team) {
        Party party = null;
        characters = new ArrayList<>();
        System.out.println("Generating "+team);
        Scanner scanner = new Scanner(System.in);
        int i =0;
        while (i<3) {
            System.out.println("Creating new character "+(i+1)+"/3");
            String wizardOrWarrior = null;
            //ask user to introduce 1 o 2  (wizard o warrior)
            System.out.println("Type '1' To create Wizard\nType '2' To create Warrior");
            wizardOrWarrior = scanner.nextLine();
            //Handling user did not introduce 1 or 2
            while(!(wizardOrWarrior.equals("1")||wizardOrWarrior.equals("2"))) {
                System.out.println("Type '1' To create Wizard\nType '2' To create Warrior");
                wizardOrWarrior = scanner.nextLine();
            }

            System.out.println("Introduce the name of the character");
            String name = scanner.nextLine();
            int hp;

            if(wizardOrWarrior.equals("1")) {
                //50-100
                System.out.println("Introduce character's hp between 50 and 100");
                hp = Integer.parseInt(scanner.nextLine());
                while(!(hp>=50 && hp<=100)) {
                    System.out.println("Introduce character's hp between 50 and 100");
                    hp = Integer.parseInt(scanner.nextLine());
                }

                System.out.println("Introduce wizard's mana between 10 and 50");
                //10-50
                int mana = Integer.parseInt(scanner.nextLine());
                while(!(mana>=10 && mana<=50)) {
                    System.out.println("Introduce wizard's mana between 10 and 50");
                    mana = Integer.parseInt(scanner.nextLine());
                }

                System.out.println("Introduce wizard's intelligence between 1 and 50");
                //1-50
                int intelligence = Integer.parseInt(scanner.nextLine());
                while(!(intelligence>=1 && intelligence<=50)) {
                    System.out.println("Introduce wizard's intelligence between 1 and 50");
                    intelligence = Integer.parseInt(scanner.nextLine());
                }

                characters.add(new Wizard(name,hp,true,mana,intelligence));


            } else {
                //100-200
                System.out.println("Introduce character's hp between 100 and 200");
                hp = Integer.parseInt(scanner.nextLine());
                while(!(hp>=100 && hp<=200)) {
                    System.out.println("Introduce character's hp between 100 and 200");
                    hp = Integer.parseInt(scanner.nextLine());
                }


                //10-50
                System.out.println("Introduce warrior's stamina between 10 and 50");
                int stamina = Integer.parseInt(scanner.nextLine());
                while(!(stamina>=10 && stamina<=50)) {
                    System.out.println("Introduce warrior's stamina between 10 and 50");
                    stamina = Integer.parseInt(scanner.nextLine());
                }

                System.out.println("Introduce warrior's strength between 1 and 10");
                //1-10
                int strength = Integer.parseInt(scanner.nextLine());
                while(!(strength>=1 && strength<=10)) {
                    System.out.println("Introduce warrior's strength between 1 and 10");
                    strength = Integer.parseInt(scanner.nextLine());
                }
                characters.add(new Warrior(name,hp,true,stamina,strength));
            }
            System.out.println("New character added");
            i++;
        }
        party = new Party(characters);
        return party;
    }
}
