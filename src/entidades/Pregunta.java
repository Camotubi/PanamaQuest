package entidades;

import java.util.ArrayList;

public class Pregunta {
	public Pregunta(String pregunta, ArrayList<String> opciones, int respuesta, int categoria) {

		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuesta = respuesta;
		this.categoria = categoria;
	}
	private String pregunta;
	private ArrayList<String> opciones = new ArrayList<String>();
	private int respuesta;
	private int categoria;


}
