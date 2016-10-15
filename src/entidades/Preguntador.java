package entidades;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Preguntador {
	private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	private ArrayList<Pregunta> preguntadeCategoriaActual = new ArrayList<Pregunta>();
	private ArrayList<Integer> preguntasHechas = new ArrayList<Integer>();
	private void agregarPregunta(String pregunta, String opciones[],int respuesta,int categoria )
	{
		preguntas.add(new Pregunta(pregunta,new ArrayList<String>(Arrays.asList(opciones)),respuesta,categoria));
	}
	
	private void cambiarCategoria(int categoria)//extrae todas las preguntas
	{
		preguntadeCategoriaActual.clear();
		preguntasHechas.clear();
		for(int i = 0; i<preguntas.size();i++)
		{
			if(preguntas.get(i).getCategoria() == categoria)
			{
				preguntadeCategoriaActual.add(preguntas.get(i));
			}
		}
	}
	
	private Pregunta preguntar()
	{
		Random generator = new Random();
		int numeroRand ;
		boolean pregRepetida = false;
		do
		{
			numeroRand = generator.nextInt(preguntadeCategoriaActual.size());
			for(int i=0; i<preguntadeCategoriaActual.size() && !pregRepetida;i++)
			{
				
				if(numeroRand == preguntasHechas.get(i))
				{
					pregRepetida = true;
				}
			}
		}while(pregRepetida);
		
		return preguntadeCategoriaActual.get(numeroRand);
	}
}
