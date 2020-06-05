package com.pokemon.pokeapi.errohandling;

public class UsuarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6138761693672952150L;

	public UsuarioNotFoundException(int id) {
		super("No se puede encontrar el usuario con ID: " + id);
	}

}
