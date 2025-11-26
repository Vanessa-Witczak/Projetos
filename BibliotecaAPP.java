/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.biblioteca;

/**
 *
 * @author Usuario
 */
import biblioteca.dao.livroDAO;
import biblioteca.dao.usuarioDAO;
import biblioteca.dao.funcionarioDAO;
import biblioteca.dao.emprestimoDAO;

import biblioteca.model.Livro;
import biblioteca.model.Usuario;
import biblioteca.model.Funcionario;
import biblioteca.model.Emprestimo;

import java.util.List;
import java.util.Scanner;

public class BibliotecaAPP {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        livroDAO livroDAO = new livroDAO();
        usuarioDAO usuarioDAO = new usuarioDAO();
        funcionarioDAO funcionarioDAO = new funcionarioDAO();
        emprestimoDAO emprestimoDAO = new emprestimoDAO();

        int opcao = 0;

        while (opcao != 9) {

            System.out.println("\n-----------------------------------------");
            System.out.println("        MENU DO SISTEMA BIBLIOTECA");
            System.out.println("-----------------------------------------");
            System.out.println("1 - Listar Livros");
            System.out.println("2 - Pesquisar Livro por Título");
            System.out.println("3 - Registrar Empréstimo");
            System.out.println("4 - Registrar Devolução");
            System.out.println("5 - Listar Usuários");
            System.out.println("6 - Listar Funcionários");
            System.out.println("7 - Listar Empréstimos");
            System.out.println("9 - Sair");
            System.out.println("-----------------------------------------");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    List<Livro> livros = livroDAO.listar();
                    for (Livro l : livros) {
                        System.out.println(l.getId() + " - " + l.getTitulo() +
                                " | Qtd: " + l.getQuantidade());
                    }
                    break;

                case 2:
                    System.out.print("Digite o título: ");
                    String titulo = sc.nextLine();
                    List<Livro> encontrados = livroDAO.buscarPorTitulo(titulo);

                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        for (Livro l : encontrados) {
                            System.out.println(l.getId() + " - " + l.getTitulo());
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID do Livro: ");
                    int idLivro = sc.nextInt();
                    System.out.print("ID do Usuário: ");
                    int idUsuario = sc.nextInt();
                    sc.nextLine();

                    Emprestimo novo = new Emprestimo();
                    novo.setLivro(new Livro(idLivro));
                    //novo.setUsuario(new |Usuario(idUsuario));
                    novo.setStatus("Emprestado");

                    emprestimoDAO.inserir(novo);
                    break;

                case 4:
                    System.out.print("ID do Empréstimo: ");
                    int idEmp = sc.nextInt();
                    sc.nextLine();

                    emprestimoDAO.registrarDevolucao(idEmp);
                    break;

                case 5:
                    List<Usuario> usuarios = usuarioDAO.listarUsuarios();
                    for (Usuario u : usuarios) {
                        System.out.println(u.getId() + " - " + u.getNome());
                    }
                    break;

                case 6:
                    List<Funcionario> funcionarios = funcionarioDAO.listar();
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            System.out.println(
                                    "Nome: " + f.getNome() +
                                    " | Cargo: " + f.getCargo() +
                                    " | Login: " + f.getLogin()
                            );
                        }
                    }
                    break;

                case 7:
                    List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
                    if (emprestimos.isEmpty()) {
                        System.out.println("Nenhum empréstimo encontrado.");
                    } else {
                        for (Emprestimo e : emprestimos) {
                            System.out.println(
                                    "ID: " + e.getId() +
                                    " | Livro: " + e.getLivro().getId() +
                                    " | Usuário: " + e.getUsuario().getId() +
                                    " | Data: " + e.getDataEmprestimo() +
                                    " | Devolução: " + e.getDataDevolucao()
                            );
                        }
                    }
                    break;

                case 9:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}