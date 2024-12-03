package com.example.models.ENUM;

public enum Dni {

    CEDULA("CEDULA"), RUC ("RUC"),PASAPORTE("PASAPORTE");
    private String name;
    
    Dni(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }


}
