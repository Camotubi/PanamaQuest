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
<<<<<<< HEAD
	/*public Pregunta(String pregunta, String opciones[],int respuesta,int categoria )
	{
		this
	} */ 
=======

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
>>>>>>> 20839c263af4cacd8114606fadd453c4017789d1

}
