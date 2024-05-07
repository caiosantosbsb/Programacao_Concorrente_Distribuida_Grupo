import java.util.ArrayList;
import java.util.List;

public class Recepcionista implements Runnable {
    private final int id;
    private final List<Quarto> quartos;
    private final List<Integer> filaEspera;
    
    public Recepcionista(int id, List<Quarto> quartos) {
        this.id = id;
        this.quartos = quartos;
        this.filaEspera = new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000); 
            synchronized (quartos) {
                boolean encontrouQuarto = false;
                for (Quarto q : quartos) {
                    if (!q.isOcupado() && q.isChaveNaRecepcao()) { //Apenas verifica os quartos não hospeda diretamente
                        System.out.println("🔑 Recepcionista " + id + " encontrou quarto vago: " + q.getNumero() + ", último hospede: " + q.getUltimoHospedeId());
                        encontrouQuarto = true;
                        break;
                    }
                }
                if (!encontrouQuarto) { //Apenas verifica não remove hospede
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
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle the interrupt
            System.out.println("-----Interrompido------");
        }
    }
}
