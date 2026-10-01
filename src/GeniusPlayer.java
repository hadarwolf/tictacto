import java.util.Random;

/**
 * Represents a "genius" player in the game.
 * This player implements a simple strategy of placing marks sequentially on the board.
 */
public class GeniusPlayer implements Player {
    /** Stores the coordinates of the last move made by this player. */
    private static int[] lastCords;

    /**
     * Constructs a new GeniusPlayer.
     * Initializes the last coordinates to (0,0).
     */
    public GeniusPlayer() {
        lastCords = new int[2];
    }

    /**
     * Executes a turn for the GeniusPlayer.
     * The player attempts to place a mark sequentially on the board, starting from the last placed position.
     * If the end of a row is reached, it moves to the start of the next row.
     *
     * @param board The game board on which to place the mark.
     * @param mark The mark (X or O) to be placed on the board.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int row = lastCords[0];
        int col = lastCords[1];
        while (!board.putMark(mark, row, col)) {
            if (col == board.getSize() - 1) {
                row = (row + 1) %board.getSize();
                col = 0;
            } else {
                col++;
            }
        }
        lastCords[0] = row;
        lastCords[1] = col;
    }
}