import java.util.List;
import java.util.Random;

public class Hospede implements Runnable  { // Criação da classe e atributos
    private final int id;
    private final List<Quarto> quartos;
    private final Random random;
    private final int pessoas;
    
    public Hospede(int id, List<Quarto> quartos) { //Metodo construtor da classe
        this.id = id;
        this.quartos = quartos;
        this.random = new Random();
        this.pessoas = random.nextInt(8) + 1;
    }

    //Metodo get
    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(random.nextInt(5000)); // Simula tempo para chegar ao hotel
                
                int pessoasRestantes = pessoas; // Total de pessoas para acomodar              
                
                Quarto quarto = null;
                
                while (pessoasRestantes > 0) {
                    synchronized (quartos) {
                        for (Quarto q : quartos) {
                        // Tenta acomodar até 4 pessoas por vez
                        int pessoasParaAcomodar = Math.min(pessoasRestantes, 4);
                        if (q.ocupar(pessoasParaAcomodar)) {
                            quarto = q;
                            pessoasRestantes -= pessoasParaAcomodar; // Reduz o número de pessoas restantes
                            break;
                            }
                        }
                    }
                    if (quarto != null) {
                        System.out.println("🚶‍ Hóspede " + id + " ocupou 🛌 Quarto " + quarto.getNumero() + " Grupo Inicial " + pessoas + " - Pessoas restantes " + pessoasRestantes);
                        Thread.sleep(random.nextInt(5000)); // Simula tempo de estadia no quarto
                        // Simulação de passeio randomica
                        if (random.nextBoolean()) { // 50% de chance de passear 
                            quarto.passear();
                            System.out.println("🚶‍ Hóspede " + id + " saiu para passeio 🔑 chave na recepção  " + quarto.getNumero());                                                      
                        } else{
                        // Simulação Checkout do hotel    
                            quarto.desocupar();
                        }
                        if (quarto.isLimpo()) {
                            System.out.println("🛌 Quarto " + quarto.getNumero() + " não foi limpo!");
                        }
                    } else {
                        System.out.println("🚶‍♂️ Hóspede " + id + " não encontrou quartos disponíveis e saiu para passear.");
                        Thread.sleep(random.nextInt(10000)); // Simula passeio pela cidade
                    }
                }    
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hospede " + id + " foi interrompido e parado.");
        }
    }
}
