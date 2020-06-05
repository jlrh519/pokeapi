package com.pokemon.pokeapi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pokemon.pokeapi.model.Pokemon;
import com.pokemon.pokeapi.model.PokemonIdentity;
import com.pokemon.pokeapi.model.Usuario;
import com.pokemon.pokeapi.repository.IPokemonRepository;
import com.pokemon.pokeapi.repository.IUsuarioRepository;

@SpringBootTest
class PokeapiApplicationTests {

	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private IPokemonRepository pokemonRepo;
	
	@Test
	void crearUsuarioTest() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Jose");
		usuario.setPassword(encoder.encode("123"));
		
		Usuario usuarioRetorno = usuarioRepo.save(usuario);
		
		assertTrue(usuario.getPassword().equalsIgnoreCase(usuarioRetorno.getPassword()));
	}
	
	@Test
	void crearPokemon() {
		Optional<Usuario> opt = usuarioRepo.findById(6);
		Usuario usuario = opt.get();
		
		PokemonIdentity pi = new PokemonIdentity();
		pi.setIdPokedex(1);
		pi.setNombre("pikachu2");
		pi.setUrlImagen("ftp://");
		
		Pokemon pokemon = new Pokemon();
		pokemon.setPokemonIdentity(pi);
		pokemon.setAlias("Es un alias");
		pokemon.setUsuario(usuario);
		
		Pokemon pokemonRetorno = pokemonRepo.save(pokemon);
		assertTrue(pokemonRetorno.getPokemonIdentity().getIdPokedex() == 
				pokemon.getPokemonIdentity().getIdPokedex());
	}

}
