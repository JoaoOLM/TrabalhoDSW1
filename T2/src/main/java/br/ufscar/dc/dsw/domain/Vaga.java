package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Vaga")
public class Vaga extends AbstractEntity<Long> {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference
    private Empresa empresa;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private double remuneracao;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private Date dataLimiteInscricao;

    @NotNull
    @Column(nullable = false)
    private Timestamp dataCriacao = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "vaga")
	private List<Candidatura> candidaturas;

    // Construtores

    public Vaga() {
    }

    public Vaga(Empresa empresa, String descricao, double remuneracao, Date dataLimiteInscricao) {
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public Vaga(Long id, Empresa empresa, String descricao, double remuneracao, Date dataLimiteInscricao, Timestamp dataCriacao) {
        super.setId(id);
        this.empresa = empresa;
        this.descricao = descricao;
        this.remuneracao = remuneracao;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters

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

    public List<Candidatura> getCandidaturas(){
		return candidaturas;
	}
	
	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}
}
