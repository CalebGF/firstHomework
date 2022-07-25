package cat.ironhack.battle;

import cat.ironhack.character.Character;
import cat.ironhack.party.Party;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Battle {
    private final ArrayList<Character> graveyard;
    private int round;
    private Party party1;
    private Party party2;

    public Battle(Party party1, Party party2) {
        this.party1 = party1;
        this.party2 = party2;
        graveyard = new ArrayList<>();
    }

    //String to show character winner
    public String battleRound(Character character1,Character character2) {
        //TODO
        return null;
    }

    public ArrayList<Character> getGraveyard() {
        return graveyard;
    }

    private void addCharacterToGraveyard(Character character) {
        this.graveyard.add(character);
    }

    public int getRound() {
        return round;
    }

    public void incRound() {
        this.round = this.round +1;
    }

    public Party getParty1() {
        return party1;
    }

    public void setParty1(Party party1) {
        this.party1 = party1;
    }

    public Party getParty2() {
        return party2;
    }

    public void setParty2(Party party2) {
        this.party2 = party2;
    }

    private boolean isBattleOver() {
        boolean isBattleOver = false;
        if(getWinner()!=null) isBattleOver = true;
        return isBattleOver;
    }

    //return null if there is no winner
    public Party getWinner() {
        if(this.party1.isPartyDead()){
            return this.party2;
        }else if(this.party2.isPartyDead()){
            return this.party1;
        }else{
            return null;
        }
    }
}
