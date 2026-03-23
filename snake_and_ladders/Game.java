import java.util.Queue;

public class Game {
    private GameBoard board;
    private Queue<Player> players;
    private Die dice;
    private TurnExecutionBehavior turnBehavior;

    public Game(GameBoard board, Queue<Player> players, Die dice, TurnExecutionBehavior turnBehavior) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.turnBehavior = turnBehavior;
    }

    public void beginGame() {
        boolean gameWon = false;

        while (!gameWon && !players.isEmpty()) {
            Player currentPlayer = players.poll();
            
            gameWon = turnBehavior.playTurn(currentPlayer, board, dice);
            
            if (gameWon) {
                System.out.println(currentPlayer.getName() + " wins the game!");
            } else {
                players.offer(currentPlayer);
            }
        }
    }
}