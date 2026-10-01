/**
 * A factory class responsible for creating instances of different types of renderers.
 * Based on the input type, it returns a specific implementation of the Renderer interface.
 */
public class RendererFactory {

    /**
     * Constructs a new RendererFactory.
     * This constructor doesn't perform any specific initialization.
     */
    public RendererFactory() {
    }

    /**
     * Builds and returns a Renderer object based on the given renderer type.
     *
     * @param type The type of the renderer to create. Supported types are "void" and "console".
     * @param size The size of the board, used by some renderers (e.g., ConsoleRenderer).
     * @return A Renderer object corresponding to the specified type.
     * Returns null if the type is not recognized.
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type) {
            case "void":
                return new VoidRenderer();
            case "console":
                return new ConsoleRenderer(size);
            default:
                return null;
        }
    }
}