package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {

	MANANA("Ma�ana"),TARDE("Tarde");
	
	private String cadenaAMostrar;
	
	private Tramo(String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar;
		
	}
	
	@Override
	public String toString() {
		return cadenaAMostrar;
	}
	
	
	