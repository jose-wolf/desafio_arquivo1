package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produto implements Comparable<Produto>{
    private String nome;
    private Double valor;
    private LocalDate data;

    public Produto() {
    }

    public Produto(String nome, Double valor, LocalDate data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public int compareTo(Produto outro) {
        return valor.compareTo(outro.getValor());
    }
}
