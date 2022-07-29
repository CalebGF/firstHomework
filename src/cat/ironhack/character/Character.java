package cat.ironhack.character;

import cat.ironhack.attacker.Attacker;

import java.util.UUID;

public abstract class Character implements Attacker{
    private UUID id;
    private String name;
    private int hp;
    private boolean isAlive;

    public Character(String name, int hp, boolean isAlive) {
        this.name = name;
        this.hp = hp;
        this.isAlive = isAlive;
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
        if(this.hp==0){
            setAlive(false);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void attackEnemy(Character enemy) {

    }

    @Override
    public String toString() {
        return "Ch4r4ct3r" + "ID" + id + ", NAME" + name + '\'' + ", VITALITY" + hp + ",ALIVEORNOT " + isAlive;
    }
}
