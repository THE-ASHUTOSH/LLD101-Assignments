package pen;

public class BottleRefillMethod implements RefillMethod {
    @Override
    public void refill() {
        System.out.println("Drawing ink from a bottle using a converter.");
    }
}