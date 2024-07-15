package br.ufscar.dc.dsw.domain;

import java.sql.Timestamp;

public class Candidatura {

    public enum Status {
        NAO_SELECIONADO,
        ENTREVISTA,
        ABERTO
    }

    private Long id;
    private Vaga vaga;
    private Long profissionalId;
    private String arquivoCurriculo;
    private Timestamp dataCandidatura;
    private Status status;

    public Candidatura() {
    }

    public Candidatura(Long id, Vaga vaga, Long profissionalId, String arquivoCurriculo, Timestamp dataCandidatura, Status status) {
        this.id = id;
        this.vaga = vaga;
        this.profissionalId = profissionalId;
        this.arquivoCurriculo = arquivoCurriculo;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vaga getVagaId() {
        return vaga;
    }

    public void setVagaId(Vaga vaga) {
        this.vaga = vaga;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getArquivoCurriculo() {
        return arquivoCurriculo;
    }

    public void setArquivoCurriculo(String arquivoCurriculo) {
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
