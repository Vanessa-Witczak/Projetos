/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.dao;

/**
 *
 * @author Usuario
 */

import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class emprestimoDAO {

   
    public void inserir(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (ID_Livro, ID_Usuario, DataEmprestimo, Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, emprestimo.getLivro().getId());
            stmt.setInt(2, emprestimo.getUsuario().getId());

            if (emprestimo.getDataEmprestimo() != null) {
                stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            } else {
                stmt.setDate(3, new java.sql.Date(new Date().getTime()));
            }

            stmt.setString(4, emprestimo.getStatus());

            stmt.executeUpdate();

          
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    emprestimo.setId(rs.getInt(1));
                }
            }

            System.out.println("Empréstimo inserido com sucesso! ID = " + emprestimo.getId());

        } catch (SQLException e) {
            System.out.println("Erro ao inserir empréstimo: " + e.getMessage());
        }
    }

 
    public void registrarDevolucao(int idEmprestimo) {
        String sql = "UPDATE emprestimos SET DataDevolucao = ?, Status = ? WHERE ID = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(new Date().getTime()));
            stmt.setString(2, "Devolvido");
            stmt.setInt(3, idEmprestimo);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Devolução registrada para ID " + idEmprestimo);
            } else {
                System.out.println("Nenhum empréstimo encontrado para este ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
        }
    }

   
    public List<Emprestimo> listarTodos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos ORDER BY ID DESC";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToEmprestimo(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }

        return lista;
    }

    
    public Emprestimo buscarPorId(int id) {
        String sql = "SELECT * FROM emprestimos WHERE ID = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEmprestimo(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimo: " + e.getMessage());
        }

        return null;
    }

    
    public void deletar(int id) {
        String sql = "DELETE FROM emprestimos WHERE ID = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0)
                System.out.println("Empréstimo removido ID " + id);
            else
                System.out.println("Nenhum empréstimo encontrado para remoção.");

        } catch (SQLException e) {
            System.out.println("Erro ao remover empréstimo: " + e.getMessage());
        }
    }

    
    private Emprestimo mapResultSetToEmprestimo(ResultSet rs) throws SQLException {

        Emprestimo e = new Emprestimo();

        e.setId(rs.getInt("ID"));

        Livro livro = new Livro();
        livro.setId(rs.getInt("ID_Livro"));
        e.setLivro(livro);

        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("ID_Usuario"));
        e.setUsuario(usuario);

        Date d1 = rs.getDate("DataEmprestimo");
        if (d1 != null) e.setDataEmprestimo(new Date(d1.getTime()));

        Date d2 = rs.getDate("DataDevolucao");
        if (d2 != null) e.setDataDevolucao(new Date(d2.getTime()));

        e.setStatus(rs.getString("Status"));

        return e;
    }
}
