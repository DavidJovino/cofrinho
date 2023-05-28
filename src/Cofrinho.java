import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cofrinho {
    private List<Moeda> moedas;

    public Cofrinho() {
        moedas = new ArrayList<>();
    }

    public void adicionarMoeda(Moeda moeda) {
        moedas.add(moeda);
        System.out.println("Moeda adicionada com sucesso!");
    }

    public void removerMoeda(Moeda moeda) {
        if (moedas.remove(moeda)) {
            System.out.println("Moeda removida com sucesso!");
        } else {
            System.out.println("Moeda não encontrada no cofrinho.");
        }
    }

    public List<Moeda> listarMoedas() {
        return moedas;
    }

    public double calcularSaldoEmReal() {
        double saldoReal = 0;
        for (Moeda moeda : moedas) {
            double taxaDeCambio = obterTaxaDeCambio(moeda.getPais());
            double valorReal = moeda.getValor() * taxaDeCambio;
            saldoReal += valorReal;
        }
        return saldoReal;
    }

    private double obterTaxaDeCambio(String pais) {
        // Implemente aqui a lógica para obter a taxa de câmbio para cada país
        // Pode ser um banco de dados, API ou qualquer outra fonte de dados externa
        // Neste exemplo, faremos uma implementação simples com algumas taxas fixas

        double taxaPadrao = 1.0; // Taxa de câmbio para o país padrão (no caso, o Brasil)
        double taxaDolar = 5.50; // Taxa de câmbio para os Estados Unidos
        double taxaEuro = 6.50;  // Taxa de câmbio para a União Europeia

        if (pais.equalsIgnoreCase("EUA")) {
            return taxaDolar;
        } else if (pais.equalsIgnoreCase("UE")) {
            return taxaEuro;
        } else {
            return taxaPadrao;
        }
    }

    public static void main(String[] args) {
        Cofrinho cofrinho = new Cofrinho();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("========== Cofrinho de Moedas ==========");
                System.out.println("1 - Adicionar moeda");
                System.out.println("2 - Remover moeda");
                System.out.println("3 - Listar moedas");
                System.out.println("4 - Calcular saldo em Real");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor da moeda: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine(); // Limpar o buffer do scanner
                        System.out.print("Digite o país da moeda: ");
                        String pais = scanner.nextLine();
                        Moeda novaMoeda = new Moeda(valor, pais);
                        cofrinho.adicionarMoeda(novaMoeda);
                        break;
                    case 2:
                        System.out.print("Digite o valor da moeda a ser removida: ");
                        double valorRemover = scanner.nextDouble();
                        scanner.nextLine(); // Limpar o buffer do scanner
                        System.out.print("Digite o país da moeda a ser removida: ");
                        String paisRemover = scanner.nextLine();
                        Moeda moedaRemover = new Moeda(valorRemover, paisRemover);
                        cofrinho.removerMoeda(moedaRemover);
                        break;
                    case 3:
                        List<Moeda> moedas = cofrinho.listarMoedas();
                        System.out.println("Moedas no cofrinho:");
                        for (Moeda moeda : moedas) {
                            System.out.println(moeda.getValor() + " " + moeda.getPais());
                        }
                        break;
                    case 4:
                        double saldoReal = cofrinho.calcularSaldoEmReal();
                        System.out.println("Saldo em Real: R$ " + String.format("%.2f", saldoReal));
                        break;
                    case 0:
                        System.out.println("Saindo do programa...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida! Digite um número válido.");
                }

                System.out.println(); // Linha em branco para separar as operações
            }
        }
    }

    static class Moeda {
        private double valor;
        private String pais;

        public Moeda(double valor, String pais) {
            this.valor = valor;
            this.pais = pais;
        }

        public double getValor() {
            return valor;
        }

        public String getPais() {
            return pais;
        }
    }
}
