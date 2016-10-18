package entidades;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Preguntador {
	private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	private ArrayList<Pregunta> preguntaCatAct = new ArrayList<Pregunta>();
	private ArrayList<Integer> preguntasHechas = new ArrayList<Integer>();
	public void agregarPregunta(String pregunta, ArrayList<String> opciones,int respuesta,int categoria,String dirImg,String dirAudio)
	{
		
		getPreguntas().add(new Pregunta(pregunta,opciones,respuesta,categoria,dirImg,dirAudio));
	}
	
	public void cambiarCategoria(int categoria)//extrae todas las preguntas
	{
		preguntaCatAct.clear();
		preguntasHechas.clear();
		for(int i = 0; i<getPreguntas().size();i++)
		{
			if(getPreguntas().get(i).getCategoria() == categoria)
			{
				preguntaCatAct.add(getPreguntas().get(i));

			}
		}
		
	}
	
	public Pregunta preguntar()
	{
		Random generator = new Random();
		int numeroRand ;
		boolean pregRepetida = false;
		do
		{
			numeroRand = generator.nextInt(preguntaCatAct.size());
			for(int i=0; i<preguntaCatAct.size() && i<preguntasHechas.size();i++)
			{
				
				if(numeroRand == preguntasHechas.get(i))
				{
					pregRepetida = true;
				}
				else pregRepetida = false;
			}
		}while(pregRepetida);
		preguntasHechas.add(numeroRand);
		return preguntaCatAct.get(numeroRand);
	}

	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
