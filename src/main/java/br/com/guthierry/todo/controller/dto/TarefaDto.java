package br.com.guthierry.todo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.guthierry.todo.model.Tarefa;

public class TarefaDto {

	private Long id;
	private String nome;
	private String descricao;	
	

	public TarefaDto(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.nome = tarefa.getNome();
		this.descricao = tarefa.getDescricao();
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

	public static List<TarefaDto> converter(List<Tarefa> tarefas) {		
		return tarefas.stream().map(TarefaDto::new).collect(Collectors.toList());
	}


}
