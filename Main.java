import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;

public class  Main {
    public static void main(String[] args) {
        abrirArquivo("C:\\Users\\gugur\\Documents\\BankPro\\eventos.txt");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Admin admin = new Admin("Augusto", "Java");
                AgenciaBancaria agencia = new AgenciaBancaria();
                LoginSystem loginSystem = new LoginSystem(admin);
                new BancoInterface();
            }
        });
    }

    public static void abrirArquivo(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                java.awt.Desktop.getDesktop().open(file);
                System.out.println("Arquivo aberto: " + file.getAbsolutePath());
            } else {
                System.err.println("O arquivo não foi encontrado: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }
}
