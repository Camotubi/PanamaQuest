package entidades;

import java.util.ArrayList;

public class Pregunta {

	private String pregunta;
	private ArrayList<String> opciones = new ArrayList<String>();
	private int respuesta;
	private int categoria;
	private String dirImagen;
	private String dirAudio;

	public Pregunta(String pregunta, ArrayList<String> opciones, int respuesta, int categoria,String dirImg,String dirAudio) {

		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuesta = respuesta;
		this.categoria = categoria;
		this.dirImagen = dirImg;
		this.dirAudio = dirAudio;
	}
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
	public String getDirImagen() {
		return dirImagen;
	}
	public void setDirImagen(String dirImagen) {
		this.dirImagen = dirImagen;
	}
	public String getDirAudio() {
		return dirAudio;
	}
	public void setDirAudio(String dirAudio) {
		this.dirAudio = dirAudio;
	}


}
