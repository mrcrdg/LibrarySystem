/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetobibliteca.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import probjetobiblioteca.Classes.Pessoa;

/**
 *
 * @author marcia
 */
public class PessoaDAO {

    File file = new File("Pessoa.txt");

    public void Salvar(Pessoa pessoa) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(pessoa.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Pessoa> listar() {
        List<Pessoa> lstPessoa = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                Pessoa pessoa = new Pessoa(str[0], str[1], str[2], str[3]);
                lstPessoa.add(pessoa);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstPessoa;
    }

    public List<Pessoa> listar(String nome) {
        List<Pessoa> lstPessoa = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (str[0].contains(nome)) {
                    Pessoa pessoa = new Pessoa(str[0], str[1], str[2], str[3]);
                    lstPessoa.add(pessoa);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstPessoa;
    }

    public Pessoa getPessoa(String cpf) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (str[1].contains(cpf)) {
                    Pessoa pessoa = new Pessoa(str[0], str[1], str[2], str[3]);
                    return pessoa;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void Excluir(Pessoa pessoa) {
        List<Pessoa> lstPessoa = listar();
        lstPessoa.remove(pessoa);
        file.delete();
        for (Pessoa lst : lstPessoa) {
            Salvar(lst);
        }
    }

    public void Excluir(int i) {
        List<Pessoa> lstPessoa = listar();
        lstPessoa.remove(i);
        file.delete();
        for (Pessoa lst : lstPessoa) {
            Salvar(lst);
        }
    }
    
    public void alterar(Pessoa pessoaAlterar, Pessoa pessoaAlterada) {
        atualizaArquivo(pessoaAlterar.toString(), pessoaAlterada.toString());
    }

    private void atualizaArquivo(String linhaAlterar, String linhaAlterada) {
        File fileTemp = new File("temp.tmp");
        FileWriter fileWriter = null;
        Scanner scan = null;
        try {
            fileWriter = new FileWriter(fileTemp);
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                linha = linha.replace(linhaAlterar, linhaAlterada);
                try {
                    fileWriter.write(linha + System.getProperty("line.separator"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.close();
                scan.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        file.delete();
        fileTemp.renameTo(file);
    }

}
