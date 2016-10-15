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

	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public ArrayList<String> getOpciones() {
		return opciones;
	}
	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


}
