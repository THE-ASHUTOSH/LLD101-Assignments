package elevator;

public class CabinControlPanel {
    private final LiftCabin cabin;
    private final VerticalTransportManager manager;

    public CabinControlPanel(LiftCabin cabin, VerticalTransportManager manager) {
        this.cabin = cabin;
        this.manager = manager;
    }

    public void selectLevel(int level) {
        manager.processCabinRequest(cabin, level);
    }

    public void triggerDoorOpen() {
        cabin.openDoors();
    }

    public void triggerDoorClose() {
        cabin.closeDoors();
    }

    public void activatePanicButton() {
        manager.engageBuildingEmergencyFireProtocol();
    }
}
