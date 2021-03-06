package de.htwg.blackjack.model;


import com.google.inject.ImplementedBy;
import de.htwg.blackjack.model.impl.Table;

import java.util.List;

@ImplementedBy(Table.class)
public interface ITable {
    /**
     * Returns the amount of decks used on this table.
     * @return Amount of decks used.
     */
    int getAmountOfDecks();

    /**
     * Adds a player to the table.
     * @param newPlayer Player to add.
     */
    void addPlayer(IPlayer newPlayer);

    /**
     * Adds a list of players to the table.
     * @param newPlayers Players to add.
     */
    void addPlayers(List<IPlayer> newPlayers);

    /**
     * Delivers the amount of players on the table.
     * @return Amount of players
     */
    int getAmountOfPlayers();

    /**
     * Removes player from the table.
     * @param player Player to remove.
     * @return True if player was not on the table.
     */
    boolean removePlayer(IPlayer player);

    /**
     * Gets bet sum of a player on the table.
     * @param player Player with bet
     * @return Bet sum of the player.
     */
    Integer getBet(IPlayer player);

    /**
     * Places a bet of a player on the table.
     * @param player Player to bet.
     * @param amount Amount og the bet.
     */
    void placeBet(IPlayer player, Integer amount);

    /**
     * Fetches a new card from the stack of decks.
     * @return A card from the stack.
     */
    ICard getNewCard();
}
