package interfaz;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import entidades.Jugador;

import entidades.Jugador;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
		
		int numJugadores;
		
		
		JOptionPane.showMessageDialog(null, "PanamaQuest 1.0");
		
		String temp=JOptionPane.showInputDialog(null,"Cuantos jugadores jugaran esta vez?");
		numJugadores = Integer.parseInt(temp);
		
		// JOptionPane.showInputDialog(null,"");
		// JOptionPane.showMessageDialog(null, "");
		
		
		for(int i =0; i<numJugadores ;i++)
		{
		 String tempnom =(JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1)));
			jugadores.add(new Jugador(tempnom));
		}
		
		/* prueba de como imprime
		for(int i =0; i<numJugadores;i++)
		{  
			Jugador temph = jugadores.get(i);
			System.out.println(temph.getNombre());
			
		}
		*/
		
		
	}

}
