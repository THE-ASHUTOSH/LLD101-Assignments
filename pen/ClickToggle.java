package pen;

public class ClickToggle implements ToggleBehavior {
    @Override
    public void open() {
        System.out.println("Clicking the top button to extend the nib.");
    }

    @Override
    public void deactivate() {
        System.out.println("Clicking the top button to retract the nib.");
    }
}