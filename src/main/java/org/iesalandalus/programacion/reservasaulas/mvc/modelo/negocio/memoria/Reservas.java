package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Reservas implements IReservas{

	private List<Reserva> coleccionReservas;

	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}


	public Reservas (Reservas reservas) {			
		setReservas(reservas);
	}



	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("ERROR: No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);

	}


	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<>() ;
		for (Reserva reserva : reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		if ( coleccionReservas.contains(reserva)){
			throw new OperationNotSupportedException("La reserva ya existe.");
		}
		else coleccionReservas.add(new Reserva(reserva));                       

	}


	public Reserva buscar(Reserva reserva) {
		int indice = coleccionReservas.indexOf(reserva);

		if (indice != -1) {
			return new Reserva (coleccionReservas.get(indice));
		} else {
			return null;
		}
	}


	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}

		if (!coleccionReservas.remove(reserva)) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}



	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(reserva.toString());
		}
		return representacion;
	}


	public List<Reserva> getReservasAula(Aula aula) {
		if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List<Reserva> reservaAula = new ArrayList<>();

		for (Reserva reserva : coleccionReservas) {
			if( reserva.getAula().equals(aula))

				reservaAula.add(new Reserva(reserva));

		}

		return reservaAula;
	}


	public List<Reserva> getReservasProfesor(Profesor profesor) {

		if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		List<Reserva> reservaProfesor = new ArrayList<>();

		for (Reserva reserva : coleccionReservas) {
			if( reserva.getProfesor().equals(profesor))

				reservaProfesor.add(new Reserva(reserva));
		}

		return reservaProfesor;
	}



	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if(permanencia==null)
			throw new IllegalArgumentException("No se pueden consultar las reservas de una permanencia nula.");
		List<Reserva> reservaPermanencia = new ArrayList<>();

		for (Reserva reserva : coleccionReservas) {
			if( reserva.getPermanencia().equals(permanencia))
				reservaPermanencia.add(new Reserva(reserva));
		}

		return reservaPermanencia;
	}


	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(Reserva reserva : coleccionReservas) {
			if(coleccionReservas.equals(aula) && coleccionReservas.equals(permanencia))
				return false;
		}
		return true;
	}
}
