package de.htwg.blackjack.model;


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
     * Removes player from the table.
     * @param player Player to remove.
     * @return True if player was not on the table.
     */
    boolean removePlayer(IPlayer player);

    /**
     * Makes the player hit... He doesn't want cards anymore.
     * @param player Player that wants to hit.
     */
    void hit(IPlayer player);

    /**
     * Makes the player stand... He wants a new card.
     * @param player The player that wants to stand.
     */
    void stand(IPlayer player);
}
