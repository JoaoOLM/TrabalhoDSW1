package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import java.util.Optional;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public interface ICandidaturaService {

	Candidatura buscarPorId(Long id);

	List<Candidatura> buscarTodos();

	void salvar(Candidatura Candidatura);

	void excluir(Long id);

	Optional<Candidatura> buscarPorVagaEProfissional(Vaga vaga, Profissional profissional);

	List<Candidatura> buscarPorProfissional(Profissional profissional);

	List<Candidatura> buscarPorVaga(Vaga vaga);
}
