import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogger {
    private static final String FILE_PATH = "C:\\Users\\gugur\\Documents\\BankPro\\eventos.txt";

    public static void registrarEvento(String evento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(timestamp + " - " + evento);
            writer.newLine();
            System.out.println("Evento registrado: " + evento);
        } catch (IOException e) {
            System.err.println("Erro ao registrar evento: " + e.getMessage());
        }
    }
}
