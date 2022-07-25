package cat.ironhack.party;

import cat.ironhack.character.Character;

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

    private void setId() {
        id = UUID.randomUUID();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public ArrayList<Character> getDeadCharacters(){
        ArrayList<Character> deadCharacters = new ArrayList<Character>();
        for (Character character : characters) {
            if (!character.isAlive()){
                deadCharacters.add(character);
            }
        }
        for (Character character : characters) {
            if (!character.isAlive()){
                deadCharacters.add(character);
            }
        }
        return deadCharacters;
    }
    public ArrayList<Character> getAliveCharacters(){
        ArrayList<Character> aliveCharacters = new ArrayList<Character>();
        for (Character character : characters) {
            if (character.isAlive()){
                aliveCharacters.add(character);
            }
        }
        for (Character character : characters) {
            if (character.isAlive()){
                aliveCharacters.add(character);
            }
        }
        return aliveCharacters;
    }

    public boolean isPartyDead() {
        //TODO
        return true;
    }
}
