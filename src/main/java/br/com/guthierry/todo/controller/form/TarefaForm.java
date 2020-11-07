package br.com.guthierry.todo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.guthierry.todo.model.Tarefa;

public class TarefaForm {
	
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
	public Tarefa converter() { 
		return new Tarefa(nome, descricao);
	}

}
