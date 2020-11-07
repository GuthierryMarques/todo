package br.com.guthierry.todo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.guthierry.todo.controller.dto.TarefaDto;
import br.com.guthierry.todo.controller.form.TarefaForm;
import br.com.guthierry.todo.controller.form.TarefaUpdateForm;
import br.com.guthierry.todo.model.Tarefa;
import br.com.guthierry.todo.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@GetMapping
	public List<TarefaDto> lista(String nomeTarefa){
		if(nomeTarefa == null) {
			List<Tarefa> tarefas = tarefaRepository.findAll();
			return TarefaDto.converter(tarefas);
		} else {
			List<Tarefa> tarefas = tarefaRepository.listarPorNome(nomeTarefa);
			return TarefaDto.converter(tarefas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TarefaDto> detalhar(@PathVariable Long id){
		Optional<Tarefa> retorno = tarefaRepository.findById(id);
		if(retorno.isPresent()) {
			return ResponseEntity.ok(new TarefaDto(retorno.get()));
		}
		return ResponseEntity.notFound().build();
			
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TarefaDto> cadastraTarefa(@RequestBody @Valid TarefaForm form, UriComponentsBuilder uriBuilder) {
		Tarefa tarefa = tarefaRepository.save(form.converter());
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).body(new TarefaDto(tarefa));
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TarefaDto> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaUpdateForm form) {
		Optional<Tarefa> retorno = tarefaRepository.findById(id);
		if(retorno.isPresent()) {
			return ResponseEntity.ok(new TarefaDto(form.atualizar(id, tarefaRepository)));
		}
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeTarefa(@PathVariable Long id) {
		Optional<Tarefa> retorno = tarefaRepository.findById(id);
		if(retorno.isPresent()) {
			tarefaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
