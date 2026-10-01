/**
 * Represents a game between two players on a board.
 * The game alternates turns between players and checks for win or draw conditions.
 */
public class Game {
    /** The default number of marks in a row needed to win. */
    public static final int DEFAULT_WIN_STREAK = 3;
    /** The player using the X mark. */
    private final Player playerX;
    /** The player using the O mark. */
    private final Player playerO;
    /** The game board where marks are placed. */
    private final Board board;
    /** The number of consecutive marks needed to win. */
    private final int winStreak;
    /** The renderer used to display the game board. */
    private final Renderer renderer;
    /** The player whose turn it currently is. */
    private Player curTurnPlayer;
    /** The mark of the current turn's player. */
    private Mark curTurnMark;
    /** The mark of the winner, if there is one. */
    private Mark winnerMark;

    /**
     * Constructs a Game with default board size and win streak.
     *
     * @param playerX  The player using the X mark.
     * @param playerO  The player using the O mark.
     * @param renderer The renderer for displaying the board.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.board = new Board();
        this.winStreak = DEFAULT_WIN_STREAK;
        this.curTurnPlayer = playerX;
        this.curTurnMark = Mark.X;
    }

    /**
     * Constructs a Game with specified board size and win streak.
     *
     * @param playerX   The player using the X mark.
     * @param playerO   The player using the O mark.
     * @param size      The size of the board.
     * @param winStreak The number of consecutive marks needed to win.
     * @param renderer  The renderer for displaying the board.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board(size);
        this.winStreak = winStreak;
        this.renderer = renderer;
        this.curTurnPlayer = playerX;
        this.curTurnMark = Mark.X;
    }

    /**
     * Returns the number of consecutive marks needed to win.
     *
     * @return The win streak length.
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Returns the size of the board.
     *
     * @return The size of one dimension of the board.
     */
    public int getBoardSize() {
        return board.getSize();
    }

    /**
     * Runs the game loop until a win or draw condition is met.
     *
     * @return The mark of the winning player, or Mark.BLANK for a draw.
     */
    public Mark run() {
        // Main game loop
        while (true) {
            // Render the board (optional, if you have a renderer)
            renderer.renderBoard(board);

            // Current player makes a move
            curTurnPlayer.playTurn(board, curTurnMark);

            // Check for a win condition
            if (checkWin(curTurnMark)) {
                winnerMark = curTurnMark;
                renderer.renderBoard(board); // Optional final rendering
                return winnerMark; // Return the mark of the winner
            }

            // Check for a draw condition (if applicable)
            if (isBoardFull()) {
                renderer.renderBoard(board); // Optional final rendering
                return Mark.BLANK; // Indicate a draw (or use a special Mark for draw)
            }

            // Switch turns
            switchTurns();
        }
    }

    /**
     * Checks if the specified mark has achieved a winning streak on the board.
     *
     * @param mark The mark to check for a winning streak.
     * @return True if the mark has achieved a winning streak, false otherwise.
     */
    private boolean checkWin(Mark mark) {
        int size = board.getSize();

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkLine(mark, i, 0, 0, 1) ||
                    checkLine(mark, 0, i, 1, 0)) {
                return true;
            }
        }

        // Check all diagonals parallel to the main diagonal (\)
        for (int i = 0; i < size; i++) {
            if (checkLine(mark, i, 0, 1, 1) ||
                    checkLine(mark, 0, i, 1, 1)) {
                return true;
            }
        }

        // Check all diagonals parallel to the anti-diagonal (/)
        for (int i = 0; i < size; i++) {
            if (checkLine(mark, i, size - 1, 1, -1)
                    || checkLine(mark, 0, size - 1 - i, 1, -1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if there is a line of consecutive marks starting from a given position and direction.
     *
     * @param mark         The mark to check for consecutive occurrences.
     * @param startRow     The starting row index.
     * @param startCol     The starting column index.
     * @param rowIncrement Row increment per step in line checking.
     * @param colIncrement Column increment per step in line checking.
     * @return True if there is a line of consecutive marks equal to winStreak length.
     */
    private boolean checkLine(Mark mark, int startRow, int startCol,
                              int rowIncrement, int colIncrement) {
        int count = 0;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;

            if (row < size && col < size && row >= 0 && col >= 0
                    && board.getMark(row, col) == mark) {
                count++;
                if (count == winStreak) {
                    return true;
                }
            } else {
                count = 0; // Reset count if a non-matching mark is found
            }
        }
        return false;
    }

    /**
     * Checks if the board is full with no empty spaces left.
     *
     * @return True if no empty spaces are left on the board; false otherwise.
     */
    private boolean isBoardFull() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true; // Placeholder return value
    }

    /**
     * Switches turns between players X and O.
     */
    private void switchTurns() {
        if (curTurnPlayer == playerX) {
            curTurnPlayer = playerO;
            curTurnMark = Mark.O;
        } else {
            curTurnPlayer = playerX;
            curTurnMark = Mark.X;
        }
    }
}