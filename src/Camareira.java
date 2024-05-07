import java.util.List;

public class Camareira implements Runnable {
    private final List<Quarto> quartos; // Quartos que a camareira irá gerenciar

    // Método construtor da camareira
    public Camareira(List<Quarto> quartos) {
        this.quartos = quartos;
    }

    @Override // Sobrescreve o método run quando a thread é iniciada
    public void run() {
        try {
            Thread.sleep(10000); // Simula tempo de intervalo entre limpezas
            synchronized (quartos) { // Garantir que as alterações nos estados dos quartos sejam feitas de forma segura
                for (Quarto q : quartos) { // Percorre todos os quartos e limpa sempre que possível
                    if (!q.isOcupado() && !q.isLimpo() && q.isChaveNaRecepcao()) {
                        q.limpar();
                        System.out.println("Camareira limpou o quarto " + q.getNumero() + ".");
                    }
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("-----Interrompido------");
        }
    }
}
