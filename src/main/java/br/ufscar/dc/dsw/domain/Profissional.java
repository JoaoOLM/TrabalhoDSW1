package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Profissional{
    
    public enum Sexo {
        MASCULINO, FEMININO, OUTRO
    }

    private Long id;
    private Usuario usuario;
    private String cpf;
    private String telefone;
    private Sexo sexo;
    private Date dataNascimento;
    
    public Profissional() {

    }

    public Profissional(Long id) {
        this.id = id;
    }


    public Profissional(Usuario usuario, String CPF, String telefone, Sexo sexo, Date dataNascimento) {
        this.usuario = usuario;
        this.cpf = CPF;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
    
    public Profissional(Long id, Usuario usuario, String cPF, String telefone, Sexo sexo, Date dataNascimento) {
        this.id = id;
        this.usuario = usuario;
        cpf = cPF;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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
