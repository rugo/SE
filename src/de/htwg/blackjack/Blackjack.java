package de.htwg.blackjack;

public class Blackjack {
    public static void main(String[] args){
        char f = 0xA0;
        char s = 0x1;
        char ass = (char) (0x1F000 + f + s);
        System.out.println("Hello, world and ass: " + ass);
    }

    public static int testMe() {
        return 5;
    }
}
