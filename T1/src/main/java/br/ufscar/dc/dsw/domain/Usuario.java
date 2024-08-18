package br.ufscar.dc.dsw.domain;

public class Usuario {

    private Long id;
    private String email;
    private String nome;
    private String senha;
    private boolean isAdmin;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(String email, String nome, String senha, boolean isAdmin) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public Usuario(Long id, String email, String nome, String senha, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}