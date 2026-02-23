public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        // Setup
        FakeEligibilityStore store = new FakeEligibilityStore();
        EligibilityEngine engine = new EligibilityEngine(store);
        ReportPrinter printer = new ReportPrinter();
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        // Run
        EligibilityEngineResult result = engine.evaluate(s);

        // Output & Save
        printer.print(s, result);
        store.save(s.rollNo, result.status);
    }
}
