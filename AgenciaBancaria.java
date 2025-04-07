import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgenciaBancaria {
    private Map<Integer, ContaBancaria> bancoDeDadosContas;

    public AgenciaBancaria() {
        this.bancoDeDadosContas = DataPersistence.carregarDados();
    }

    public void salvarDados() {
        DataPersistence.salvarDados(bancoDeDadosContas);
    }

    public void abrirConta(Cliente cliente, int numeroConta, String tipoConta) {
        ContaBancaria novaConta = null;
        if (tipoConta.equalsIgnoreCase("corrente")) {
            novaConta = new ContaCorrente(cliente, numeroConta);
        } else if (tipoConta.equalsIgnoreCase("poupanca")) {
            novaConta = new ContaPoupanca(cliente, numeroConta);
        }

        if (novaConta != null) {
            bancoDeDadosContas.put(numeroConta, novaConta);
            DataPersistence.salvarDados(bancoDeDadosContas);
            String evento = String.format("Conta %s aberta com sucesso para %s | Número da Conta: %d",
                    tipoConta, cliente.getNome(), numeroConta);
            EventLogger.registrarEvento(evento);
            System.out.println(evento);
        } else {
            System.out.println("Erro ao abrir conta.");
        }
    }

    public ContaBancaria buscarConta(int numeroConta) {
        ContaBancaria conta = bancoDeDadosContas.get(numeroConta);
        if (conta != null) {
            String evento = String.format("Conta buscada com sucesso: Número %d", numeroConta);
            EventLogger.registrarEvento(evento);
            System.out.println(evento);
        } else {
            String evento = String.format("Erro: Conta número %d não encontrada.", numeroConta);
            EventLogger.registrarEvento(evento);
            System.out.println(evento);
        }
        return conta;
    }

    public Collection<ContaBancaria> getContas() {
        return bancoDeDadosContas.values();
    }

    public void exibirContas() {
        if (bancoDeDadosContas.isEmpty()) {
            String evento = "Nenhuma conta cadastrada.";
            EventLogger.registrarEvento(evento);
            System.out.println(evento);
        } else {
            StringBuilder evento = new StringBuilder("Exibindo todas as contas:\n");
            for (ContaBancaria conta : bancoDeDadosContas.values()) {
                evento.append("Conta: ").append(conta.getNumeroConta())
                        .append(" | Cliente: ").append(conta.getCliente().getNome()).append("\n");
                conta.exibirInformacoesConta();
            }
            EventLogger.registrarEvento(evento.toString());
            System.out.println(evento);
        }
    }
}
