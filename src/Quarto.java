public class Quarto { //Classe quarto
    private final int numero;
    private boolean ocupado;
    private boolean limpo;
    private boolean chaveNaRecepcao;
    private int ultimoHospedeId; 

    public Quarto(int numero) { // Metodo construtor de quarto
        this.numero = numero;
        this.ocupado = false;
        this.limpo = true;
        this.chaveNaRecepcao = true;
        this.ultimoHospedeId = -1;
    }
    
  
    public synchronized boolean ocupar(int hospedeId) { //Funcao para ocupar quarto
        if (!ocupado && chaveNaRecepcao) {
            ocupado = true;
            limpo = false;
            chaveNaRecepcao = false;
            ultimoHospedeId = hospedeId; //Id para verificação da recepcionista
            return true;
        }
        return false;
    }

    public synchronized void desocupar() {
        ocupado = false;
        chaveNaRecepcao = true;
        System.out.println("🔑 Quarto " + numero + " desocupado. Chave devolvida à recepção.");
    }

    public synchronized void passear() {
        System.out.println("🔑 Quarto " + numero + " disponível para limpeza. Hospede foi passear.");
    }    
    
    public synchronized void limpar() {
        limpo = true;
        System.out.println("🧹 Quarto " + numero + " limpo.");
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public boolean isLimpo() {
        return limpo;
    }

    public boolean isChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public int getUltimoHospedeId() {
        return ultimoHospedeId;
    }    
}
