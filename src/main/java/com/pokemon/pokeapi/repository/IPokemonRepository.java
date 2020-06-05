package com.pokemon.pokeapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.pokeapi.model.Pokemon;
import com.pokemon.pokeapi.model.PokemonIdentity;

@Repository
public interface IPokemonRepository extends JpaRepository<Pokemon, PokemonIdentity>{
	public Page<Pokemon> findByUsuarioId(int usuarioId, Pageable pageable);
}
