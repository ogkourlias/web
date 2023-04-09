package models;

import java.util.ArrayList;

public class Animal {

    private String name;
    private String location;

    public void set(String name, String location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }
}