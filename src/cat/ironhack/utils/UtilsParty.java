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

import java.io.IOException;

public class UtilsParty {
    public static Party generatePartyRandom() {
        return null;
    }

    public static Party generatePartyFromCSV(String csvfile) throws IOException {
        ArrayList<Character> characters = new ArrayList<Character>();
        File file = new File(csvfile);
        Scanner scannedFile = new Scanner(file);
        Path path = Paths.get(csvfile);

        for (int i = 0; i < Files.lines(path).count()-1; i++) {
            if (i > 0) {
                String[] values = scannedFile.nextLine().split(","); //divides the current line i an Array of strings
                switch (values[0].replaceAll("\"", "")) {
                    case "wizard" -> characters.add(new Wizard(values[1], Integer.parseInt(values[2]), Boolean.parseBoolean(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5])));
                    case "warrior" -> characters.add(new Warrior(values[1], Integer.parseInt(values[2]), Boolean.parseBoolean(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5])));
                    default -> {
                    }
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
