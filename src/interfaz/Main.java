package interfaz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import entidades.Jugador;
import entidades.Pregunta;
import entidades.Preguntador;
import entidades.Jugador;

public class Main {
	
	/*Cosas por hacer
	 * Cuando los jugadores se retiran, no se retiran en verdad siguen saliendo en el juego ** WIP
	 * Hacer que aparescan 2 preguntas cuando se usa el comodin  ** done by a good boi
	 * 
	 * */
	

	public static void main(String[] args) {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugadorAct ;
		Preguntador preg = cargarPreguntas(new Preguntador());
		int cantJugador;
		int controlRespuesta =-1;
		int contadorPreguntas=1;
		int turno=0;
		int resp = 0;
		int categoriaAct = 1;
		boolean UsoComodin; //control sobre los comodines para mostrar opciones
		boolean jugadoresDisponibles = true;
		int ctrlJugadoresRetidaros = 0;
		JOptionPane.showMessageDialog(null, "PanamaQuest 1.0");
		cantJugador = Integer.parseInt(JOptionPane.showInputDialog(null,"Cuantos jugadores jugaran esta vez?"));
		
	
		for(int i =0; i<cantJugador ;i++)
		{
			jugadores.add(new Jugador(JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1))));
		}
		preg.cambiarCategoria(1);
		do {
			UsoComodin =false;  // inicializacion de los usos de comodin
			if(contadorPreguntas%10==0)
			{
				preg.cambiarCategoria(++categoriaAct);
				JOptionPane.showMessageDialog(null,categoriaAct);
			}
			ctrlJugadoresRetidaros = 0;
			do
			{
				
				turno = (turno+1)%cantJugador;
				jugadorAct = jugadores.get(turno);
				System.out.println(ctrlJugadoresRetidaros);
				if(jugadorAct.isRetirado()) 
				{
					ctrlJugadoresRetidaros++;
					if(ctrlJugadoresRetidaros< jugadores.size()) jugadoresDisponibles = false;
				}
			}while(jugadorAct.isRetirado() && jugadoresDisponibles);
			if(jugadoresDisponibles)
			{
				jugadorAct.setPreguntaRecibida(preg.preguntar());
				resp = Integer.parseInt(JOptionPane.showInputDialog(null,stringPregunta(jugadorAct,contadorPreguntas,UsoComodin)));
				controlRespuesta = jugadorAct.responderPregunta(resp);
				if(resp == 10) // alternativa si decide usar el   comodin 
				{
					JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+ " usó un comodin, le quedan " + jugadorAct.getComodin());
					UsoComodin = true;
					resp = Integer.parseInt(JOptionPane.showInputDialog(null,stringPregunta(jugadorAct,contadorPreguntas,UsoComodin)));
				}
				controlRespuesta = jugadorAct.responderPregunta(resp);
				switch(controlRespuesta)
				{
				case 1: JOptionPane.showMessageDialog(null, "Felicidades " + jugadorAct.getNombre()+"la respuesta ha sido correcta");break;
				case 2: JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+"su respuesta ha sido incorrecta");break;
				case 3: //JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+ " usó un comodin, le quedan " + jugadorAct.getComodin());
			
				break;
				case 0: Jugador temph = jugadores.get(turno);
				temph.setRetirado(true);
				JOptionPane.showMessageDialog(null, "El jugador "+temph.getNombre()+" se retiro \n Pregunta en la que se retiro:"+contadorPreguntas+"\n dinero acumulado fue :"+temph.getDinero());
				break;
	
				}
				contadorPreguntas++;
			}
		}while((categoriaAct<3 || contadorPreguntas%10!=0) &&jugadoresDisponibles);
		System.out.println("termine");
	}

	public static Preguntador cargarPreguntas(Preguntador preguntador)
	{	
		BufferedReader br = null;
		try {
			int conttemp = 0; // pruebas para reducir opciones
			
			String sCurrentLine;
			String tempPreg = null;
			int tempRespIndex = 0;
			int tempCat=1;
			ArrayList<String> tempOps = new ArrayList<String>();
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
					tempOps.add(sCurrentLine.substring(1));
				}
				else if(sCurrentLine.charAt(0) == '*')
				{
					tempOps.add(sCurrentLine.substring(1));
					tempRespIndex =tempOps.size()-1;
				}
				if(sCurrentLine.charAt(0) == '=')
				{
					preguntador.agregarPregunta(tempPreg,new ArrayList<String>(tempOps),tempRespIndex,tempCat,"","");
					tempOps.clear();
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
		return preguntador;
	}
	public static String stringPregunta(Jugador jugador,int contador,boolean usoComo)
	{
		int contadordepreguntas=0;
		boolean respencontrada = false;
		boolean pregbasura = false;
		
		int resp=jugador.getPreguntaRecibida().getRespuesta();
		StringBuilder strBuild = new StringBuilder(); // objetos de tipo string que hacen mas facil concatenar  varios strings, aka lo que se hace aqui abajo
		strBuild.append("Turno de "+jugador.getNombre()+"         Dinero:" + jugador.getDinero()+"\nPregunta #"+contador+":" +jugador.getPreguntaRecibida().getPregunta()+"\n Opciones:");
		if(usoComo == true ) {
			
			
			for(int i=0;i<jugador.getPreguntaRecibida().getOpciones().size() ||  contadordepreguntas < 2 && respencontrada == true;i++)
			{
				
				if(jugador.getPreguntaRecibida().getOpciones().get(i) == jugador.getPreguntaRecibida().getOpciones().get(resp))
				{
					contadordepreguntas++;
					respencontrada = true;
					strBuild.append("\n"+(i+1)+"-"+jugador.getPreguntaRecibida().getOpciones().get(i));
				}
				if(jugador.getPreguntaRecibida().getOpciones().get(i) != jugador.getPreguntaRecibida().getOpciones().get(resp) && pregbasura != true)
				{
					contadordepreguntas++;
					pregbasura = true;
					strBuild.append("\n"+(i+1)+"-"+jugador.getPreguntaRecibida().getOpciones().get(i));
				}
					
				
			}
			
		
			strBuild.append("\n0-Retirarse");
		}
		else {
			for(int i=0;i<jugador.getPreguntaRecibida().getOpciones().size();i++)
			{
				strBuild.append("\n"+(i+1)+"-"+jugador.getPreguntaRecibida().getOpciones().get(i));
			}
			if(jugador.getComodin()>0)
			{
				strBuild.append("\n10-Usar comodin");
				
			}
			strBuild.append("\n0-Retirarse");
		}
			return strBuild.toString();
	}
}
