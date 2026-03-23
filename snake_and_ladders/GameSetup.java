import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameSetup {
    public static Game initializeGame(List<String> playerNames, List<Snake> snakes, 
                                  List<Ladder> ladders, String difficultyMode) {
        GameBoard board = new GameBoard(100, snakes, ladders);
        Die dice = new Die(6);
        
        Queue<Player> playerQueue = new LinkedList<>();
        for (String name : playerNames) {
            playerQueue.offer(new Player(name));
        }

        TurnExecutionBehavior strategy;
        if ("HARD".equalsIgnoreCase(difficultyMode)) {
            strategy = new StrictTurnExecution();
        } else {
            strategy = new SimpleTurnExecution();
        }

        return new Game(board, playerQueue, dice, strategy);
    }
}