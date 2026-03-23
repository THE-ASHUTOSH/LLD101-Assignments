package pen;

public class Main {
    public static void main(String[] args) {
        Pen gelPen = PenBuilderSetup.buildPen(PenType.GEL, "Blue", PenMechanism.CLICK);
        Pen fountainPen = PenBuilderSetup.buildPen(PenType.INK, "Black", PenMechanism.CAP);

        try {
            gelPen.write(); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            gelPen.activate();
            gelPen.write();
            gelPen.deactivate();

            System.out.println("-------------------");

            fountainPen.activate();
            fountainPen.write();
            fountainPen.refill("Red");
            fountainPen.write();
            fountainPen.deactivate();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}