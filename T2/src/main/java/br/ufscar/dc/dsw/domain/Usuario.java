package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, length = 64, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private String role;

    public Usuario() {
    }

    public Usuario(Long id) {
        super.setId(id);
    }

    public Usuario(String email, String nome, String senha, String role) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(Long id, String email, String nome, String senha, String role) {
        super.setId(id);
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.role = role;
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

    public String role() {
        return role;
    }

    public void setAdmin(String role) {
        this.role = role;
    }
}