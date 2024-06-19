package org.application;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URI_BASE = "https://v6.exchangerate-api.com/v6/b147c45971c45b829e4c659b/pair/";

    public void exibeMenu() {
        String menuMoedas = """
                1) Dólar(USD) 
                2) Euro(EUR)
                3) Peso Argentino(ARS)
                * OBS: Cotação em relação ao valor do Real Brasileiro *
                """;
        String menu = """
                1) Dólar(USD) -> Real Brasileiro(BRL)
                2) Real Brasileiro(BRL) -> Dólar(USD)
                3) Euro(EUR) -> Real Brasileiro(BRL)
                4) Real Brasileiro(BRL) -> Euro(EUR)
                5) Peso Argentino(ARS) -> Real Brasileiro(BRL)
                6) Real Brasileiro(BRL) -> Peso Argentino(ARS)
                7) Verificar Cotação Atual
                * Para sair digite qualquer outro número *
                """;
        System.out.println(menu);

        System.out.println("Informe a operação que deseja realizar: ");
        var operacao = scanner.nextInt();
        
        if (operacao < 1 || operacao > 7) {
            System.out.println("Saindo...");
            return;
        } if (operacao == 7) {
                System.out.println("\n" + menuMoedas);
                System.out.println("Selecione a moeda para obter a cotação: ");
                int operacaoCotacao = scanner.nextInt();
            try {
                verificarCotacao(operacaoCotacao);
                return;
            } catch (Exception e) {
                System.out.println("Erro ao processar a informação: " + e.getMessage());
            }
        }

        
        System.out.println("Informe o valor que deseja converter: ");
        var valor = scanner.nextDouble();
        scanner.close();

        try {
            selecionarConversao(operacao, valor);
        } catch (Exception e) {
            System.out.println("Erro ao processar a informação: " + e.getMessage());
        };
    }    
    
    public void selecionarConversao(int operacao, double valor) {
        String[] moedas = {
            "USD/BRL",
            "BRL/USD",
            "EUR/BRL",
            "BRL/EUR",
            "ARS/BRL",
            "BRL/ARS"
        };
        String endereco = URI_BASE + moedas[operacao - 1];
        String json = consumoApi.obterDados(endereco);
        Moedas converteJson = converteDados.obterDados(json, Moedas.class);
        double taxaConversao = converteJson.conversao();
        double resultado = valor * taxaConversao;
        System.out.println("Atualmente, " + valor + " " + converteJson.moeda() + " equivalem a " + resultado + " " + converteJson.moedaConvertida());
    }

    public void verificarCotacao (int operacaoCotacao) {
        String[] moedas = {
            "USD/BRL",
            "EUR/BRL",
            "ARS/BRL"
        };
        String endereco = URI_BASE + moedas[operacaoCotacao - 1];
        String json = consumoApi.obterDados(endereco);
        Moedas converteJson = converteDados.obterDados(json, Moedas.class);
        double taxaConversao = converteJson.conversao();
        System.out.println("A cotação atual é de: " + converteJson.moeda() + ": " + taxaConversao); 
    }
}
