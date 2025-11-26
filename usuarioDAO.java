/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.dao;

/**
 *
 * @author Usuario
 */
import biblioteca.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO {

    
    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (Nome, Email, CPF, Telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getTelefone());

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }


    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Email"),
                        rs.getString("CPF"),
                        rs.getString("Telefone")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET Nome=?, Email=?, CPF=?, Telefone=? WHERE ID=?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getTelefone());
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    
    public void removerUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE ID=?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Usuário removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
