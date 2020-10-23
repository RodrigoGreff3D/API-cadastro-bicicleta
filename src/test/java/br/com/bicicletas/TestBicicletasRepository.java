package br.com.bicicletas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bicicletas.model.Bicicletas;
import br.com.bicicletas.repository.BicicletasRepository;

@SpringBootTest
public class TestBicicletasRepository {

	private String descricao = "Bicicleta d";
	private String modelo = "Modelo 1";
	private double preco = 1000;
	private String nomeComprador = "Rodrigo";
	private String loja = "Loja Bicicletas";

	@Autowired
	public BicicletasRepository bicicletasRepository;

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createShouldPersistData() {
		Bicicletas bicicleta = new Bicicletas(descricao, modelo, preco, nomeComprador, loja);
		this.bicicletasRepository.save(bicicleta);
		assertThat(bicicleta.getId()).isNotNull();
		assertThat(bicicleta.getModelo()).isEqualTo("Modelo 1");
		assertThat(bicicleta.getPreco()).isEqualTo(1000);
		assertThat(bicicleta.getNomeComprador()).isEqualTo("Rodrigo");
		assertThat(bicicleta.getLoja()).isEqualTo("Loja Bicicletas");
	}

	@Test
	public void deleteShouldRemoveData() {
		Bicicletas bicicleta = new Bicicletas(descricao, modelo, preco, nomeComprador, loja);
		this.bicicletasRepository.save(bicicleta);
		bicicletasRepository.deleteById(bicicleta.getId());
		assertThat(bicicletasRepository.findById(bicicleta.getId())).isEmpty();
	}

	@Test
	public void updateShouldChangeAndRemoveData() {
		Bicicletas bicicleta = new Bicicletas(descricao, modelo, preco, nomeComprador, loja);
		this.bicicletasRepository.save(bicicleta);
		bicicleta.setNomeComprador("Rodrigo D");
		bicicleta.setLoja("Loja Bicicletas 2");
		bicicleta.setDescricao("Bicicleta A");
		bicicleta.setPreco(1500);
		bicicleta.setModelo("Modelo 2");

		bicicleta = this.bicicletasRepository.save(bicicleta);

		assertThat(bicicleta.getModelo()).isEqualTo("Modelo 2");
		assertThat(bicicleta.getPreco()).isEqualTo(1500);
		assertThat(bicicleta.getNomeComprador()).isEqualTo("Rodrigo D");
		assertThat(bicicleta.getLoja()).isEqualTo("Loja Bicicletas 2");
	}
//
//	@Test
//	public void createWhenNameIsNullShouldThrowConstraintViolationException() {
//		thrown.expect(ConstraintViolationException.class);
//		thrown.expectMessage("O modelo é obrigatório");
//		this.bicicletasRepository.save(new Bicicletas());
//	}

}
