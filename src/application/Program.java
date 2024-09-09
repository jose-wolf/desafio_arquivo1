package application;

import model.entities.Produto;
import model.service.GerenciadorCSV;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Program {

    public static void main(String[] args) throws IOException {

        Locale.setDefault(Locale.US);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Produto> produtos = new ArrayList<>();

        String nomeArquivo = "C:\\Teste\\produtos.csv";

        GerenciadorCSV gerenciador = new GerenciadorCSV(nomeArquivo);
        gerenciador.criarArquivo(nomeArquivo);

        Produto produto1 = new Produto("Produto A", 10.0, LocalDate.now());
        Produto produto2 = new Produto("Produto B",15.0, LocalDate.now());
        Produto produto3 = new Produto("Produto C", 20.50, LocalDate.parse("28/08/2024",dtf));
        Produto produto4 = new Produto("Produto D", 18.0, LocalDate.now());
        Produto produto5 = new Produto("Produto E", 1.58, LocalDate.now());

        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);
        produtos.add(produto4);
        produtos.add(produto5);
        gerenciador.escreverProdutos(produtos);

        List<Produto> produtosLidos = gerenciador.lerProdutos(nomeArquivo);

        Collections.sort(produtosLidos);
        for (Produto p : produtosLidos){
            System.out.println(p.getNome() + "," + p.getValor() +  "," + dtf.format(p.getData()));
        }

        Produto maiorValor = Collections.max(produtosLidos);
        Produto menorValor = Collections.min(produtosLidos);

        System.out.println("Maior valor é: " + maiorValor.getValor());
        System.out.println("Menor valor é: " + menorValor.getValor());

    }

}
