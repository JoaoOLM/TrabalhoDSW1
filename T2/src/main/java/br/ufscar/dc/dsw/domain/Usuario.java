package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
    private String password;

    @NotNull
    @Column(nullable = false)
    private String role;

    public Usuario() {
    }

    public Usuario(Long id) {
        super.setId(id);
    }

    public Usuario(String email, String nome, String password, String role) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.role = role;
    }

    public Usuario(Long id, String email, String nome, String password, String role) {
        super.setId(id);
        this.email = email;
        this.nome = nome;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setAdmin(String role) {
        this.role = role;
    }
}