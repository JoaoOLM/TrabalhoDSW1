package br.ufscar.dc.dsw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface ICandidaturaDAO extends CrudRepository<Candidatura, Long>{

	Candidatura findById(long id);

	List<Candidatura> findAll();
	
	Candidatura save(Candidatura candidatura);

	void deleteById(Long id);

	Optional<Candidatura> findByVagaAndProfissional(Vaga vaga, Profissional profissional);

    List<Candidatura> findByProfissional(Profissional profissional);

	List<Candidatura> findByVaga(Vaga vaga);
}