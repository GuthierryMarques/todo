package br.com.guthierry.todo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.guthierry.todo.model.Tarefa;
import br.com.guthierry.todo.repository.TarefaRepository;

public class TarefaUpdateForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Tarefa atualizar(Long id, TarefaRepository tarefaRepository) {
		Tarefa tarefa =  tarefaRepository.getOne(id);
		tarefa.setNome(this.nome);
		tarefa.setDescricao(this.descricao);
		return tarefa;
	}


	

}
