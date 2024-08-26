package br.ufscar.dc.dsw.domain;

import java.sql.Timestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Candidatura")
public class Candidatura extends AbstractEntity<Long> {

    public enum Status {
        NAO_SELECIONADO,
        ENTREVISTA,
        ABERTO
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @Lob
	@Basic
	@Column(length = 10485760) // 1MB
	private byte[] arquivoCurriculo;

    @NotNull
    @Column(nullable = false)
    private Timestamp dataCandidatura = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ABERTO;

    // Construtores
    public Candidatura() {
    }

    public Candidatura(Vaga vaga, Profissional profissional, byte[] arquivoCurriculo) {
        this.vaga = vaga;
        this.profissional = profissional;
        this.arquivoCurriculo = arquivoCurriculo;
    }

    public Candidatura(Vaga vaga, Profissional profissional, byte[] arquivoCurriculo, Timestamp dataCandidatura, Status status) {
        this.vaga = vaga;
        this.profissional = profissional;
        this.arquivoCurriculo = arquivoCurriculo;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    // Getters e Setters
    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public byte[] getArquivoCurriculo() {
        return arquivoCurriculo;
    }

    public void setArquivoCurriculo(byte[] arquivoCurriculo) {
        this.arquivoCurriculo = arquivoCurriculo;
    }

    public Timestamp getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Timestamp dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
