package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    private String role = "ROLE_EMPRESA";

    public Profissional() {
    }

    public Profissional(Long id) {
        super.setId(id);
    }

    public Profissional(String email, String nome, String password, String role, String cpf, String telefone, Sexo sexo, String dataNascimento) {
        super(email, nome, password, role);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(Long id, String email, String nome, String password, String role, String cpf, String telefone, Sexo sexo, String dataNascimento) {
        super(id, email, nome, password, role);
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

    public void setSexo(Integer sexo) {
        if (sexo != null && sexo >= 0 && sexo < Sexo.values().length) {
            this.sexo = Sexo.values()[sexo];
        } else {
            throw new IllegalArgumentException("Valor inválido para sexo: " + sexo);
        }
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
