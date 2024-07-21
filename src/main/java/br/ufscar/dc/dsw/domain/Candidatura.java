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
    private Profissional profissional;
    private String arquivoCurriculo;
    private Timestamp dataCandidatura = new Timestamp(System.currentTimeMillis());
    private Status status = Status.ABERTO;

    public Candidatura() {
    }

    public Candidatura(Vaga vaga, Profissional profissional, String arquivoCurriculo) {
        this.vaga = vaga;
        this.profissional = profissional;
        this.arquivoCurriculo = arquivoCurriculo;
    }

    public Candidatura(Vaga vaga, Profissional profissional, String arquivoCurriculo, Timestamp dataCandidatura, Status status) {
        this.vaga = vaga;
        this.profissional = profissional;
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
