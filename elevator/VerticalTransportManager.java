package elevator;

import java.util.ArrayList;
import java.util.List;

public class VerticalTransportManager {
    private final List<LiftCabin> activeCabins;
    private final LiftDispatchStrategy dispatcher;

    public VerticalTransportManager(LiftDispatchStrategy dispatcher) {
        this.activeCabins = new ArrayList<>();
        this.dispatcher = dispatcher;
    }

    public void registerCabin(LiftCabin cabin) {
        activeCabins.add(cabin);
    }

    public synchronized void processCallStationRequest(int level, TravelDirection direction) {
        System.out.println("\n<< Incoming Request >> Call from Level " + level + " heading " + direction);
        LiftCabin allocatedCabin = dispatcher.determineBestCabin(activeCabins, level, direction);

        if (allocatedCabin != null) {
            System.out.println("-> Dispatched Lift " + allocatedCabin.getCabinId() + " to Level " + level);
            allocatedCabin.transitionToLevel(level);
        } else {
            System.out.println("-> All lifts currently busy or unavailable. Please wait.");
        }
    }

    public synchronized void processCabinRequest(LiftCabin cabin, int targetLevel) {
        System.out.println("\n<< Cabin Input >> Passenger in Lift " + cabin.getCabinId() + " selected Level " + targetLevel);
        cabin.transitionToLevel(targetLevel);
    }

    public synchronized void engageBuildingEmergencyFireProtocol() {
        System.out.println("\n*** FIRE ALARM TRIGGERED! INITIATING SAFETY PROTOCOL ***");
        for (LiftCabin cabin : activeCabins) {
            cabin.initiateEmergencyHalt();
        }
    }

    public synchronized void resolvePowerFailure() {
        System.out.println("\n*** POWER GRID FAILURE! Utilizing backup generators to return cabins. ***");
        for (LiftCabin cabin : activeCabins) {
            if (cabin.getStatus() != LiftStatus.OUT_OF_ORDER) {
                cabin.transitionToLevel(0);
                cabin.openDoors();
                cabin.setStatus(LiftStatus.OUT_OF_ORDER);
            }
        }
    }
}
