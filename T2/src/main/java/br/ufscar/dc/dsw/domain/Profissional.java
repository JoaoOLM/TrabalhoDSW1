package br.ufscar.dc.dsw.domain;

import java.sql.Date;

import br.ufscar.dc.dsw.validation.UniqueCPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {

    public enum Sexo {
        MASCULINO, FEMININO, OUTRO
    }

    @UniqueCPF (message = "{Unique.profissional.CPF}")
    @NotBlank
    @Column(nullable = false, length = 14, unique = true)
    @Size(min = 14, max = 14, message = "{Size.profissional.CPF}")
    private String cpf;

    @NotBlank
    @Column(nullable = false, length = 15)
    @Size(min = 15, max = 15, message = "{Size.profissional.telefone}")
    private String telefone;

    @NotNull
    @Column(nullable = false)
    private Sexo sexo;

    @NotNull
    @Past
    @Column(nullable = false)
    private Date dataNascimento;

    public Profissional() {
    }

    public Profissional(Long id) {
        super.setId(id);
    }

    public Profissional(String email, String nome, String password, String role, String cpf, String telefone, Sexo sexo, Date dataNascimento) {
        super(email, nome, password, role);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(Long id, String email, String nome, String password, String role, String cpf, String telefone, Sexo sexo, Date dataNascimento) {
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
