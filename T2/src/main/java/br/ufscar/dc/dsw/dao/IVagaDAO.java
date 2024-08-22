package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IVagaDAO extends CrudRepository<Vaga, Long>{

	Vaga findById(long id);

	List<Vaga> findAll();
	
	Vaga save(Vaga vaga);

	void deleteById(Long id);

    List<Vaga> findByEmpresa(Empresa empresa);
}