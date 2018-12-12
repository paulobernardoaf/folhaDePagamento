import java.util.Date;

public class CartaoDePonto {

    Date entrada;
    Date saida;

    public CartaoDePonto(Date entrada, Date saida) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
