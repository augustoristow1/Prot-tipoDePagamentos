import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DataPersistence {
    private static final String FILE_PATH = "dados.json";

    private static Gson criarGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ContaBancaria.class, new ContaBancariaAdapter())
                .create();
    }

    public static void salvarDados(Map<Integer, ContaBancaria> contas) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = criarGson();
            gson.toJson(contas, writer);
            System.out.println("Dados salvos com sucesso em " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Map<Integer, ContaBancaria> carregarDados() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = criarGson();
            Type tipo = new TypeToken<HashMap<Integer, ContaBancaria>>() {}.getType();
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            System.out.println("Nenhum dado encontrado. Criando novo banco de dados.");
            return new HashMap<>();
        }
    }
}
