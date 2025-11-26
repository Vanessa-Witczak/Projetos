/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.model;

/**
 *
 * @author Usuario
 */
import java.util.Date;

public class Emprestimo {
    private int id;
    private Livro livro;       
    private Usuario usuario;  
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;

    public Emprestimo() {}

    public Emprestimo(int id, Livro livro, Usuario usuario, Date dataEmprestimo, Date dataDevolucao, String status) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, String status) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.status = status;
    }

    
    public Emprestimo(int idLivro, int idUsuario) {
        this.livro = new Livro(idLivro);
        this.usuario = new Usuario(idUsuario);
        this.dataEmprestimo = new Date(); 
        this.status = "Emprestado";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String livroInfo = (livro != null ? ("ID:" + livro.getId() + " Título:" + (livro.getTitulo() == null ? "" : livro.getTitulo())) : "null");
        String usuarioInfo = (usuario != null ? ("ID:" + usuario.getId() + " Nome:" + (usuario.getNome() == null ? "" : usuario.getNome())) : "null");
        return "Emprestimo{id=" + id + ", livro=" + livroInfo + ", usuario=" + usuarioInfo +
               ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + ", status=" + status + "}";
    }
}
