/**
 * Represents a tournament of multiple game rounds between two players.
 * This class manages the execution of multiple games and keeps track of the results.
 */
public class Tournament {
    /** The first player in the tournament. */
    private final Player player1;
    /** The second player in the tournament. */
    private final Player player2;
    /** The number of rounds won by player1. */
    private int player1WinCount;
    /** The number of rounds won by player2. */
    private int player2WinCount;
    /** The number of rounds that ended in a draw. */
    private int drawCount;
    /** The renderer used to display the game board. */
    private final Renderer renderer;
    /** The total number of rounds to be played in the tournament. */
    private final int rounds;
    /** The message format used to display the final results of the tournament. */
    private static final String END_TOURNAMENT_MSG = """
            ######### Results #########
            Player 1, %s won: %d rounds
            Player 2, %s won: %d rounds
            Ties: %d""";

    /**
     * Constructs a new Tournament with specified parameters.
     *
     * @param rounds   The number of rounds to be played.
     * @param renderer The renderer to be used for displaying the game board.
     * @param player1  The first player in the tournament.
     * @param player2  The second player in the tournament.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Updates the win counts or draw count based on the result of a round.
     *
     * @param PlayerIndicator 1 for player1, 2 for player2.
     * @param mark            The mark of the winning player or BLANK for a draw.
     */
    private void addWinCount(int PlayerIndicator, Mark mark) {
        if (mark == Mark.BLANK) {
            drawCount++;
            return;
        }
        if ((PlayerIndicator == 1 && mark == Mark.X) || (PlayerIndicator == 2 && mark == Mark.O)) {
            player1WinCount++;
        } else {
            player2WinCount++;
        }
    }

    /**
     * Executes the tournament by playing the specified number of rounds.
     * Alternates which player goes first in each round and updates win counts.
     * Prints the final results of the tournament.
     *
     * @param size         The size of the game board.
     * @param winStreak    The number of marks in a row needed to win.
     * @param playerName1  The name of player1.
     * @param playerName2  The name of player2.
     */
    public void playTournament(int size, int winStreak,
                               String playerName1, String playerName2) {
        for (int i = 0; i < rounds; i++) {
            if (i % 2 == 0) {
                Game game = new Game(player1, player2, size, winStreak, renderer);
                addWinCount(1, game.run());
            } else {
                Game game = new Game(player2, player1, size, winStreak, renderer);
                addWinCount(2, game.run());
            }
        }
        System.out.printf((END_TOURNAMENT_MSG), playerName1, player1WinCount,
                playerName2, player2WinCount, drawCount);
    }

    /**
     * The main entry point of the program. Sets up and runs a tournament based on command-line arguments.
     *
     * @param args Command-line arguments:
     *             args[0] - Number of rounds
     *             args[1] - Board size
     *             args[2] - Win streak required
     *             args[3] - Renderer type
     *             args[4] - Player 1 type
     *             args[5] - Player 2 type
     */
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        Player player1 = playerFactory.buildPlayer(args[4]);
        Player player2 = playerFactory.buildPlayer(args[5]);
        Renderer renderer = rendererFactory.buildRenderer(args[3], size);
        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        tournament.playTournament(size, winStreak, args[4], args[5]);
    }
}