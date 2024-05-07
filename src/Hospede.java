import java.util.List;
import java.util.Random;

public class Hospede implements Runnable { 
    private final int id;
    private final List<Quarto> quartos;
    private final Random random;
    private final int pessoas;
    private int tentativas = 0; //Desisitir após duas tentativas e deixar reclamação

    public Hospede(int id, List<Quarto> quartos) { 
        this.id = id;
        this.quartos = quartos;
        this.random = new Random();
        this.pessoas = random.nextInt(8) + 1; // Quanto maior o grupo mais problemático fica o código
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(5000)); 
            
            int pessoasRestantes = pessoas; 
            
            Quarto quarto = null;

            while (pessoasRestantes > 0) {
                synchronized (quartos) {
                    for (Quarto q : quartos) {
                        int pessoasParaAcomodar = Math.min(pessoasRestantes, 4);
                        if (q.ocupar(pessoasParaAcomodar)) {
                            quarto = q;
                            pessoasRestantes -= pessoasParaAcomodar; 
                            break;
                        }
                    }
                }
                if (quarto != null) {
                    System.out.println("🚶‍ Hóspede " + id + " ocupou 🛌 Quarto " + quarto.getNumero() + " Grupo Inicial " + pessoas + " - Pessoas restantes " + pessoasRestantes);
                    Thread.sleep(random.nextInt(5000)); 
                    
                    if (random.nextBoolean()) { 
                        quarto.passear();
                        System.out.println("🚶‍ Hóspede " + id + " saiu para passeio 🔑 chave na recepção " + quarto.getNumero());
                    } else {
                        quarto.desocupar();
                    }
                    if (quarto.isLimpo()) {
                        System.out.println("🛌 Quarto " + quarto.getNumero() + " não foi limpo!");
                    }
                    break; 
                } else {
                    tentativas++;
                    if (tentativas < 2) {
                        System.out.println("🚶‍♂️ Hóspede " + id + " não encontrou quartos disponíveis, tentará novamente.");
                        Thread.sleep(random.nextInt(5000));
                    } else {
                        System.out.println("🚶‍♂️ Hóspede " + id + " não encontrou quartos após 2 tentativas e deixou reclamação.");
                        break; 
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hospede " + id + " foi interrompido e parado.");
        }
    }
}
