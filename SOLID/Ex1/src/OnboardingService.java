import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final OutputPrinter printer;

    public OnboardingService(StudentRepository db, OutputPrinter printer) {
        this.db = db;
        this.printer = printer;
    }

    // SRP-compliant: orchestrates parsing, validation, creation, and saving.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        InputParser parser = new InputParser();
        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        StudentValidator validator = new StudentValidator();
        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        printer.printSuccess(id, db.count(), rec);
    }
}
