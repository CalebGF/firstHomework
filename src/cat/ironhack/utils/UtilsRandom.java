package cat.ironhack.utils;

import cat.ironhack.character.Character;
import java.util.ArrayList;

public class UtilsRandom {
    /**
     * Method used to get a random number given a min and a max
     * @param min will get the minimal value
     * @param max will get the maximal value
     * @return a random number in the range given
     */
    public static int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static boolean repeatedName(String name, ArrayList<Character> characters) {
        for (Character character : characters) {
            if (character.getName() == name){ return true; }
        }
        return false;
    }
}
