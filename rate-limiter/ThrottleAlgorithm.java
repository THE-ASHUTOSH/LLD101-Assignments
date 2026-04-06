public interface ThrottleAlgorithm {
    boolean evaluate(String identifier, ThrottleRules rules);
}
