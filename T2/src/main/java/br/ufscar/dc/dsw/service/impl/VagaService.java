package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Service
@Transactional(readOnly = false)
public class VagaService implements IVagaService {

	@Autowired
	IVagaDAO dao;

	@Autowired
	ICandidaturaDAO candidaturaDAO;
	
	public void salvar(Vaga vaga) {
		dao.save(vaga);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Vaga buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarPorEmpresa(Empresa empresa) {
		return dao.findByEmpresa(empresa);
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarPorCidade(String cidade) {		
		return dao.findByEmpresaCidade(cidade);
	}

	@Transactional (readOnly = true)
    public List<Vaga> buscarVagasNaoCandidatadasPorProfissional(Long profissionalId) {
        return dao.findVagasNaoCandidatadasPorProfissional(profissionalId);
    }

	@Transactional(readOnly = true)
	public boolean vagaTemCandidaturas(Long id) {
		return !dao.findById(id.longValue()).getCandidaturas().isEmpty(); 
	}
}
