package model.service;

import model.entities.Produto;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorCSV {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String SEPARADOR_CSV = ",";
    private String nomeArquivo;

    public GerenciadorCSV() {
    }

    public GerenciadorCSV(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void criarArquivo(String nomeArquivo){
        File arquivo = new File(nomeArquivo);
        if(!arquivo.exists()){
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Novo arquivo CSV criado: " + nomeArquivo);
                } else {
                    System.err.println("Falha ao criar o arquivo CSV: " + nomeArquivo);
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
            }
        }
    }

    public List<Produto> lerProdutos(String nomeArquivo) throws IOException {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){
            String linha = br.readLine();

            while (linha != null){
                String[] campos = linha.split(SEPARADOR_CSV);
                produtos.add(new Produto(campos[0], Double.parseDouble(campos[1]),
                        LocalDate.parse(campos[2],dtf)));
                linha = br.readLine();
            }
            return produtos;
        }
    }

    public void escreverProdutos(List<Produto> produtos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))){
            for (Produto produto : produtos){
                String linha = produto.getNome() + SEPARADOR_CSV + String.format("%.2f",produto.getValor()) +
                        SEPARADOR_CSV + dtf.format(produto.getData());
                bw.write(linha);
                bw.newLine();
            }

        }
    }
}
