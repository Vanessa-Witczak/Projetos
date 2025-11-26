/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.dao;

/**
 *
 * @author Usuario
 */

import biblioteca.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class funcionarioDAO {

    private static List<Funcionario> funcionarios = new ArrayList<>();

    public void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarios;
    }

    public void atualizar(int index, Funcionario funcionarioAtualizado) {
        funcionarios.set(index, funcionarioAtualizado);
    }

    public void remover(int index) {
        funcionarios.remove(index);
    }
}
