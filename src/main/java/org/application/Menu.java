package org.application;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URI_BASE = "https://v6.exchangerate-api.com/v6/b147c45971c45b829e4c659b/pair/";

    public void exibeMenu() {
        String menu = """
                1) Dólar -> Real
                2) Real -> Dólar
                3) Euro -> Real Brasileiro
                4) Real Brasileiro -> Euro
                5) Peso Argentino -> Real Brasileiro
                6) Real Brasileiro -> Peso Argentino
                * Para sair digite qualquer outro número *
                """;
        System.out.println(menu);

        String endereco;
        String json;
        Moedas converteJson;
        double taxaConversao;
        double resultado;

        System.out.println("Informe a operação que deseja realizar: ");
        var operacao = scanner.nextInt();
        System.out.println("Informe o valor que deseja converter: ");
        var valor = scanner.nextDouble();
        
        switch (operacao) {
            case 1:
                endereco = URI_BASE + "USD/BRL";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println("O resultado da conversão de " + converteJson.moeda() + " para " + converteJson.moedaConvertida() + " é de: " + resultado);
                break;
            case 2:
                endereco = URI_BASE + "BRL/USD";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println(resultado);
                break;
            case 3:
                endereco = URI_BASE + "EUR/BRL";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println(resultado);
                break;
            case 4:
                endereco = URI_BASE + "BRL/EUR";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println(resultado);
                break;
            case 5: 
                endereco = URI_BASE + "ARS/BRL";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println(resultado);
                break;  
            case 6:
                endereco = URI_BASE + "BRL/ARS";
                json = consumoApi.obterDados(endereco);
                converteJson = converteDados.obterDados(json, Moedas.class);
                taxaConversao = converteJson.conversao();
                resultado = valor * taxaConversao;
                System.out.println(resultado);
                break;
            default:
                System.out.println("Saindo");
                break;
        }
    }    
}
