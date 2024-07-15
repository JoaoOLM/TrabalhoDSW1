package br.ufscar.dc.dsw.domain;

public class Smartphone {

    private Long id;
    private String marca;
    private String modelo;
    private String sistemaOperacional;
    private Integer memoriaRamGB;
    private Integer armazenamentoGB;
    private Float tamanhoTela;
    private Double preco;

    public Smartphone(Long id) {
        this.id = id;
    }

    public Smartphone(String marca, String modelo, String sistemaOperacional,
                      Integer memoriaRamGB, Integer armazenamentoGB, Float tamanhoTela,
                      Double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperacional = sistemaOperacional;
        this.memoriaRamGB = memoriaRamGB;
        this.armazenamentoGB = armazenamentoGB;
        this.tamanhoTela = tamanhoTela;
        this.preco = preco;
    }

    public Smartphone(Long id, String marca, String modelo, String sistemaOperacional,
                      Integer memoriaRamGB, Integer armazenamentoGB, Float tamanhoTela,
                      Double preco) {
        this(marca, modelo, sistemaOperacional, memoriaRamGB, armazenamentoGB, tamanhoTela, preco);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getMemoriaRamGB() {
        return memoriaRamGB;
    }

    public void setMemoriaRamGB(Integer memoriaRamGB) {
        this.memoriaRamGB = memoriaRamGB;
    }

    public Integer getArmazenamentoGB() {
        return armazenamentoGB;
    }

    public void setArmazenamentoGB(Integer armazenamentoGB) {
        this.armazenamentoGB = armazenamentoGB;
    }

    public Float getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(Float tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
