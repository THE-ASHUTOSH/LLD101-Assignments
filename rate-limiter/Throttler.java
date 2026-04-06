public class Throttler {
    private ThrottleAlgorithm algorithm;

    public Throttler(ThrottleAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void switchAlgorithm(ThrottleAlgorithm newAlgorithm) {
        this.algorithm = newAlgorithm;
    }

    public boolean canProceed(String identifier, ThrottleRules rules) {
        return algorithm.evaluate(identifier, rules);
    }
}
