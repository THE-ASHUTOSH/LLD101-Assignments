package pen;

public class Pen {
    private String inkColor;
    private boolean isReady;
    private WritingMethod writeStrategy;
    private RefillMethod refillStrategy;
    private ToggleBehavior openCloseStrategy;

    public Pen(String inkColor, WritingMethod writeStrategy, RefillMethod refillStrategy, ToggleBehavior openCloseStrategy) {
        this.inkColor = inkColor;
        this.writeStrategy = writeStrategy;
        this.refillStrategy = refillStrategy;
        this.openCloseStrategy = openCloseStrategy;
        this.isReady = false;
    }

    public void activate() {
        openCloseStrategy.open();
        this.isReady = true;
    }

    public void deactivate() {
        openCloseStrategy.deactivate();
        this.isReady = false;
    }

    public void write() throws Exception {
        if (!isReady) {
            throw new Exception("Cannot write! The pen is closed. Please activate() the pen first.");
        }
        System.out.print("[" + inkColor.toUpperCase() + "] ");
        writeStrategy.write();
    }

    public void refill(String newColor) {
        refillStrategy.refill();
        this.inkColor = newColor;
        System.out.println("Pen has been refilled with " + newColor + " ink.");
    }
}
