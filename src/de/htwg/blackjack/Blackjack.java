package de.htwg.blackjack;

public class Blackjack {

    private interface Pers{
    }
    public static void main(String[] args){
        char f = 0xA0;
        char s = 0x1;
        char ass = (char) (0x1F000 + f + s);
        Pers a = new Pers() {};
        System.out.println("Hello, world and ass: \uD83C\uDCDD");
    }

    public static int testMe() {
        return 5;
    }
}

