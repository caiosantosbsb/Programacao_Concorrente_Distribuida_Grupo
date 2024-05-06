import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HotelSimulation {
    public static void main(String[] args) {
        //Instanciar variaveis
        int numQuartos = 10;
        int numHospedes = 50;
        int numCamareiras = 10;
        int numRecepcionistas = 5;
        ExecutorService executor = null; 
        
        //Instanciar quartos
        //Quartos incialmente limpos, vazios e numerados
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 1; i <= numQuartos; i++) {
            quartos.add(new Quarto(i));
        }      
        //Criação e execução das threads
        try {
        executor = Executors.newFixedThreadPool(numHospedes + numCamareiras + numRecepcionistas);
        for (int i = 1; i <= numHospedes; i++) {
            executor.submit(new Hospede(i, quartos));        
        }
        for (int i = 1; i <= numCamareiras; i++) {
            executor.submit(new Camareira(i, quartos));
        }
        for (int i = 1; i <= numRecepcionistas; i++) {
            executor.submit(new Recepcionista(i, quartos));
        }
        
        // Desligar o executor para que não aceite novas tarefas
        executor.shutdown();
    
        // Aguardar finalização das threads ou encerramento após timeout
        executor.awaitTermination(20000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("-----Interrompido------");
        } finally {
            if (executor != null && !executor.isTerminated()) {
                // Tenta encerrar as tarefas ativas
                executor.shutdownNow();
            }
            System.out.println("-----Executor Finalizado------");
        }
    } 
}
