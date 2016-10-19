package entidades;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import interfaz.Main;

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
					//audio de comodin, porque porque no
					try {
					Clip sonido = AudioSystem.getClip();
					AudioInputStream Audioreti = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/comodin.wav"));
					sonido.open(Audioreti);
					sonido.start();
					}catch(LineUnavailableException Aude)
					{
						System.out.print("Error de conexion de audio ");
					}
					catch(IOException e)
					{
						System.out.print("IOException esta molestando");
					}
					catch(UnsupportedAudioFileException UNe)
					{
						System.out.print("La musica no es compatible");
					}
					
					comodin--;
					return 3;
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
