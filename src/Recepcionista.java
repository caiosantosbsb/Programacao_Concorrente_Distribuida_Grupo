import java.util.ArrayList;
import java.util.List;

public class Recepcionista implements Runnable { // Criação da classe e atributos
    private final int id;
    private final List<Quarto> quartos;
    private final List<Integer> filaEspera;

    public Recepcionista(int id, List<Quarto> quartos) { //Metodo construtor da classe
        this.id = id;
        this.quartos = quartos;
        this.filaEspera = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000); // Simula tempo de intervalo entre verificações de quartos vagos
                synchronized (quartos) {
                    boolean encontrouQuarto = false;
                    for (Quarto q : quartos) {
                        if (q.isOcupado() && q.isChaveNaRecepcao()) {
                            System.out.println("🔑 Recepcionista " + id + " encontrou quarto vago: " + q.getNumero());
                            encontrouQuarto = true;
                            break;
                        }
                    }
                    if (!encontrouQuarto) {
                        filaEspera.add(id);
                        System.out.println("🙇‍♂️ Recepcionista " + id + " adicionado à fila de espera.");
                        if (filaEspera.size() >= 2) {
                            int reclamacao = filaEspera.get(0);
                            System.out.println("📢 Recepcionista " + reclamacao + " deixou uma reclamação e foi embora.");
                            filaEspera.remove(0);
                        }
                    } else {
                        filaEspera.remove(Integer.valueOf(id));
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hospede " + id + " foi interrompido e parado.");
        }
    }
}
