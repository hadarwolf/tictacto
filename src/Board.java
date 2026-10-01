/**
 * Represents a game board for a two-player game.
 * The board is a square grid of Mark objects, initialized to a default size or a specified size.
 */
public class Board {
    /** The default size of the board if no size is specified. */
    private int DEFAULTSIZE = 4;
    /** A 2D array representing the board grid, storing Mark objects. */
    private final Mark[][] board;
    /** The width (and height) of the board. */
    private final int width;
    /** The number of occupied positions on the board. */
    private int capacity;

    /**
     * Constructs a Board with the default size.
     * Initializes all positions to Mark.BLANK.
     */
    public Board() {
        this.width = DEFAULTSIZE;
        this.board = new Mark[DEFAULTSIZE][DEFAULTSIZE];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                this.board[i][j] = Mark.BLANK;
            }
        }
        this.capacity = 0;
    }

    /**
     * Constructs a Board with a specified size.
     * Initializes all positions to Mark.BLANK.
     *
     * @param size The size of the board (width and height).
     */
    public Board(int size) {
        this.board = new Mark[size][size];
        this.width = size;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                this.board[i][j] = Mark.BLANK;
            }
        }
        this.capacity = 0;
    }

    /**
     * Returns the size of the board (width or height).
     *
     * @return The size of one dimension of the board.
     */
    public int getSize() {
        return this.board.length;
    }

    /**
     * Attempts to place a mark on the board at the specified position.
     *
     * @param mark The mark to place on the board.
     * @param row  The row index where the mark should be placed.
     * @param col  The column index where the mark should be placed.
     * @return True if the mark was successfully placed, false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (row >= width || col >= width || row < 0 || col < 0 || board[row][col] != Mark.BLANK) {
            return false;
        }
        board[row][col] = mark;
        this.capacity++;
        return true;
    }

    /**
     * Retrieves the mark at a specified position on the board.
     *
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @return The mark at the specified position, or Mark.BLANK if out of bounds.
     */
    public Mark getMark(int row, int col) {
        if (row >= width || col >= width || row < 0 || col < 0) {
            return Mark.BLANK;
        }
        return board[row][col];
    }
}