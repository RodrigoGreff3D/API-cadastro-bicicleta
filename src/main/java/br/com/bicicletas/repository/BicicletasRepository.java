package br.com.bicicletas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bicicletas.model.Bicicletas;

@Repository
public interface BicicletasRepository extends JpaRepository<Bicicletas, Long> {

	@Query("select m from Bicicletas m where m.modelo = ?1")
	Page<Bicicletas> findByBicicletasModelo(String modelo, Pageable paginacao);

}