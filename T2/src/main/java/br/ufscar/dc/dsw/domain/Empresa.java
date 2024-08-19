package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa extends Usuario {

    @NotBlank
    @Column(nullable = false, length = 18, unique = true)
    private String CNPJ;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String cidade;

    public Empresa() {
    }

    public Empresa(String email, String nome, String senha, boolean isAdmin, String CNPJ, String descricao, String cidade) {
        super(email, nome, senha, isAdmin);
        this.CNPJ = CNPJ;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public Empresa(Long id, String email, String nome, String senha, boolean isAdmin, String CNPJ, String descricao, String cidade) {
        super(id, email, nome, senha, isAdmin);
        this.CNPJ = CNPJ;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    // Getters e Setters

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}