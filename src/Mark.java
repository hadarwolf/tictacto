/**
 * Represents the possible marks that can be used in the game.
 * This enum defines three states: BLANK, X, and O.
 */
public enum Mark {
    /** Represents an empty position on the board. */
    BLANK,
    /** Represents the mark for player X. */
    X,
    /** Represents the mark for player O. */
    O;

    /**
     * Returns a string representation of the mark.
     *
     * @return A string corresponding to the mark:
     *         - "X" for player X,
     *         - "O" for player O,
     *         - null for BLANK.
     */
    public String toString() {
        switch (this) {
            case BLANK:
                return null;
            case X:
                return "X";
            case O:
                return "O";
            default:
                return null;
        }
    }
}