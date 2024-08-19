package br.ufscar.dc.dsw.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {

    public enum Sexo {
        MASCULINO, FEMININO, OUTRO
    }

    @NotBlank
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @NotBlank
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotNull
    @Column(nullable = false)
    private Sexo sexo;

    @NotNull
    @Column(nullable = false, length = 19)
    private String dataNascimento;

    public Profissional() {
    }

    public Profissional(Long id) {
        super.setId(id);
    }

    public Profissional(String email, String nome, String senha, boolean isAdmin, String cpf, String telefone, Sexo sexo, Date dataNascimento) {
        super(email, nome, senha, isAdmin);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(Long id, String email, String nome, String senha, boolean isAdmin, String cpf, String telefone, Sexo sexo, Date dataNascimento) {
        super(id, email, nome, senha, isAdmin);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    // Getters and Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
