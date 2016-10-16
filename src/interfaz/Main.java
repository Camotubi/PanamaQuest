package interfaz;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import entidades.Jugador;
import entidades.Preguntador;
import entidades.Jugador;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugador = new Jugador();
		Preguntador preg = new Preguntador();
		
		int numJugadores;
		int controlRespuesta =-1;
		int contadorPreguntas=0;
		int turno;
		int resp;
		
		/*
		 * -faltan respuestas para cuando salga pregunta
			-faltan poner los comodines     done
			- mensaje para ganadores
			- cosas que se que se me estan olvidando
		 * 
		 */
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
		
		// turno = (i+1)%numJugadores
		do {
			// que debo poner en respuestas ._.
			int i =0; 			
			turno = (i+1)%numJugadores;
			Jugador usarComodines = jugadores.get(turno);
			if(usarComodines.getComodines()!=0 )
			{
				int tempcom =Integer.parseInt(JOptionPane.showInputDialog(null,"Desea usar un comodin ? \n1-si \n2- no"));
				switch(tempcom)
				{
				case 1: // hay que reducir las respuestas a 2, la correcta y una que no sea correcta
					usarComodines.setComodines(1);
					resp = Integer.parseInt(JOptionPane.showInputDialog(null,"Turno del jugador "+turno+"\nLa pregunta es :"+jugador.getPreguntaRecibida()+"\n Respuesta:\n1- \n2-  \n0-Retirarse"));
					contadorPreguntas++;
					controlRespuesta = jugador.responderPregunta(resp);
					break;
				case 2:
					resp = Integer.parseInt(JOptionPane.showInputDialog(null,"Turno del jugador "+turno+"\nLa pregunta es :"+jugador.getPreguntaRecibida()+"\n Respuesta:\n1- \n2- \n3- \n4- \n0-Retirarse"));
					contadorPreguntas++;
					controlRespuesta = jugador.responderPregunta(resp);
					break;
				}
				
			}
			
			else {
				resp = Integer.parseInt(JOptionPane.showInputDialog(null,"Turno del jugador "+turno+"\nLa pregunta es :"+jugador.getPreguntaRecibida()+"\n Respuesta:\n1- \n2- \n3- \n4- \n0-Retirarse"));
				contadorPreguntas++;
				controlRespuesta = jugador.responderPregunta(resp);
				}
				switch(controlRespuesta)
				{
				case 1: JOptionPane.showMessageDialog(null, "Felicidades jugador "+turno+"la respuesta ha sido correcta");	 break;
				case 2: JOptionPane.showMessageDialog(null, "jugador "+turno+"su respuesta ha sido incorrecta");	 break;
				case 0: Jugador temph = jugadores.get(turno);
				JOptionPane.showMessageDialog(null, "El jugador "+temph.getNombre()+" se retiro \n Pregunta en la que se retiro:"+contadorPreguntas+"\n dinero acumulado fue :"+temph.getDinero());
				break;

				}
			
		}while(contadorPreguntas <10 && controlRespuesta !=0 );
		
		
		/* prueba de como imprime
		for(int i =0; i<numJugadores;i++)
		{  
			Jugador temph = jugadores.get(i);
			System.out.println(temph.getNombre());
			
		}
		*/
		
		
	}

}
