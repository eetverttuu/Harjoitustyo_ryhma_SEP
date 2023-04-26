package com.example.harjoitustyo;

import android.content.Context;
import android.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//This class is used to store all superheroes.
public class Storage{
    private ArrayList<Superhero> superheroes = new ArrayList<>();
    private static Storage storage = null;
    private Context context;

    private Storage(){
    }

    //Method to make Storage a singleton.
    public static Storage getInstance(){
        if(storage==null){
            storage=new Storage();
        }
        return storage;
    }

    public ArrayList<Superhero> getSuperheroes(){
        return superheroes;
    }

    public int getSize() {
        return superheroes.size();
    }

    public Superhero getName(int id){return superheroes.get(id);}

    public Superhero getSuperheroById(int id) {
        return superheroes.get(id);
    }
    //This method is used to remove superheroes from the list.
    public void removeSuperhero(String name) {
        int i = 0;
        for (Superhero s : superheroes) {
            if(s.getName().equals(name)) {
                break;
            }
        i++;
        }
        superheroes.remove(i);
    }

    public void addSuperhero(Superhero superhero){
        superheroes.add(superhero);
    }
}

