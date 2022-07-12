package cat.ironhack.party;

import java.util.ArrayList;
import java.util.UUID;

public class Party {
    private UUID id;
    private ArrayList<Character> characters;

    public Party(ArrayList<Character> characters) {
        setId();
        this.characters = characters;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        id = UUID.randomUUID();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
