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
import probjetobiblioteca.Classes.Livro;

/**
 *
 * @author marcia
 */
public class LivroDAO {

    File file = new File("Livro.txt");

    public void adicionar(Livro livro) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(livro.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Livro> listar() {
        List<Livro> lstLivro = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                Livro livro = new Livro(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], Integer.parseInt(str[5]), Boolean.parseBoolean(str[6]));
                lstLivro.add(livro);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstLivro;
    }

    public List<Livro> listar(boolean disponivel) {
        List<Livro> lstLivro = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (Integer.parseInt(str[5]) > 0) {
                    Livro livro = new Livro(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], Integer.parseInt(str[5]), Boolean.parseBoolean(str[6]));
                    lstLivro.add(livro);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstLivro;
    }

    public List<Livro> listar(String titulo) {
        List<Livro> lstLivro = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (str[0].contains(titulo)) {
                    Livro livro = new Livro(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], Integer.parseInt(str[5]), Boolean.parseBoolean(str[6]));
                    lstLivro.add(livro);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstLivro;
    }

    public Livro getLivro(int isbn) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Livro livro = null;
            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (Integer.parseInt(str[3]) == isbn) {
                    livro = new Livro(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], Integer.parseInt(str[5]), Boolean.parseBoolean(str[6]));

                }
            }
            bufferedReader.close();
            fileReader.close();
            return livro;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void Excluir(Livro livro) {
        List<Livro> lstLivro = listar();
        lstLivro.remove(livro);
        file.delete();
        for (Livro lst : lstLivro) {
            adicionar(lst);
        }
    }

    public void Excluir(int i) {
        List<Livro> lstLivro = listar();
        lstLivro.remove(i);
        file.delete();
        for (Livro lst : lstLivro) {
            adicionar(lst);
        }
    }

    public void atualizaQtdExemplar(String isbn, boolean emprestimo) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strAlterar = null;
            String strAlterada = null;
            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (str[3].contains(isbn)) {
                    Livro livro = new Livro(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), str[4], Integer.parseInt(str[5]), Boolean.parseBoolean(str[6]));
                    strAlterar = livro.toString();
                    int qtdExemplar = livro.getQtdExemplar();
                    if (emprestimo == false) {
                        livro.setQtdExemplar(qtdExemplar + 1);
                        livro.setDisponivel(true);
                    } else if (emprestimo == true && qtdExemplar > 0) {
                        livro.setQtdExemplar(qtdExemplar - 1);
                        if (qtdExemplar == 1) {
                            livro.setDisponivel(false);
                        }
                    }
                    strAlterada = livro.toString();
                }
            }
            bufferedReader.close();
            fileReader.close();
            atualizaArquivo(file, strAlterar, strAlterada);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizaArquivo(File fileLivro, String linhaAlterar, String linhaAlterada) {
        File fileTemp = new File("temp.tmp");
        FileWriter fileWriter = null;
        Scanner scan = null;
        try {
            fileWriter = new FileWriter(fileTemp);
            scan = new Scanner(fileLivro);
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

        fileLivro.delete();
        fileTemp.renameTo(file);
    }
}
