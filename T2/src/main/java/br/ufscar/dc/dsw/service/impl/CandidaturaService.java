package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;

@Service
@Transactional(readOnly = false)
public class CandidaturaService implements ICandidaturaService {

    @Autowired
    private ICandidaturaDAO dao;

    public void salvar(Candidatura candidatura) {
        dao.save(candidatura);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Candidatura buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Candidatura> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Candidatura> buscarPorVagaEProfissional(Vaga vaga, Profissional profissional) {
        return dao.findByVagaAndProfissional(vaga, profissional);
    }

    @Transactional(readOnly = true)
    public List<Candidatura> buscarPorProfissional(Profissional profissional) {
        return dao.findByProfissional(profissional);
    }
    
    @Transactional(readOnly = true)
    public List<Candidatura> buscarPorVaga(Vaga vaga) {
        return dao.findByVaga(vaga);
    }
}
