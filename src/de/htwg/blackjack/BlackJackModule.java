package de.htwg.blackjack;

import com.google.inject.AbstractModule;
import de.htwg.blackjack.controller.IBlackJackController;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IBlackJackController.class).to(de.htwg.blackjack.controller.impl.TriBlackJackController.class);
    }
}
