package com.example.eggmanager;

public class Egg {

    String name;

    String species;

    String size;

    int days_to_hatch;

    public Egg(String name, String species, String size, int days_to_hatch){
        this.name = name;
        this.species = species;
        this.size = size;
        this.days_to_hatch = days_to_hatch;
    }

    public String getName(){
        return name;
    }

    public String getSpecies(){
        return species;
    }

    public String getSize(){
        return size;
    }

    public int getDaysToHatch(){
        return days_to_hatch;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public void setSize(String size){
        this.size = size;
    }

    public void setDaysToHatch(int days_to_hatch){
        this.days_to_hatch = days_to_hatch;
    }

    public String toString(){
        return "Name: " + name + ", Species: " + species + ", Size: " + size + ", Days to Hatch: " + days_to_hatch;
    }


}
