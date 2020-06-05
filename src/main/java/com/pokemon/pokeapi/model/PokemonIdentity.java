package com.pokemon.pokeapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Immutable;

@Embeddable
@Immutable
public class PokemonIdentity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1187581597185209608L;
	
	@GeneratedValue
	@Column(name = "idPokedex", nullable = false, unique = true)
	private int idPokedex;
	
	@Column(name = "nombre", nullable = false,length = 50)
	private String nombre;
	
	@Column(name = "urlImagen", nullable = false)
	private String urlImagen;
	
	public int getIdPokedex() {
		return idPokedex;
	}
	
	public void setIdPokedex(int idPokedex) {
		this.idPokedex = idPokedex;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}
	
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
}
