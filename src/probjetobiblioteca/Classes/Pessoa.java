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
public class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Emprestimo> emprestimo;

    public Pessoa() {

    }

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the emprestimo
     */
    public List<Emprestimo> getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(List<Emprestimo> emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return this.getNome() + "|" + this.getCpf() + "|" + this.getEmail() + "|" + this.getTelefone() + "|";
    }

}
