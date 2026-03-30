package elevator;

public class LiftCabin {
    private final String cabinId;
    private final double maxPayload;
    
    private int currentLevel;
    private LiftStatus status;
    private double currentPayload;
    private boolean doorOpen;

    public LiftCabin(String cabinId, double maxPayload) {
        this.cabinId = cabinId;
        this.maxPayload = maxPayload;
        this.currentLevel = 0;
        this.status = LiftStatus.IDLE;
        this.currentPayload = 0.0;
        this.doorOpen = false;
    }

    public String getCabinId() { return cabinId; }
    public int getCurrentLevel() { return currentLevel; }
    public LiftStatus getStatus() { return status; }
    public void setStatus(LiftStatus status) { this.status = status; }

    public void embarkPassengers(double weight) {
        this.currentPayload += weight;
    }

    public void disembarkPassengers(double weight) {
        this.currentPayload = Math.max(0, this.currentPayload - weight);
    }

    private boolean exceedsCapacity() {
        return currentPayload > maxPayload;
    }

    private void handleOverload() {
        System.out.println("[Lift " + cabinId + "] OVERLOAD DETECTED! Max Payload bypassed. Sounding buzzer.");
        openDoors();
    }

    public void transitionToLevel(int targetLevel) {
        if (status == LiftStatus.OUT_OF_ORDER) return;
        
        if (exceedsCapacity()) {
            handleOverload();
            return;
        }

        closeDoors();
        this.status = (targetLevel > currentLevel) ? LiftStatus.MOVING_UP : LiftStatus.MOVING_DOWN;
        System.out.println("[Lift " + cabinId + "] Travelling " + status + " from Level " + currentLevel + " to " + targetLevel);
        
        // Simulating the arrival
        this.currentLevel = targetLevel;
        this.status = LiftStatus.IDLE;
        openDoors();
    }

    public void openDoors() {
        this.doorOpen = true;
        System.out.println("[Lift " + cabinId + "] Doors opening.");
    }

    public void closeDoors() {
        this.doorOpen = false;
        System.out.println("[Lift " + cabinId + "] Doors closed.");
    }

    public void initiateEmergencyHalt() {
        this.status = LiftStatus.IDLE;
        this.doorOpen = true;
        System.out.println("[Lift " + cabinId + "] EMERGENCY HALT ACTIVATED at Level " + currentLevel + "!");
    }
}
