package cat.ironhack.utils;

import java.util.ArrayList;
import cat.ironhack.character.Character;

public class UtilsGame {

    public boolean validCharacter(ArrayList<Character> characters, int key) {
        if (key >=0 && key < characters.size()){return true;}
        return false;
    }
}
