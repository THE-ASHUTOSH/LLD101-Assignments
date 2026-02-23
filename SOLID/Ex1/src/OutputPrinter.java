import java.util.List;

public interface OutputPrinter {
    void printInput(String raw);
    void printErrors(List<String> errors);
    void printSuccess(String id, int totalStudents, StudentRecord record);
}
