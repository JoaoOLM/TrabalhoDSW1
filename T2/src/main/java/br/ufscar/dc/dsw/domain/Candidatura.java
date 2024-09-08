package br.ufscar.dc.dsw.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
import jakarta.validation.constraints.FutureOrPresent;
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
    @Column(length = 10485760) // 10MB
    private byte[] arquivoCurriculo;

    @NotNull
    @Column(nullable = false)
    private Timestamp dataCandidatura = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ABERTO;

    @FutureOrPresent
    @Column(nullable = true)
    private LocalDateTime horarioEntrevista;

    @Column(nullable = true, length = 100)
    private String linkVideoconferencia;

    // Construtor padrão
    public Candidatura() {
        this.dataCandidatura = new Timestamp(System.currentTimeMillis());
        this.status = Status.ABERTO;
    }

    // Construtor completo
    public Candidatura(Vaga vaga, Profissional profissional, byte[] arquivoCurriculo) {
        this();
        this.vaga = vaga;
        this.profissional = profissional;
        this.arquivoCurriculo = arquivoCurriculo;
    }

    public Candidatura(Vaga vaga, Profissional profissional, byte[] arquivoCurriculo, Status status, LocalDateTime horarioEntrevista, String linkVideoconferencia) {
        this(vaga, profissional, arquivoCurriculo);
        this.status = status;
        this.horarioEntrevista = horarioEntrevista;
        this.linkVideoconferencia = linkVideoconferencia;
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

    public LocalDateTime getHorarioEntrevista() {
        return horarioEntrevista;
    }

    public void setHorarioEntrevista(LocalDateTime horarioEntrevista) {
        this.horarioEntrevista = horarioEntrevista;
    }

    public String getLinkVideoconferencia() {
        return linkVideoconferencia;
    }

    public void setLinkVideoconferencia(String linkVideoconferencia) {
        this.linkVideoconferencia = linkVideoconferencia;
    }
}
