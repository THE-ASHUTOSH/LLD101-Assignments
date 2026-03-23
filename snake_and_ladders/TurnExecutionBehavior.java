public interface TurnExecutionBehavior {
    boolean playTurn(Player player, GameBoard board, Die dice);
}