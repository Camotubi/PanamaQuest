package interfaz;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		  jugadores.add(i,JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1)));
			
		}
		
		System.out.println(jugadores);
		
		
		
	}

}
