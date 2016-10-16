package entidades;

public class Jugador {
	
	
	
	private double dinero;
	private String nombre;

	private int comodines;  // 2 comodines por jugador
	private Pregunta preguntaRecibida;
	
	private int contPregunta;
	
	public Jugador()
	{};
	public Jugador(String nom)
	{
		
		nombre = nom;
		comodines = 2;
		
		contPregunta = 0;
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
	
	public int responderPregunta(int respuesta)
	{	// return 1 respuesta correcta, 2 respuesta incorrecta, return 0 se retira
		if(respuesta == preguntaRecibida.getRespuesta())
		{
			dinero = dinero +100;
			contPregunta++;
			return 1;
		}
		else
		{
			if(respuesta == 0) // retiro
			{
				return 0;
			}
			else {
			dinero = 0;  //dinero = dinero -100;  cambio #1 
			contPregunta = 0;
			return 2;
			}
		}
		
	}
	
	public int retiro()
	{
		return contPregunta;
	}
	
	public void setComodines()
	{
		
	}
	

}
