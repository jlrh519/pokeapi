package com.pokemon.pokeapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pokemon")
public class Pokemon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485667042371311928L;

	@EmbeddedId
	private PokemonIdentity pokemonIdentity;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "shiny")
	private boolean shiny;
	
	@Column(name = "nature")
	private String nature;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuario;
	
	public PokemonIdentity getPokemonIdentity() {
		return pokemonIdentity;
	}

	public void setPokemonIdentity(PokemonIdentity pokemonIdentity) {
		this.pokemonIdentity = pokemonIdentity;
	}

	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public boolean isShiny() {
		return shiny;
	}
	
	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}