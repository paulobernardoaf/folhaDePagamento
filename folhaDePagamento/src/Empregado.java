import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Empregado {

    private int id;
    private String nome;
    private String endereco;
    private int tipo;
    private double salario;
    private ArrayList<CartaoDePonto> cartoesDePonto = new ArrayList<CartaoDePonto>();
    private ArrayList<Venda> vendas = new ArrayList<Venda>();
    private int pertencenteAoSindicato = 0;
    private int taxaDeServico;
    private int metodoDePagamento;
    Date ultimoPagamento = null;

    public Empregado(int id, String nome, String endereco, int tipo, double salario, int metodoDePagamento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.salario = salario;
        this.metodoDePagamento = metodoDePagamento;
    }

    public void editar() {

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Escolha o que deseja editar: \n" +
                    "(1) - Nome\n" +
                    "(2) - Endereço\n" +
                    "(3) - Tipo\n" +
                    "(4) - Método de pagamento\n" +
                    "(5) - Participação do sindicato\n" +
                    "(0) - Voltar a página anterior");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    this.setNome(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Digite o novo endereço: ");
                    this.setEndereco(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Digite o novo tipo: ");
                    int tipo = 0;
                    while(tipo < 1 || tipo > 3) {
                        System.out.println("Escolha um tipo válido:");
                        System.out.println("(1) - Horista\n" +
                                "(2) - Assalariado\n" +
                                "(3) - Comissionado");
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                    }
                    this.setTipo(tipo);
                    break;
                case 4:
                    System.out.print("Digite o novo método de pagamento: ");
                    this.setMetodoDePagamento(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Escolha a participação no sindicato: \n" +
                            "(1) - Participa\n" +
                            "(2) - Não participa");
                    this.setPertencenteAoSindicato(scanner.nextInt());
                    break;
            }

        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getTaxaDeServico() {
        return taxaDeServico;
    }

    public void setTaxaDeServico(int taxaDeServico) {
        this.taxaDeServico = taxaDeServico;
    }

    public ArrayList<CartaoDePonto> getCartoesDePonto() {
        return cartoesDePonto;
    }

    public void setCartoesDePonto(ArrayList<CartaoDePonto> cartoesDePonto) {
        this.cartoesDePonto = cartoesDePonto;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public int getPertencenteAoSindicato() {
        return pertencenteAoSindicato;
    }

    public void setPertencenteAoSindicato(int pertencenteAoSindicato) {
        this.pertencenteAoSindicato = pertencenteAoSindicato;
    }

    public int getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(int metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public Date getUltimoPagamento() {
        return ultimoPagamento;
    }

    public void setUltimoPagamento(Date ultimoPagamento) {
        this.ultimoPagamento = ultimoPagamento;
    }
}
