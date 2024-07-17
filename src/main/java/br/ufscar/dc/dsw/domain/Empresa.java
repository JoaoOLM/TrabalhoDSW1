package br.ufscar.dc.dsw.domain;

public class Empresa {
    private Long id;
    private Usuario usuario;
    private String CNPJ;
    private String descricao;
    private String cidade;

    public Empresa() {
    }

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(Usuario usuario, String CNPJ, String descricao, String cidade) {
        this.usuario = usuario;
        this.CNPJ = CNPJ;
        this.descricao = descricao;
        this.cidade = cidade;
    }

    public Empresa(Long id, Usuario usuario, String cNPJ, String descricao, String cidade) {
        this.id = id;
        this.usuario = usuario;
        CNPJ = cNPJ;
        this.descricao = descricao;
        this.cidade = cidade;
    }

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
