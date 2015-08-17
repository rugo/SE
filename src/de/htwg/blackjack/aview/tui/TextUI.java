package de.htwg.blackjack.aview.tui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;

import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class TextUI implements IObserver {
    private IBlackJackController controller;
    private static final Logger LOG = Logger.getLogger("de.htwg.blackjack.aview.tui");

    @Inject
    public TextUI(IBlackJackController controller){
        this.controller = controller;
        this.controller.addObserver(this);
    }

    @Override
    public void update(Event e) {
        if (e != null) {
            LOG.warn(e.getMessage());
        } else {
            LOG.info(this.controller.getTableInfoString());
        }
    }

    public void loop() {
        printCommands();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            processInput(s.next());
        }
        LOG.info("Goodbye");
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
                LOG.warn("Invalid command entered.");
                printCommands();
        }
    }

    private void printCommands() {
        LOG.warn("Blackjack commands: b[AMOUNT]=bet amount, d=double, s=stand, h=hit");
    }
}
