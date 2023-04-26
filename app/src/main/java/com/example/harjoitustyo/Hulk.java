package com.example.harjoitustyo;

public class Hulk extends Superhero{

    public Hulk(String name, int picture) {
        super(name, "Hulk", 6, 4, 18, 18, 2, 0, picture, 0, 0);
    }

    public void trainHulkAttack() {
        trainAttack();
    }
}
