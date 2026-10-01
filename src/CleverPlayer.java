
import java.util.Random;

/**
 * Represents a "clever" player in the game.
 * This player uses a random strategy to place marks on the board, attempting to avoid getting stuck.
 */
public class CleverPlayer implements Player {
    /** A random number generator used for making decisions. */
    private final Random rand;

    /**
     * Constructs a new CleverPlayer.
     * Initializes the random number generator.
     */
    public CleverPlayer() {
        this.rand = new Random();
    }

    /**
     * Executes a turn for the CleverPlayer.
     * The player attempts to place a mark on the board, using a
     * combination of sequential and random strategies.
     *
     * @param board The game board on which to place the mark.
     * @param mark  The mark (X or O) to be placed on the board.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int size = board.getSize();
        int row = size/2;
        int col = size/2;
        boolean direction = rand.nextBoolean();

        // Attempt to place the mark until a valid position is found
        while (!board.putMark(mark, row, col)) {
            // Update row and col, keeping them within bounds by wrapping around
            if (direction) {
                row = (row + 1) % size;
            } else {
                col = (col + 1) % size;
            }

            // Sometimes choose a new random position to avoid getting stuck
            if (rand.nextInt(25) == 0) {  // gives chance to reset to a random spot
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            }
        }
    }

}