package com.pokemon.pokeapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokeapi.controller.PokemonController;
import com.pokemon.pokeapi.model.Pokemon;
import com.pokemon.pokeapi.model.PokemonIdentity;
import com.pokemon.pokeapi.repository.IPokemonRepository;
import com.pokemon.pokeapi.repository.IUsuarioRepository;

@RestController
@RequestMapping("/pokemon")
public class RestPokemonController {

	private static Logger LOG = LoggerFactory.getLogger(PokemonController.class);

	@Autowired
	private IPokemonRepository pokemonRepo;

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@GetMapping("/usuario/{usuarioId}")
	public Page<Pokemon> getAllPokemonsByUsuario(@PathVariable (value = "usuarioId") int usuarioId, 
			Pageable pageable){
		LOG.info(String.format("Consultando los pokemon asociados al idUsuario %s", usuarioId));
		return pokemonRepo.findByUsuarioId(usuarioId, pageable);
	}

	@PostMapping
	public void insertPokemon(@RequestBody Pokemon pokemon) {
		try {
			LOG.info(String.format("Guardando el pokemon %s asociados al idUsuario %s", 
					pokemon.getPokemonIdentity().getIdPokedex(), pokemon.getUsuario().getId()));
			
		} catch (Exception exc) {
			LOG.error(String.format("Error al obtener datos del objeto enviado por el usuario."));
		}
		
		pokemonRepo.save(pokemon);
	}

	@PutMapping
	public void updatePokemon(@RequestBody Pokemon pokemon) {
		try {
			LOG.info(String.format("Actualizando el pokemon con ID %s", 
					pokemon.getPokemonIdentity().getIdPokedex()));
		} catch (Exception exc) {
			LOG.error(String.format("Error al obtener datos del objeto enviado por el usuario."));
		}
		
		pokemonRepo.save(pokemon);
	}

	@DeleteMapping
	public void deletePokemon(@RequestBody PokemonIdentity pokemonIdentity) {
		try {
			LOG.info(String.format("Eliminando el pokemon con ID %s", 
					pokemonIdentity.getIdPokedex()));
		} catch (Exception exc) {
			LOG.error(String.format("Error al obtener datos del objeto enviado por el usuario."));
		}
		
		pokemonRepo.deleteById(pokemonIdentity);
	}
}
