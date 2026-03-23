package pen;

public class PenBuilderSetup {
    public static Pen buildPen(PenType type, String inkColor, PenMechanism mechanism) {
        WritingMethod writeStrategy;
        RefillMethod refillStrategy;
        ToggleBehavior openCloseStrategy;

        switch (type) {
            case BALLPOINT:
                writeStrategy = new BallpointWritingMethod();
                refillStrategy = new TubeRefillMethod();
                break;
            case GEL:
                writeStrategy = new GelWritingMethod();
                refillStrategy = new TubeRefillMethod();
                break;
            case INK:
                writeStrategy = new InkWritingMethod();
                refillStrategy = new BottleRefillMethod();
                break;
            default:
                throw new IllegalArgumentException("Unknown Pen Type");
        }

        switch (mechanism) {
            case CAP:
                openCloseStrategy = new CapToggle();
                break;
            case CLICK:
                openCloseStrategy = new ClickToggle();
                break;
            default:
                throw new IllegalArgumentException("Unknown Mechanism Type");
        }

        return new Pen(inkColor, writeStrategy, refillStrategy, openCloseStrategy);
    }
}
