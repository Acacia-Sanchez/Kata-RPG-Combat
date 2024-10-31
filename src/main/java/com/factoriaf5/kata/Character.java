package org.factoriaf5.rpgcombat;

public class Character {

    private int health;
    private int level;
    private boolean alive;

    public Character(int health, int level, boolean alive) { // el constructor de la clase
        this.health = 1000;
        this.level = 1;
        this.alive = true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) { // lo del paréntesis es lo que "viene"
        this.health = health; // this. (es lo que tiene ahora mismo)
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /// iteration one /// iteration two

    public void damageHealth(Character theOther, int damage) {

        if (this != theOther && theOther.isAlive()) {
            if (theOther.getLevel() >= this.level + 5) {
                theOther.health -= damage * 0.5; // así el daño se reduce un 50%
            } else if (theOther.getLevel() <= this.level - 5) {
                theOther.health -= damage * 1.5; // así el daño se incrementa un 50%
            }
        }
        if (health <= 0) {
            health = 0;
            alive = false;
        }

        if (this.isAlive()) {
            if (this.getLevel() >= theOther.level + 5) {
                this.health -= damage * 0.5; // así el daño se reduce un 50%
            } else if (this.getLevel() <= theOther.level - 5) {
                this.health -= damage * 1.5; // así el daño se incrementa un 50%
            }
        }
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public void healHealth(int heal) {
        if (this.alive) {
            this.health += heal;
            if (this.health > 1000) {
                this.health = 1000;
            }
        }
    }
}

/*
 * Iteration Three:
 * 
 * Characters have an attack Max Range.
 * Melee fighters have a range of 2 meters.
 * Ranged fighters have a range of 20 meters.
 * Characters must be in range to deal damage to a target.
 */