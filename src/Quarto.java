public class Quarto {
    private final int numero;
    private boolean ocupado;
    private boolean limpo;
    private boolean chaveNaRecepcao;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.limpo = true;
        this.chaveNaRecepcao = true;
    }

    public synchronized boolean ocupar(int pessoas) {
        if (!ocupado && chaveNaRecepcao) {
            ocupado = true;
            limpo = false;
            chaveNaRecepcao = false;
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
        ocupado = true;
        chaveNaRecepcao = true;
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
        return !ocupado;
    }

    public boolean isLimpo() {
        return !limpo;
    }

    public boolean isChaveNaRecepcao() {
        return chaveNaRecepcao;
    }    
}
