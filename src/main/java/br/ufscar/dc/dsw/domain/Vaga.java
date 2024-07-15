package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Vaga {
    private int id;
    private Empresa empresa;
    private String descricao;
    private BigDecimal remuneracao;
    private Date dataLimiteInscricao;
    private StatusVaga status;
    private Timestamp dataCriacao;

    public enum StatusVaga {
        ABERTA,
        FECHADA
    }

    public Vaga() {
    }

    public Vaga(int id, Empresa empresa, String descricao, BigDecimal remuneracao, Date dataLimiteInscricao, StatusVaga status, Timestamp dataCriacao) {
        this.id = id;
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public BigDecimal getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(BigDecimal remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Date getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(Date dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public StatusVaga getStatus() {
        return status;
    }

    public void setStatus(StatusVaga status) {
        this.status = status;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
