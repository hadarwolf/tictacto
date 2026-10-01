import java.awt.*;

/**
 * A renderer that does not display the game board.
 * This implementation of the Renderer interface is used when no visual output is required.
 */
public class VoidRenderer implements Renderer {

    /**
     * Renders the specified game board.
     * This implementation does nothing, effectively providing a "null" rendering.
     *
     * @param board The game board to be rendered (ignored in this implementation).
     */
    @Override
    public void renderBoard(Board board) {
        // No rendering occurs in this implementation
    }
}