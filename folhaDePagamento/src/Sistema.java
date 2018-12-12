import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Empregado> empregados = new ArrayList<Empregado>();
    private int quantidadeTotal = 0;

    public Sistema() {

    }

    public Sistema(Sistema original) {
        this.empregados = new ArrayList<>(original.getEmpregados());
        this.quantidadeTotal = new Integer(original.quantidadeTotal);
    }

    public void rodarFolha() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Digite o dia de pagamento: (ex: dd/MM/yyyy)");
        String diaDeEntrada = scanner.nextLine();

        Date dia;
        try {
            dia = ft.parse(diaDeEntrada);
        } catch(ParseException e) {
            System.out.println("Data de entrada inválida");
            return;
        }

        for (Empregado empregado :this.getEmpregados()) {

            if(empregado.getUltimoPagamento() == null || empregado.getUltimoPagamento().equals(dia)) {

                empregado.setUltimoPagamento(dia);

                if(empregado.getTipo() == 1) {
                    long total = 0;

                    for (CartaoDePonto cartao : empregado.getCartoesDePonto()) {
                        long atual = cartao.getSaida().getTime() - cartao.getEntrada().getTime();
                        atual = atual % 28800000;
                        atual = atual % 3600000;
                        total += atual * 1.5;

                    }

                    System.out.println("Empregado: " + empregado.getNome());
                    System.out.println("Salário: " + (empregado.getSalario() + total));


                } else if(empregado.getTipo() == 2) {

                    if(dia.getDate() == 30) {
                        System.out.println("Empregado: " + empregado.getNome());
                        System.out.println("Salário: " + empregado.getSalario());
                    }

                } else if(empregado.getTipo() == 3) {
                    double total = 0;
                    for (Venda venda : empregado.getVendas()) {

                        if(!venda.isContabilizada()) {
                            venda.setContabilizada(false);
                            total += venda.getValor();
                        }

                    }

                    System.out.println("Empregado: " + empregado.getNome());
                    System.out.println("Salário: " + (empregado.getSalario() + total));

                }

            }

        }

    }

    public void registrarEmpregado() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do empregado: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço do empregado: ");
        String endereco = scanner.nextLine();
        int tipo = 0;
        while(tipo < 1 || tipo > 3) {
            System.out.println("Escolha um tipo válido:");
            System.out.println("(1) - Horista\n" +
                    "(2) - Assalariado\n" +
                    "(3) - Comissionado");
            tipo = scanner.nextInt();
        }
        switch (tipo) {
            case 1:
                System.out.print("Digite o salário horário do empregado: ");
                break;
            case 2:
                System.out.print("Digite o salário mensal do empregado: ");
                break;
            case 3:
                System.out.println("Digite a comissão do empreagado: ");
                break;
        }
        double salario = scanner.nextDouble();

        System.out.println("Digite o método de pagamento do empregado: \n" +
                "(1) - Cheque pelos correios\n" +
                "(2) - Cheque em mãos\n" +
                "(3) - Depósito na conta bancária");

        int metodo = scanner.nextInt();

        int id  = this.getQuantidadeTotal();
        this.setQuantidadeTotal(id + 1);

        System.out.println("Deseja adicionar esse empregado ao sindicato? \n" +
                "(1) - Sim\n" +
                "(2) - Não");
        int sindicato = scanner.nextInt();
        scanner.nextLine();

        Empregado novoEmpregado = new Empregado(id, nome, endereco, tipo, salario, metodo);
        this.getEmpregados().add(novoEmpregado);

    }

    public void listarEmpregados() {

        System.out.println("Lista de empregados:");
        for (Empregado empregado : this.getEmpregados()) {

            System.out.println("\tId: " + empregado.getId() + "\tNome: " + empregado.getNome());

        }
        System.out.println();

    }

    public void removerEmpregado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do empregado que deseja remover: ");
        this.listarEmpregados();
        int id = scanner.nextInt();
        scanner.nextLine();

        Iterator<Empregado> iterator = this.getEmpregados().iterator();
        while (iterator.hasNext()) {
            Empregado empregado = iterator.next();
            if(empregado.getId() == id) {
                iterator.remove();
                System.out.println("Empregado removido.");
                return;
            }
        }

        System.out.println("Empregado não encontrado.");

    }

    public void editarEmpregado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do empregado que deseja remover: ");
        this.listarEmpregados();
        int id = scanner.nextInt();
        scanner.nextLine();

        Empregado emp = null;
        for(Empregado empregado : this.getEmpregados()) {
            if(empregado.getId() == id) {
                emp = empregado;
            }
        }

        if(emp != null) {
            emp.editar();
        } else {
            System.out.println("Empregado não encontrado.");
        }

    }

    public Empregado pegarEmpregado(int id) {
        for (Empregado emp : this.getEmpregados()) {
            if(emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void lancarCartaoDePonto() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("Digite o ID do empregado que deseja lancar o cartão de ponto: ");
        this.listarEmpregados();
        int id = scanner.nextInt();
        scanner.nextLine();
        Empregado empregado = this.pegarEmpregado(id);

        if(empregado == null) {
            System.out.println("Empregado não encontrado.");
        } else {

            System.out.println("Digite o dia da entrada: (ex: dd/MM/yyyy)");
            String diaDeEntrada = scanner.nextLine();
            System.out.println("Digite a hora de entrada: (ex: 16:23)");
            String horaDeEntrada = scanner.nextLine();
            System.out.println("Digite o dia da saida: (ex: dd/MM/yyyy)");
            String diaDeSaida = scanner.nextLine();
            System.out.println("Digite a hora de saida: (ex: 16:23)");
            String horaDeSaida = scanner.nextLine();

            Date entrada, saida;
            try {
                entrada = ft.parse(diaDeEntrada + " " + horaDeEntrada);
            } catch(ParseException e) {
                System.out.println("Data de entrada inválida");
                return;
            }
            try {
                saida = ft.parse(diaDeSaida + " " + horaDeSaida);
            } catch(ParseException e) {
                System.out.println("Data de saida inválida");
                return;
            }

            CartaoDePonto novoCartao = new CartaoDePonto(entrada, saida);

            System.out.println(entrada);
            System.out.println(saida);

            empregado.getCartoesDePonto().add(novoCartao);

        }

    }

    public void lancarTaxaDeServico() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do empregado que deseja lancar a taxa: ");
        this.listarEmpregados();
        int id = scanner.nextInt();
        scanner.nextLine();
        Empregado empregado =  this.pegarEmpregado(id);

        if(empregado == null) {
            System.out.println("Empregado não encontrado");
        } else {

            System.out.println("Digite a porcentagem da taxa: (sem o %)");
            int valor = scanner.nextInt();

            empregado.setTaxaDeServico(valor);

        }

    }

    public void lancarResultadoDeVenda() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do empregado que deseja adicionar a venda: ");
        this.listarEmpregados();
        int id = scanner.nextInt();
        scanner.nextLine();
        Empregado empregado =  this.pegarEmpregado(id);

        if(empregado == null) {
            System.out.println("Empregado não encontrado");
        } else {

            System.out.println("Digite o valor da venda: ");
            double valor = scanner.nextDouble();
            Venda venda = new Venda(valor);

            empregado.getVendas().add(venda);

        }


    }

    public ArrayList<Empregado> getEmpregados() {
        return empregados;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
