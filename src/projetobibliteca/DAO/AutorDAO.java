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
import probjetobiblioteca.Classes.Autor;

/**
 *
 * @author marcia
 */
public class AutorDAO {

    File file = new File("Autor.txt");

    public void Salvar(Autor autor) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(autor.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void alterar(Autor autorAlterar, Autor autorAlterado) {
        atualizaArquivo(autorAlterar.toString(), autorAlterado.toString());
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

    public List<Autor> listar() {
        List<Autor> lstAutor = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                Autor autor = new Autor(str[0], str[1], str[2], str[3]);
                lstAutor.add(autor);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstAutor;
    }

    public void Excluir(Autor autor) {
        List<Autor> lstAutor = listar();
        lstAutor.remove(autor);
        file.delete();
        for (Autor lst : lstAutor) {
            Salvar(lst);
        }
    }

    public void Excluir(int i) {
        List<Autor> lstAutor = listar();
        lstAutor.remove(i);
        file.delete();
        for (Autor lst : lstAutor) {
            Salvar(lst);
        }
    }
}
