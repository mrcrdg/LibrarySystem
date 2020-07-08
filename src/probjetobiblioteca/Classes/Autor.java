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
public class Autor {

    private String primeiroNome;
    private String ultimoNome;
    private String cidade;
    private String pais;
    private List<Livro> livro;

    public Autor() {

    }

    public Autor(String primeiroNome, String ultimoNome, String cidade, String pais) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.cidade = cidade;
        this.pais = pais;
    }

    /**
     * @return the primeiroNome
     */
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    /**
     * @param primeiroNome the primeiroNome to set
     */
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    /**
     * @return the ultimoNome
     */
    public String getUltimoNome() {
        return ultimoNome;
    }

    /**
     * @param ultimoNome the ultimoNome to set
     */
    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the livro
     */
    public List<Livro> getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return this.getPrimeiroNome() + "|" + this.getUltimoNome() + "|" + this.getCidade() + "|" + this.getPais() + "|";
    }

}
