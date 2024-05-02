public class Quarto {
    private final int numero;
    private boolean ocupado;
    private boolean limpo;
    private boolean chaveNaRecepcao;
    
    //Metodo construtor de quarto
    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.limpo = true;
        this.chaveNaRecepcao = true;
    }
    
    //Metodos synchronized para garantir apenas uma thread por vez
    //Garantir consistencia
    public synchronized boolean ocupar() {
        if (!ocupado && chaveNaRecepcao) {
            ocupado = true;
            limpo = false;
            chaveNaRecepcao = false;
            return true;
        }
        return false;
    }
    
    //Metodos Gets and Setters
    public synchronized void desocupar() {
        ocupado = false;
        chaveNaRecepcao = true;
        System.out.println("🔑 Quarto " + numero + " desocupado. Chave devolvida à recepção.");
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
