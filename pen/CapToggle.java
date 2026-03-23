package pen;

public class CapToggle implements ToggleBehavior {
    @Override
    public void open() {
        System.out.println("Removing the cap.");
    }

    @Override
    public void deactivate() {
        System.out.println("Putting the cap back on.");
    }
}
