package br.com.guthierry.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.guthierry.todo.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findByNome(String nome);

	@Query("SELECT t FROM Tarefa t WHERE t.nome like :nomeTarefa")
	List<Tarefa> listarPorNome(@Param("nomeTarefa") String nomeTarefa);

}
