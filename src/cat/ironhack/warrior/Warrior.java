package cat.ironhack.warrior;

import cat.ironhack.character.Character;

public class Warrior extends Character {
    private int stamina;
    private int strength;

    public Warrior(String name, int hp, boolean isAlive, int stamina, int strength) {
        super(name, hp, isAlive);
        this.stamina = stamina;
        this.strength = strength;
    }
    public void attackEnemy(Character enemy){
        if (this.stamina>=5){
            enemy.setHp(enemy.getHp()-this.strength);
            this.stamina -=5;
        }else{
            double damage = this.strength/2;
            enemy.setHp((int) (enemy.getHp()-damage));
            this.stamina+=1;
        }
        if(enemy.getHp()<=0){
            enemy.setAlive(false);
        }
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
