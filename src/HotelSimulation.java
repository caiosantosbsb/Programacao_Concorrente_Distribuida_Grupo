import java.util.ArrayList;
import java.util.List;

public class HotelSimulation {
    public static void main(String[] args) {
        //Instanciar variaveis
        int numQuartos = 10;
        
        //Instanciar quartos
        //Quartos incialmente limpos, vazios e numerados
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 1; i <= numQuartos; i++) {
            quartos.add(new Quarto(i));
        }

        //Ver quartos criados
        visualizarQuartos(quartos);
    }
    
    private static void visualizarQuartos(List<Quarto> quartos) {
        for (Quarto quarto : quartos) {
            System.out.println("Quarto " + quarto.getNumero() +
                               ": Ocupado? " + !quarto.isOcupado() +   // Note que invertemos a lógica baseado na sua implementação original
                               ", Limpo? " + !quarto.isLimpo() +       // Idem
                               ", Chave na Recepção? " + quarto.isChaveNaRecepcao());
        }
    }
}
