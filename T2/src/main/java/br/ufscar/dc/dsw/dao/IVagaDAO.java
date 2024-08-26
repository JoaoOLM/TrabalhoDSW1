package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IVagaDAO extends CrudRepository<Vaga, Long>{

	Vaga findById(long id);

	List<Vaga> findAll();
	
	Vaga save(Vaga vaga);

	void deleteById(Long id);

    List<Vaga> findByEmpresa(Empresa empresa);

	List<Vaga> findByEmpresaCidade(String cidade);

	@Query("SELECT v FROM Vaga v WHERE v.id NOT IN (SELECT c.vaga.id FROM Candidatura c WHERE c.profissional.id = :profissionalId)")
    List<Vaga> findVagasNaoCandidatadasPorProfissional(@Param("profissionalId") Long profissionalId);
}