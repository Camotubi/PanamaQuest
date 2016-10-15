package entidades;


import java.util.ArrayList;
import java.util.Arrays;

public class Preguntador {
	private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	private ArrayList<Pregunta> preguntadeCategoriaActual = new ArrayList<Pregunta>();
	private void agregarPregunta(String pregunta, String opciones[],int respuesta,int categoria )
	{
		preguntas.add(new Pregunta(pregunta,new ArrayList<String>(Arrays.asList(opciones)),respuesta,categoria));
	}
	
	private void cambiarCategoria(int categoria)//extrae todas las preguntas
	{
		preguntadeCategoriaActual.clear();
		for(int i = 0; i<preguntas.size();i++)
		{
			if(preguntas.get(i).getCategoria() == categoria)
			{
				preguntadeCategoriaActual.add(preguntas.get(i));
			}
		}
	}
}
