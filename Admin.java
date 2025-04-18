public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validarSenha(String password) {
        return this.password.equals(password);
    }

    public void cadastrarConta(AgenciaBancaria agencia, Cliente cliente, int numeroConta, String tipoConta) {
        agencia.abrirConta(cliente, numeroConta, tipoConta);
        String evento = String.format("Admin %s cadastrou uma nova conta: Número %d, Tipo %s para o cliente %s",
                username, numeroConta, tipoConta, cliente.getNome());
        EventLogger.registrarEvento(evento);
        System.out.println(evento);
    }
}
