/**
 * A factory class responsible for creating instances of different types of players.
 * Based on the input type, it returns a specific implementation of the Player interface.
 */
public class PlayerFactory {

    /**
     * Constructs a new PlayerFactory.
     * This constructor does not perform any specific initialization.
     */
    public PlayerFactory() {}

    /**
     * Builds and returns a Player object based on the given player type.
     *
     * @param type The type of the player to create. Supported types are:
     *             - "human": Creates a HumanPlayer.
     *             - "whatever": Creates a WhateverPlayer.
     *             - "clever": Creates a CleverPlayer.
     *             - "genius": Creates a GeniusPlayer.
     * @return A Player object corresponding to the specified type, or null if the type is not recognized.
     */
    public Player buildPlayer(String type) {
        switch (type) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "genius":
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}