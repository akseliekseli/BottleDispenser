package com.example.bottledispenser;

import java.util.ArrayList;
import java.util.Collections;

public class BottleDispenser {
    private double money;
    // The array for the Bottle-objects
    private final ArrayList<Bottle> bottle_array = new ArrayList<Bottle>();
    private ArrayList<Bottle> bottle_array_og=new ArrayList<Bottle>();

    private static BottleDispenser Bd = new BottleDispenser();
    String receipt="";

    private BottleDispenser() {
        money = 0;

        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 100.0, 1.8, 0.5));
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 100.0, 2.2, 1.5));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 100.0, 2.0, 0.5));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 100.0, 2.5, 1.5));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", 100.0, 1.95, 0.5));
        ///Täytyy kopioida manuaalisesti, jos vain laittaa yhtäsuureksi, niin toimii pointerina (Toisesta listasta poistaminen poistaa myös toisesta)
        for (int i = 0 ; i<bottle_array.size();i++){
            bottle_array_og.add(bottle_array.get(i)) ;
        }


    }

    public static BottleDispenser getInstance(){

        return Bd;
    }

    public void addMoney(int add_money) {
        money += add_money;
        System.out.println("Klink! Added more money!");
    }

    public String buyBottle(final int bottle_index){
        String result = null;
        String print_name = "";

        for (int i = 0; i < bottle_array.size(); i++) {
            System.out.println(bottle_array_og.size());
            if ((bottle_array.get(i).getName() == bottle_array_og.get(bottle_index).getName() ) & ( bottle_array.get(i).getSize() == bottle_array_og.get(bottle_index).getSize() )) {
                if (money > bottle_array.get(i).getPrice()) {
                    money = money - bottle_array.get(i).getPrice();
                    receipt = "Your purchase:\n"+bottle_array.get(i).getName()+"    "+bottle_array.get(i).getSize()+"\nPrice:\n"+bottle_array.get(i).getPrice()+"\n";
                    print_name = "KACHUNK! " + bottle_array.get(i).getName() + " came out of the dispenser!";
                    bottle_array.remove(i);
                    result = print_name;
                    break;
                } else {
                    result = "Add money first!";
                    break;
                }
            }
        }
        if (result == null) {
            result = "Bottle is out of stock.";
        }
        return result;
    }

    public double returnMoney() {
        double change = money;
        System.out.println(String.format("Klink klink. Money came out! You got %,.2f€ back",money));
        money = 0;
        return change;
    }



    public String printList() {
        String s="Bottles:\n";
        for (int i=0; i<bottle_array.size(); i++) {
            s = s.concat(i+1+". Name: "+bottle_array.get(i).getName()+"\n	Size: "+bottle_array.get(i).getSize()+"	Price: "+Math.round(bottle_array.get(i).getPrice()*100.0)/100.0+"\n");
        }
        return s;
    }
    public ArrayList<String> getBottles(){
        ArrayList<String> btl_list = new ArrayList<String>();
        for (int i=0; i<bottle_array.size(); i++) {
            btl_list.add(i+1+". Name: "+bottle_array.get(i).getName()+"\n	Size: "+bottle_array.get(i).getSize()+"	Price: "+Math.round(bottle_array.get(i).getPrice()*100.0)/100.0+"\n");
        }
        return btl_list;
    }
    public String getReceipt(){
        System.out.println(receipt);
        return receipt;
    }
}
