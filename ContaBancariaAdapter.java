import com.google.gson.*;

import java.lang.reflect.Type;

public class ContaBancariaAdapter implements JsonDeserializer<ContaBancaria>, JsonSerializer<ContaBancaria> {

    @Override
    public ContaBancaria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement tipoContaElement = jsonObject.get("tipoConta");

        if (tipoContaElement == null) {
            System.err.println("Campo 'tipoConta' ausente no JSON. Assumindo conta corrente como padrão.");
            return context.deserialize(json, ContaCorrente.class);
        }

        String tipoConta = tipoContaElement.getAsString();

        switch (tipoConta.toLowerCase()) {
            case "corrente":
                return context.deserialize(json, ContaCorrente.class);
            case "poupanca":
                return context.deserialize(json, ContaPoupanca.class);
            default:
                throw new JsonParseException("Tipo de conta desconhecido: " + tipoConta);
        }
    }

    @Override
    public JsonElement serialize(ContaBancaria src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();

        if (src instanceof ContaCorrente) {
            jsonObject.addProperty("tipoConta", "corrente");
        } else if (src instanceof ContaPoupanca) {
            jsonObject.addProperty("tipoConta", "poupanca");
        }

        return jsonObject;
    }
}
