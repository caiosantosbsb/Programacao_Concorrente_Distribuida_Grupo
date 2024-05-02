import java.util.ArrayList;
import java.util.List;

public class HotelSimulation {
    public static void main(String[] args) {
        //Instanciar variaveis
        int numQuartos = 10;
        int numCamareiras = 10;
                
        //Instanciar quartos
        //Quartos incialmente limpos, vazios e numerados
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 1; i <= numQuartos; i++) {
            quartos.add(new Quarto(i));
        }

        // Simula a criação de uma camareira e sua atuação
        Camareira camareira = new Camareira(1, quartos);
        Thread threadCamareira = new Thread(camareira);
        threadCamareira.start();

        // Verificar se a camareira está funcionando após algum tempo
        try {
            Thread.sleep(10000);  // Aguardar um tempo para a camareira atuar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread principal interrompida.");
        }

        // Verificar o estado dos quartos
        for (Quarto quarto : quartos) {
            System.out.println("Quarto " + quarto.getNumero() + " está limpo? " + !quarto.isLimpo());
        }

        // Parar a camareira
        threadCamareira.interrupt();
        
        //Ver quartos criados
        //visualizarQuartos(quartos);
    } 
    /* Validar classe quarto
    private static void visualizarQuartos(List<Quarto> quartos) {
        for (Quarto quarto : quartos) {
            System.out.println("Quarto " + quarto.getNumero() +
                               ": Ocupado? " + !quarto.isOcupado() +   // Note que invertemos a lógica baseado na sua implementação original
                               ", Limpo? " + !quarto.isLimpo() +       // Idem
                               ", Chave na Recepção? " + quarto.isChaveNaRecepcao());
        }
    }*/
}
