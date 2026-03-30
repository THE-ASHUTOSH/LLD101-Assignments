package elevator;

public class FloorCallStation {
    private final int level;
    private final VerticalTransportManager manager;

    public FloorCallStation(int level, VerticalTransportManager manager) {
        this.level = level;
        this.manager = manager;
    }

    public void callUp() {
        manager.processCallStationRequest(level, TravelDirection.ASCENDING);
    }

    public void callDown() {
        manager.processCallStationRequest(level, TravelDirection.DESCENDING);
    }
}
