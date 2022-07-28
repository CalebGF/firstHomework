package cat.ironhack.utils;

import java.util.ArrayList;
import cat.ironhack.character.Character;

public class UtilsGame {

    public boolean validCharacter(ArrayList<Character> characters, String key) {
        try {
        int index = Integer.parseInt(key);
            if (index >=1 && index <= characters.size()){return true;}
        }catch ( NumberFormatException e){
            return false;
        }
        return false;
    }
}
