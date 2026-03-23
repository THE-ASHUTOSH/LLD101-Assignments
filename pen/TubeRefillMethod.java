package pen;

public class TubeRefillMethod implements RefillMethod {
    @Override
    public void refill() {
        System.out.println("Replacing the inner ink tube.");
    }
}
