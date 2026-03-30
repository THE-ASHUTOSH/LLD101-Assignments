package elevator;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Vertical Transport System (Refactored LLD) ---");

        LiftDispatchStrategy strategy = new NearestCabinDispatcher();
        VerticalTransportManager manager = new VerticalTransportManager(strategy);

        // Standard Lift
        LiftCabin lift1 = new LiftCabin("L1", 750.0); 
        // Cargo/Freight Lift
        LiftCabin lift2 = new LiftCabin("Freight-1", 1500.0);
        // Faulty Lift
        LiftCabin lift3 = new LiftCabin("L3", 600.0); 

        manager.registerCabin(lift1);
        manager.registerCabin(lift2);
        manager.registerCabin(lift3);

        // Put L3 out of service
        lift3.setStatus(LiftStatus.OUT_OF_ORDER);

        // Install panels
        FloorCallStation lobbyStation = new FloorCallStation(0, manager);
        FloorCallStation level5Station = new FloorCallStation(5, manager);
        
        CabinControlPanel lift1Panel = new CabinControlPanel(lift1, manager);

        // Scenario 1: A user on Level 5 calls the lift down
        level5Station.callDown();

        // Scenario 2: Passenger in Lift 1 selects level 10 but overloads it
        lift1.embarkPassengers(800.0);
        lift1Panel.selectLevel(10);

        // Scenario 3: Passenger steps out, reducing weight
        lift1.disembarkPassengers(150.0);
        lift1Panel.selectLevel(10);

        // Scenario 4: Extreme emergency
        lift1Panel.activatePanicButton();
    }
}
