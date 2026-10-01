/**
 * Represents a renderer for displaying the game board.
 * This interface defines the method that all renderer implementations must provide.
 */
public interface Renderer {
    /**
     * Renders the specified game board.
     *
     * @param board The game board to be rendered.
     */
    void renderBoard(Board board);
}