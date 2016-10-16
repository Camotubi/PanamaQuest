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

	public static void main(String[] args) {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugadorAct = new Jugador();
		Preguntador preg = cargarPreguntas(new Preguntador());
		int cantJugador;
		int controlRespuesta =-1;
		int contadorPreguntas=1;
		int turno=0;
		int resp = 0;
		int categoriaAct = 1;
		JOptionPane.showMessageDialog(null, "PanamaQuest 1.0");
		cantJugador = Integer.parseInt(JOptionPane.showInputDialog(null,"Cuantos jugadores jugaran esta vez?"));
		
	
		for(int i =0; i<cantJugador ;i++)
		{
			jugadores.add(new Jugador(JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1))));
		}
		preg.cambiarCategoria(1);
		do {
			if(contadorPreguntas%10==0)
			{
				preg.cambiarCategoria(++categoriaAct);
				JOptionPane.showMessageDialog(null,categoriaAct);
			}
			do
			{
				
				turno = (turno+1)%cantJugador;
				jugadorAct = jugadores.get(turno);
			}while(jugadorAct.isRetirado());
			jugadorAct.setPreguntaRecibida(preg.preguntar());
			resp = Integer.parseInt(JOptionPane.showInputDialog(null,stringPregunta(jugadorAct,contadorPreguntas)));
			contadorPreguntas++;
			controlRespuesta = jugadorAct.responderPregunta(resp);
			switch(controlRespuesta)
			{
			case 1: JOptionPane.showMessageDialog(null, "Felicidades " + jugadorAct.getNombre()+"la respuesta ha sido correcta");break;
			case 2: JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+"su respuesta ha sido incorrecta");break;
			case 3: JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+ " usó un comodin, le quedan " + jugadorAct.getComodin());break;
			case 0: Jugador temph = jugadores.get(turno);
			JOptionPane.showMessageDialog(null, "El jugador "+temph.getNombre()+" se retiro \n Pregunta en la que se retiro:"+contadorPreguntas+"\n dinero acumulado fue :"+temph.getDinero());
			break;

			}
			
		}while((categoriaAct<3 || contadorPreguntas%10!=0) );
		System.out.println("termine");
	}

	public static Preguntador cargarPreguntas(Preguntador preguntador)
	{	
		BufferedReader br = null;
		try {
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
	public static String stringPregunta(Jugador jugador,int contador)
	{
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("Turno de "+jugador.getNombre()+"         Dinero:" + jugador.getDinero()+"\nPregunta #"+contador+":" +jugador.getPreguntaRecibida().getPregunta()+"\n Opciones:");
		
		for(int i=0;i<jugador.getPreguntaRecibida().getOpciones().size();i++)
		{
			strBuild.append("\n"+(i+1)+"-"+jugador.getPreguntaRecibida().getOpciones().get(i));
		}
		if(jugador.getComodin()>0)
		{
			strBuild.append("\n10-Usar comodin");
		}
		strBuild.append("\n0-Retirarse");
		return strBuild.toString();
	}
}
