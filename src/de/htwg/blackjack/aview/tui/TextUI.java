package de.htwg.blackjack.aview.tui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class TextUI implements IObserver {
    private IBlackJackController controller;

    public TextUI(){
        this.controller = new BlackJackController();
        this.controller.addObserver(this);
        this.controller.createGame("Hans", 10000);
    }

    private static Logger logger = Logger.getLogger("de.htwg.blackjack.aview.tui");

    @Override
    public void update(Event e) {
        logger.info(this.controller.getTableInfos());
    }

    public void loop() {
        printCommands();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            processInput(s.next());
        }
        logger.info("Goodbye");
    }

    public void processInput(String word){
        switch (word.charAt(0)) {
            case 'd':
                controller.userDouble();
                break;
            case 'h':
                controller.userHit();
                break;
            case 's':
                controller.userStand();
                break;
            case 'b':
                controller.userBet(Integer.parseInt(word.substring(1)));
                break;
            default:
                logger.warning("Invalid command entered.");
                printCommands();
        }
    }

    private void printCommands() {
        logger.warning("Blackjack commands: b[AMOUNT]=bet amount, d=double, s=stand, h=hit");
    }
}
