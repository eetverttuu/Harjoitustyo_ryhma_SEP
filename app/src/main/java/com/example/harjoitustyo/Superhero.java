package com.example.harjoitustyo;

import java.io.Serializable;
import java.util.Random;

public class Superhero implements Serializable {

    public String name;
    protected String type;
    protected int attack;
    protected int defense;
    protected int health;
    protected int maxHealth;
    protected int id;

    protected int experience;


    private int picture;
    private int wins;
    private int losses;

    public Superhero(String name, String type, int attack, int defense, int health, int maxHealth, int id, int experience, int picture, int wins, int losses) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = id;
        this.experience=experience;
        this.picture= picture;
        this.wins = wins;
        this.losses = losses;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getId() {
        return id;
    }

    public void setSuperheroName (String name){
        this.name=name;
    }

    public int getExperience() {
        return experience;
    }

    public int getPicture() {return picture;}
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    //This method increases the attack of the superhero by 1.
    public void trainAttack() {
        this.attack += 1;
    }

    //This method increases the defense of the superhero by 1.
    public void trainDefense() {
        this.defense += 1;
    }

    //This method is used in battle.
    //If the defenders defense is higher than the attackers attack then only 1 point of health is deducted.
    //Otherwise damage taken is defense - attack.
    public void defend(int damage) {
        if (this.defense > damage) {
            this.health -= 1;
        } else {
            this.health -= damage;
        }
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    //This method is used to give the winner 1 experience.
    //This method also increases the superheros max health by 1.
    //It also gives either 1 attack or 1 defense point at random.
    public void giveExperience() {
        this.experience += 1;
        this.maxHealth += 1;
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 1) {
            trainAttack();
        } else {
            trainDefense();
        }
    }

    //Method used to give the winner 1 win.
    public void giveWin() {
        this.wins += 1;
    }

    //Method used to give the loser 1 loss.
    public void giveLoss() {
        this.losses += 1;
    }

    //This method restores the superheros health back to max.
    public void sendToHospital() {
        this.health = getMaxHealth();
    }
}
