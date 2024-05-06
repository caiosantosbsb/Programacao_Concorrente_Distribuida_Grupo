import java.util.List;

public class Camareira implements Runnable { //ser executados como threads
    private final List<Quarto> quartos; //quartos que a camareira irá gerenciar
    
    //Metodo construtor camareira
    public Camareira(int id, List<Quarto> quartos) {
        this.quartos = quartos;
    }

    @Override //Sobrescreve o método run quando a thread é inciada
    public void run() {
        try {
            while (true) {
                Thread.sleep(10000); // Simula tempo de intervalo entre limpezas
                synchronized (quartos) { //garantir que as alterações nos estados dos quartos sejam feitas de forma segura
                    for (Quarto q : quartos) { //Percorre todos os quartos e limpa sempre que possível
                        if (q.isOcupado() && q.isLimpo() && q.isChaveNaRecepcao()) {
                            q.limpar();
                            System.out.println("Camareira limpou o quarto.");
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("-----Interrompido------");
        }
    }
}
