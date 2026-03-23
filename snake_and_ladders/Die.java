import java.util.Random;

public class Die {
    private int maxValue;
    private Random random;

    public Die(int maxValue) {
        this.maxValue = maxValue;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(maxValue) + 1;
    }
}