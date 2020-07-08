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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import probjetobiblioteca.Classes.Emprestimo;

/**
 *
 * @author marcia
 */
public class EmprestimoDAO {

    File file = new File("Emprestimo.txt");

    public void emprestar(Emprestimo emprestimo) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(emprestimo.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
            LivroDAO dao = new LivroDAO();
            dao.atualizaQtdExemplar(Integer.toString(emprestimo.getLivro().getIsbn()), true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void devolver(int i, LocalDate dataDevolvido) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strAlterar = null;
            String strAlterada = null;
            Emprestimo emprestado = null;
            LivroDAO dao = new LivroDAO();
            PessoaDAO pDao = new PessoaDAO();
            int linhaLida = 0;
            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                if (linhaLida == i) {
                    emprestado = new Emprestimo(dao.getLivro(Integer.parseInt(str[0])), pDao.getPessoa(str[1]), LocalDate.parse(str[2]), LocalDate.parse(str[3]));
                    strAlterar = emprestado.toString();
                    emprestado.setDataDevolvido(dataDevolvido);
                    strAlterada = emprestado.toString();
                }
                linhaLida++;
            }
            bufferedReader.close();
            fileReader.close();
            dao.atualizaQtdExemplar(Integer.toString(emprestado.getLivro().getIsbn()), false);
            atualizaArquivo(file, strAlterar, strAlterada);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Emprestimo> getEmprestado() {
        List<Emprestimo> lstEmprestimo = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            LivroDAO dao = new LivroDAO();
            PessoaDAO pDao = new PessoaDAO();
            while (bufferedReader.ready()) {
                String str[] = bufferedReader.readLine().split("\\|");
                Emprestimo emprestado;
                if (str[4].contains("null")) {
                    emprestado = new Emprestimo(dao.getLivro(Integer.parseInt(str[0])), pDao.getPessoa(str[1]), LocalDate.parse(str[2]), LocalDate.parse(str[3]));
                } else {
                    emprestado = new Emprestimo(dao.getLivro(Integer.parseInt(str[0])), pDao.getPessoa(str[1]), LocalDate.parse(str[2]), LocalDate.parse(str[3]), LocalDate.parse(str[4]));
                }
                lstEmprestimo.add(emprestado);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lstEmprestimo;
    }

    private void atualizaArquivo(File fileEmprestimo, String linhaAlterar, String linhaAlterada) {
        File fileTemp = new File("temp.tmp");
        FileWriter fileWriter = null;
        Scanner scan = null;
        try {
            fileWriter = new FileWriter(fileTemp);
            scan = new Scanner(fileEmprestimo);
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
        fileEmprestimo.delete();
        fileTemp.renameTo(file);
    }

}
