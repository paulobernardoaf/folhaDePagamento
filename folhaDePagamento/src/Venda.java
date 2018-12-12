public class Venda {

    private double valor;
    private boolean contabilizada;

    public Venda(double valor) {
        this.valor = valor;
        this.contabilizada = false;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isContabilizada() {
        return contabilizada;
    }

    public void setContabilizada(boolean contabilizada) {
        this.contabilizada = contabilizada;
    }



}
