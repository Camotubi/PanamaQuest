package entidades;

<<<<<<< HEAD
public class Preguntador
{

	
=======

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Preguntador {
	private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	private ArrayList<Pregunta> preguntadeCategoriaActual = new ArrayList<Pregunta>();
	private void agregarPregunta(String pregunta, String opciones[],int respuesta,int categoria )
	{
		
		preguntas.add(new Pregunta(pregunta,new ArrayList<String>(Arrays.asList(opciones)),respuesta,categoria));
	}
>>>>>>> 20839c263af4cacd8114606fadd453c4017789d1
}
