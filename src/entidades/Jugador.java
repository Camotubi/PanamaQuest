package entidades;

public class Jugador {
	
	
	
	private double dinero;
	private String nombre;
	private int comodin;  // 2 comodines por jugador
	private Pregunta preguntaRecibida;
	private boolean retirado = false;
	private int contPregunta;
	
	public Jugador()
	{};
	public Jugador(String nom)
	{
		
		nombre = nom;
		comodin = 2;
		
		contPregunta = 0;
	}
	
	public int getcontPregunta(){
		return contPregunta;
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
		int respCorrecta =preguntaRecibida.getRespuesta();
		if((respuesta-1) == respCorrecta)
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
			else 
			{
				if(respuesta == 10 && comodin >0)//uso de comodin
				{
					comodin--;
					return 3;
				}
				else
				{
					if(respuesta == 10 &&comodin <=0)
					{
						dinero = 0;  
						contPregunta = 0;
						return 4;
					}
					else
				{//incorrecta
					dinero = 0;  
					contPregunta = 0;
					return 2;
				}
				}
					
			
			}
		}
		
	}
	
	public int retiro()
	{
		return contPregunta;
	}
	
	public void setComodin(int x)
	{
		comodin = comodin-x;
	}
	
	public int getComodin()
	{
		return comodin;
	}
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
	

}
