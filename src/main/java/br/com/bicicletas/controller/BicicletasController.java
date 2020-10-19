package br.com.bicicletas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bicicletas.model.Bicicletas;
import br.com.bicicletas.repository.BicicletasRepository;

@RestController
@RequestMapping(value = "/bicicletas")
public class BicicletasController {

	@Autowired
	public BicicletasRepository bicicletasRepository;

	@PostMapping(value = "/")
	@CacheEvict(value = "listaBicicletas", allEntries = true)
	public ResponseEntity<Bicicletas> cadastrar(@RequestBody Bicicletas bicicletas) {
		Bicicletas salvar = bicicletasRepository.save(bicicletas);
		return new ResponseEntity<Bicicletas>(salvar, HttpStatus.OK);
	}

	@GetMapping(value = "/")
	@Cacheable(value = "listaBicicletas")
	public ResponseEntity<Page<Bicicletas>> listarTodas(@RequestParam(required = false) String modelo,
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao) {

		if (modelo == null) {
			Page<Bicicletas> bicicletas = bicicletasRepository.findAll(paginacao);
			return new ResponseEntity<Page<Bicicletas>>(bicicletas, HttpStatus.OK);

		} else {
			Page<Bicicletas> bicicletas = bicicletasRepository.findByBicicletasModelo(modelo, paginacao);
			return new ResponseEntity<Page<Bicicletas>>(bicicletas, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Bicicletas> listaId(@PathVariable(value = "id") Long id) {
		Optional<Bicicletas> bicicletas = bicicletasRepository.findById(id);
		return new ResponseEntity<Bicicletas>(bicicletas.get(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	@Cacheable(value = "listaBicicletas")
	public ResponseEntity<Bicicletas> deletar(@PathVariable("id") Long id) {
		bicicletasRepository.deleteById(id);
		return (ResponseEntity) ResponseEntity.ok().build();
	}

	@PutMapping(value = "/{id}")
	@Cacheable(value = "listaBicicletas")
	public ResponseEntity<Bicicletas> atualizar(@RequestBody Bicicletas bicicleta, @PathVariable("id") Long id) {
		Optional<Bicicletas> optional = bicicletasRepository.findById(id);
		return new ResponseEntity<Bicicletas>(bicicleta, HttpStatus.OK);
	}

}
