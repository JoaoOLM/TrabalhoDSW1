package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Vaga {


    private Long id;
    private Empresa empresa;
    private String descricao;
    private double remuneracao;
    private Date dataLimiteInscricao;
    private Timestamp dataCriacao = new Timestamp(System.currentTimeMillis());

    public Vaga() {
    }
    
    public Vaga(Long id) {
        this.id = id;
    }

    public Vaga(Long id, Empresa empresa, String descricao, double remuneracao, Date dataLimiteInscricao) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Vaga(Empresa empresa, String descricao, double remuneracao, Date dataLimiteInscricao) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Vaga(Long id, Empresa empresa, String descricao, double remuneracao, Date dataLimiteInscricao, Timestamp dataCriacao) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Date getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(Date dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
