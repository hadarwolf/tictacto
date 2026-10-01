/**
 * Represents a human player in the game.
 * This player interacts with the game through console input to place their marks on the board.
 */
public class HumanPlayer implements Player {
    /** Placeholder for the player's mark in input messages. */
    public static final String MARK_PLACEHOLDER = "<markPlaceholder>";
    /** Template message requesting input from the player. */
    public static final String INPUT_REQUEST = "Player <markPlaceholder>, type coordinates: ";
    /** Message displayed when an invalid input is provided. */
    public static final String INVAL_INPUT_MSG = "Invalid mark position. Please choose a valid position: ";
    /** Message displayed when a chosen position is already occupied. */
    public static final String POSITION_OCCUP_MSG = "Mark position is already occupied." +
            " Please choose a valid position: ";
    /** Maximum valid integer input for row and column combined (two-digit). */
    public static final int MAX_VAL_INT_INP = 99;

    /**
     * Constructs a new HumanPlayer.
     * No specific initialization is required.
     */
    public HumanPlayer() {}

    /**
     * Executes a turn for the HumanPlayer.
     * Prompts the player to enter coordinates for placing their mark on the board.
     *
     * @param board The game board on which to place the mark.
     * @param mark  The mark (X or O) to be placed on the board.
     * @throws IllegalArgumentException if the provided mark is null.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        // Check if the mark is null and throw an exception if it is
        if (mark.toString() == null) {
            throw new IllegalArgumentException("Mark cannot be null");
        }

        // Proceed with the input request
        System.out.println(INPUT_REQUEST.replace(MARK_PLACEHOLDER, mark.toString()));
        boolean validInp = false;

        // Keep asking the user until valid input is provided
        while (!validInp) {
            // Read player input (a two-digit integer for row and column)
            int playerInp = KeyboardInput.readInt();
            int row = playerInp / 10;
            int col = playerInp % 10;
            // Check if input is within valid bounds
            if (playerInp > MAX_VAL_INT_INP || row >= board.getSize() || row < 0 ||
                    col >= board.getSize() || col < 0) {
                System.out.println(INVAL_INPUT_MSG);
                continue;
            }
            // Checks if the position is already occupied
            if (!board.putMark(mark, row, col)) {
                System.out.println(POSITION_OCCUP_MSG);
            } else {
                // Finish loop, found valid spot
                validInp = true;
            }
        }
    }
}