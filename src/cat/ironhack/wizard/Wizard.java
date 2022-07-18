package cat.ironhack.wizard;

import cat.ironhack.character.Character;

public class Wizard extends Character {
    private int mana;
    private int intelligence;

    public Wizard(String name, int hp, boolean isAlive, int mana, int intelligence) {
        super(name, hp, isAlive);
        this.mana = mana;
        this.intelligence = intelligence;
    }
    public void attackEnemy(Character enemy){
        if (this.mana>=5){
            enemy.setHp(enemy.getHp()-this.intelligence);
            this.mana -=5;
        }else{
            enemy.setHp((enemy.getHp()-2));
            this.mana+=1;
        }
        if(enemy.getHp()<=0){
            enemy.setAlive(false);
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
