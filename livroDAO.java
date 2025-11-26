/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.dao;

/**
 *
 * @author Usuario
 */
import biblioteca.model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class livroDAO {

    
    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO Livros (Titulo, Autor, Editora, Genero, Ano, ISBN, Quantidade_Disponivel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getGenero());
            stmt.setInt(5, livro.getAno());
            stmt.setString(6, livro.getIsbn());
            stmt.setInt(7, livro.getQuantidade());
            stmt.executeUpdate();
            System.out.println("📚 Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

   
    public List<Livro> listar() {
        return listarLivros(); 
    }

    
    public List<Livro> listarLivros() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Livros";
        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("ID"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getString("Editora"),
                    rs.getString("Genero"),
                    rs.getInt("Ano"),
                    rs.getString("ISBN"),
                    rs.getInt("Quantidade_Disponivel")
                );
                lista.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
        return lista;
    }

    
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Livros WHERE Titulo LIKE ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("ID"),
                    rs.getString("Titulo"),
                    rs.getString("Autor"),
                    rs.getString("Editora"),
                    rs.getString("Genero"),
                    rs.getInt("Ano"),
                    rs.getString("ISBN"),
                    rs.getInt("Quantidade_Disponivel")
                );
                lista.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }

        return lista;
    }

    
    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livros SET Titulo=?, Autor=?, Editora=?, Genero=?, Ano=?, ISBN=?, Quantidade_Disponivel=? WHERE ID=?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getGenero());
            stmt.setInt(5, livro.getAno());
            stmt.setString(6, livro.getIsbn());
            stmt.setInt(7, livro.getQuantidade());
            stmt.setInt(8, livro.getId());
            stmt.executeUpdate();
            System.out.println("📖 Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    
    public void excluirLivro(int id) {
        String sql = "DELETE FROM Livros WHERE ID=?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("❌ Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }
}
