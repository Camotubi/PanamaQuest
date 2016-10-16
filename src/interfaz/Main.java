package interfaz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		cargarPreguntas(preg);
		int numJugadores;
		int controlRespuesta =-1;
		int contadorPreguntas=0;
		int turno;
		
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
			preg.cambiarCategoria(1);
			System.out.println("lala"+preg.getPreguntas().get(1).getPregunta());
			jugadores.get(turno).setPreguntaRecibida(preg.getPreguntas().get(1));
			int resp = Integer.parseInt(JOptionPane.showInputDialog(null,"Turno del jugador "+turno+"\nLa pregunta es :"+jugadores.get(turno).getPreguntaRecibida().getPregunta()+"\n Respuesta:\n1-"+jugadores.get(turno).getPreguntaRecibida().getOpciones().get(0)+" \n2- \n3- \n4- \n0-Retirarse"));
			contadorPreguntas++;
			controlRespuesta = jugador.responderPregunta(resp);
			switch(controlRespuesta)
			{
			case 1: JOptionPane.showMessageDialog(null, "Felicidades jugador "+turno+"la respuesta ha sido correcta");	 break;
			case 2: JOptionPane.showMessageDialog(null, "Felicidades jugador "+turno+"la respuesta ha sido correcta");	 break;
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

	public static void cargarPreguntas(Preguntador preguntador)
	{
		
		BufferedReader br = null;

		try {

			String sCurrentLine;
			String tempPreg = null;
			int tempRespIndex = 0;
			int tempCat=0;
			ArrayList<String> tempResp = new ArrayList<String>();
			br = new BufferedReader(new FileReader("C:\\Users\\Public\\test.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.charAt(0)=='/')
				{
					tempCat++;
				}
				System.out.println(sCurrentLine);
				if((sCurrentLine.charAt(0)=='+'))
				{
					tempPreg = sCurrentLine.substring(1);
				}
						
				if(sCurrentLine.charAt(0) == '-')
				{
					tempResp.add(sCurrentLine.substring(1));
				}
				else if(sCurrentLine.charAt(0) == '*')
				{
					tempResp.add(sCurrentLine.substring(1));
					tempRespIndex =tempResp.size()-1;
				}
				if(sCurrentLine.charAt(0) == '=')
				{
					preguntador.agregarPregunta(tempPreg,tempResp,tempRespIndex,tempCat,"","");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
		}
	}
}
