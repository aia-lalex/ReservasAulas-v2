package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas{

	private List <Aula> coleccionAulas;

	// constructor por defecto

	public Aulas(int capacidad) throws IllegalArgumentException,
	NullPointerException {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionAulas = new ArrayList<>();
	}

	public Aulas(Aulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		coleccionAulas = aulas.getAulas();
	}
	



	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		List<Aula> otrasAulas = new ArrayList<>();
		for (Aula aula : aulas) {
			otrasAulas.add(new Aula(aula));
		}
		return otrasAulas;
	}

	public int getNumAulas() {
		return coleccionAulas.size();
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("ERROR: No se puede insertar un aula nula.");
		}
		if (coleccionAulas.contains(aula)){
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");

		} else coleccionAulas.add(new Aula(aula));
	}




	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un aula nula.");
		}

		if (!coleccionAulas.remove(aula)) {
			throw new OperationNotSupportedException("ERROR: El aula a borrar no existe.");
		}
	}
	public Aula buscar(Aula aula) throws IllegalArgumentException, NullPointerException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		Iterator<Aula> it = coleccionAulas.iterator();
		while (it.hasNext()) {
			if (it.next().equals(aula)) {
				return new Aula(aula);
			}
		}
		return null;

	}


	// To string

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for ( Aula aula : coleccionAulas){
			representacion.add(aula.toString());
		}
		return representacion;
	}
}
