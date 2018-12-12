import java.io.SequenceInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Sistema sistema = new Sistema();
        Stack<Sistema> stackUndo = new Stack<Sistema>();
        Stack<Sistema> stackRedo = new Stack<Sistema>();
        stackUndo.push(sistema);

        while(true) {

            System.out.println("Digite sua escolha: \n" +
                    "(0) - Sair\n" +
                    "(1) - Registrar novo empregado\n" +
                    "(2) - Listar Empregados\n" +
                    "(3) - Remover empregado\n" +
                    "(4) - Lançar cartão de ponto\n" +
                    "(5) - Lancar resultado de venda\n" +
                    "(6) - Lancar taxa de servico\n" +
                    "(7) - Editar empregado\n" +
                    "(8) - Undo\n" +
                    "(9) - Redo\n" +
                    "(10) - Rodar folha");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            Sistema novoSistema = new Sistema(sistema);
            switch (escolha) {
                case 0:
                    return;
                case 1:
                    sistema.registrarEmpregado();
                    stackUndo.push(novoSistema);
                    break;
                case 2:
                    sistema.listarEmpregados();
                    break;
                case 3:
                    sistema.removerEmpregado();
                    stackUndo.push(novoSistema);
                    break;
                case 4:
                    sistema.lancarCartaoDePonto();
                    stackUndo.push(novoSistema);
                    break;
                case 5:
                    sistema.lancarResultadoDeVenda();
                    stackUndo.push(novoSistema);
                    break;
                case 6:
                    sistema.lancarTaxaDeServico();
                    stackUndo.push(novoSistema);
                    break;
                case 7:
                    sistema.editarEmpregado();
                    stackUndo.push(novoSistema);
                    break;
                case 8:
                    if(!stackUndo.empty()) {
                        stackRedo.push(novoSistema);
                        sistema = stackUndo.pop();
                    }
                    break;
                case 9:
                    if(!stackRedo.empty()) {
                        stackUndo.push(novoSistema);
                        sistema = stackRedo.pop();
                    }
                    break;
                case 10:
                    sistema.rodarFolha();
                    stackUndo.push(novoSistema);
                    break;
            }

        }
    }
}
