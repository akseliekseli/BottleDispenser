package com.example.bottledispenser;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double price;
    private double size;
    public Bottle(){
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        price = 1.8;
        size = 0.5;

    }
    public Bottle(String nam, String manuf, Double totE, Double pri, Double siz){
        name = nam;
        manufacturer = manuf;
        total_energy = totE;
        price = pri;
        size = siz;
    }
    public String getName(){
        return name;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public double getEnergy(){
        return total_energy;
    }
    public double getPrice(){
        return price;
    }
    public double getSize(){
        return size;
    }
}
