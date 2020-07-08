/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probjetobiblioteca.Classes;

import java.util.List;

/**
 *
 * @author marcia
 */
public class Livro {

    private String titulo;
    private int anoPublicacao;
    private String editora;
    private int isbn;
    private String categoria;
    private int qtdExemplar;
    private boolean disponivel;
    private Autor autor;
    private List<Emprestimo> emprestimo;

    public Livro() {

    }

    public Livro(String titulo, int anoPublicacao, String editora, int isbn, String categoria, int qtdExemplar, boolean disponivel) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.isbn = isbn;
        this.categoria = categoria;
        this.qtdExemplar = qtdExemplar;
        this.disponivel = disponivel;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the anoPublicacao
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    /**
     * @param anoPublicacao the anoPublicacao to set
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * @return the editora
     */
    public String getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * @return the isbn
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the qtdExemplar
     */
    public int getQtdExemplar() {
        return qtdExemplar;
    }

    /**
     * @param qtdExemplar the qtdExemplar to set
     */
    public void setQtdExemplar(int qtdExemplar) {
        this.qtdExemplar = qtdExemplar;
    }

    /**
     * @return the disponivel
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the disponivel to set
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Emprestimo> getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(List<Emprestimo> emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return this.getTitulo() + "|" + this.getAnoPublicacao() + "|" + this.getEditora() + "|" + this.getIsbn() + "|" + this.getCategoria() + "|" + this.getQtdExemplar() + "|" + this.isDisponivel() + "|";
    }
}
