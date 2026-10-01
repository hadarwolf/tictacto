import java.util.Random;

/**
 * Represents a "whatever" player in the game.
 * This player randomly places their mark on the board without any strategy.
 */
public class WhateverPlayer implements Player {

    /** A random number generator used to select positions on the board. */
    private final Random rand;

    /**
     * Constructs a new WhateverPlayer.
     * Initializes the random number generator.
     */
    public WhateverPlayer() {
        this.rand = new Random();
    }

    /**
     * Executes a turn for the WhateverPlayer.
     * The player randomly selects a position on the board to place their mark.
     *
     * @param board The game board on which to place the mark.
     * @param mark  The mark (X or O) to be placed on the board.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int size = board.getSize();
        int randRow = rand.nextInt(size);
        int randCol = rand.nextInt(size);

        // Keep trying random positions until a valid one is found
        while (!board.putMark(mark, randRow, randCol)) {
            randRow = rand.nextInt(size);
            randCol = rand.nextInt(size);
        }
    }
}