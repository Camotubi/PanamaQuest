package entidades;

public class Jugador {
	
	
	
	private double dinero;
	private String nombre;

	private int comodines;  // 2 comodines por jugador
	private Pregunta preguntaRecibida;
	
	public Jugador(String nom)
	{
		nombre = nom;
		comodines = 2;
	}

	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Pregunta getPreguntaRecibida() {
		return preguntaRecibida;
	}
	public void setPreguntaRecibida(Pregunta preguntaRecibida) {
		this.preguntaRecibida = preguntaRecibida;
	}
	
	public boolean responderPregunta(int respuesta)
	{
		if(respuesta == preguntaRecibida.getRespuesta())
		{
			dinero = dinero +100;
			return true;
		}
		else
		{
			dinero = dinero -100;
			return false;
		}
		
	}

}
