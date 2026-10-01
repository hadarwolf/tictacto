/**
 * Represents a player in the game.
 * This interface defines the behavior that all player types must implement.
 */
public interface Player {
    /**
     * Executes a turn for the player.
     * The player makes a move on the specified board using their designated mark.
     *
     * @param board The game board on which the player will place their mark.
     * @param mark  The mark (X or O) that the player will use for this turn.
     */
    void playTurn(Board board, Mark mark);
}