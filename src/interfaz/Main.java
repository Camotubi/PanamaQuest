package interfaz;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import entidades.Jugador;
import entidades.Pregunta;
import entidades.Preguntador;

public class Main {
	
	/*Cosas por hacer
	 * evitar que las preguntas se repitan 
	 * arreglar el mensaje de pregunta correcta
	 * agregar pregunta de audio
	 * 
	 * */
	

	public static void main(String[] args)   {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugadorAct ;
		Preguntador preg = cargarPreguntas(new Preguntador());
		ImageIcon eldojo = new ImageIcon(Main.class.getClassLoader().getResource("Img/2.jpg"));
		int cantJugador=0;
		int controlRespuesta =-1;
		int contadorPreguntas=0;
		int turno=0;
		int resp = 0;
		
		

		int categoriaAct = 1; // cat 1 = geografia, cat2 = historia, cat 3 = cultura		
		String categoriaTextual = null;
		boolean UsoComodin; //control sobre los comodines para mostrar opciones
		boolean jugadoresDisponibles = true;
		boolean goodInput=false;
		int ctrlJugadoresRetidaros = 0;
		JOptionPane.showMessageDialog(null, "PanamaQuest 1.0");
		do{
			try{
			cantJugador = Integer.parseInt(JOptionPane.showInputDialog(null,"Cuantos jugadores jugaran esta vez?"));
			goodInput=true;
			}
			catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Inserte un nï¿½mero porfavor.");
			}
		}while(!goodInput);
	
		for(int i =0; i<cantJugador ;i++)
		{
			jugadores.add(new Jugador(JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1))));
		}
		for(int i=0; i<jugadores.size(); i++){
			JOptionPane.showMessageDialog(null, "Jugador"+(i+1)+":\n\nNombre: "+jugadores.get(i).getNombre()+"\n\nDinero: "+jugadores.get(i).getDinero()+"\n\nPreguntas Resueltas: "+jugadores.get(i).getcontPregunta());
			}

		preg.cambiarCategoria(1);
		JOptionPane.showMessageDialog(null,"Categoria actual : Geografia");
		do {
			UsoComodin =false;  // inicializacion de los usos de comodin
			if((contadorPreguntas%10==0 && contadorPreguntas !=0))
			{
				preg.cambiarCategoria(++categoriaAct);
				switch(categoriaAct)
				{
				case 1 : 
					categoriaTextual = "Geografia";
					break;
				case 2:
					categoriaTextual = "Historia";
					break;
				case 3:
					categoriaTextual = "Cultura";
					break;
				}
				JOptionPane.showMessageDialog(null,categoriaTextual);
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
					if(ctrlJugadoresRetidaros>= jugadores.size()) jugadoresDisponibles = false;

				}
			}while(jugadorAct.isRetirado() && jugadoresDisponibles);
			if(jugadoresDisponibles)
			{
				jugadorAct.setPreguntaRecibida(preg.preguntar());
				//resp = Integer.parseInt((String) JOptionPane.showInputDialog(null,stringPregunta(jugadorAct,contadorPreguntas,UsoComodin),"",JOptionPane.PLAIN_MESSAGE,icon,null,null));

				resp = mostrarPantallaPregunta(jugadorAct, contadorPreguntas+1, UsoComodin);

				if(resp == -1) break;

				controlRespuesta = jugadorAct.responderPregunta(resp);


				if(resp == 10 && jugadorAct.getComodin()>=0) // alternativa si decide usar el   comodin 
				{
					JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+ " te quedan  " + jugadorAct.getComodin()+ " comodin restante.");
					UsoComodin = true;
					resp = mostrarPantallaPregunta(jugadorAct,contadorPreguntas+1,UsoComodin);
				}
				controlRespuesta = jugadorAct.responderPregunta(resp);
				try {
				Clip sonido = AudioSystem.getClip(); // test de sonido
				switch(controlRespuesta)
				{

				case 1:
				int contvic=0; // contador de victorias for memes reasons
				if(contvic ==3)
				{
					AudioInputStream Audio3vic = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/3goods.wav"));
					sonido.open(Audio3vic);
					sonido.start();
				}
				AudioInputStream Audioac = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/Acierto.wav"));
				sonido.open(Audioac);
				sonido.start();
				JOptionPane.showMessageDialog(null,"Felicidades " + jugadorAct.getNombre()+"la respuesta ha sido correcta", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
				contvic++;
				break;
				case 2: 
				AudioInputStream Audiofall = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/Perdio.wav"));
				sonido.open(Audiofall);
				sonido.start();
				JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+"su respuesta ha sido incorrecta");
				break;
				case 0: Jugador temph = jugadores.get(turno);
				temph.setRetirado(true);
				AudioInputStream Audioret = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/retiro.wav"));
				sonido.open(Audioret);
				sonido.start();
				JOptionPane.showMessageDialog(null, "El jugador "+temph.getNombre()+" se retiro \n Pregunta en la que se retiro:"+contadorPreguntas+"\n dinero acumulado fue :"+temph.getDinero());
				break;
	
				}
				}catch(LineUnavailableException Aude)
				{
					System.out.print("Error de conexion de audio ");
				}
				catch(IOException e)
				{
					System.out.print("IOException esta molestando");
				}
				catch(UnsupportedAudioFileException UNe)
				{
					System.out.print("La musica no es compatible");
				}
				
				
				contadorPreguntas++;
			}

		}while((categoriaAct<3 || contadorPreguntas%10!=0) &&jugadoresDisponibles);

		System.out.println("termine");
		boolean termine = true;
		
		for(int i=0; i<jugadores.size(); i++){
			if(termine == true) {
			try {
				Clip sonido = AudioSystem.getClip();
				AudioInputStream Audiofinal = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/final.wav"));
				sonido.open(Audiofinal);
				sonido.start();
				}catch(LineUnavailableException Aude)
				{
					System.out.print("Error de conexion de audio ");
				}
				catch(IOException e)
				{
					System.out.print("IOException esta molestando");
				}
				catch(UnsupportedAudioFileException UNe)
				{
					System.out.print("La musica no es compatible");
				}
			}
			termine = false;
			JOptionPane.showMessageDialog(null, "Jugador "+(i+1)+"\n\nNombre: "+jugadores.get(i).getNombre()+"\n\nDinero: "+jugadores.get(i).getDinero()+"\n\nPreguntas Resueltas: "+jugadores.get(i).getcontPregunta());
		}
		
		JOptionPane.showMessageDialog(null, "Esto fue un trabajo coperativo del DOJO DEL SOFTWARE","Gracias",JOptionPane.PLAIN_MESSAGE,eldojo);

	}

	public static Preguntador cargarPreguntas(Preguntador preguntador)
	{	
		BufferedReader br = null;
		try {
			
			ClassLoader cl = Main.class.getClassLoader();
			String sCurrentLine;
			String tempPreg = null;
			int tempRespIndex = 0;
			int tempCat=1;
			String tempDirImg="";
			String tempDirAudio="";
			ArrayList<String> tempOps = new ArrayList<String>();
			br = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("Preguntas_Cargar.txt")));

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
				if((sCurrentLine.charAt(0)=='&'))
				{
					tempDirImg = sCurrentLine.substring(1);
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
					preguntador.agregarPregunta(tempPreg,new ArrayList<String>(tempOps),tempRespIndex,tempCat,tempDirImg,tempDirAudio);
					tempOps.clear();
					tempDirImg ="";
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
			strBuild.append("\n-1-Salir del juego");
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
			strBuild.append("\n-1-Salir del juego");
		}
			return strBuild.toString();
	}
	public static Integer mostrarPantallaPregunta(Jugador jugador,int contador,boolean usoComo){
		Integer a=0, b=0;
		boolean goodInput = false;
		ImageIcon icon;
		if(jugador.getPreguntaRecibida().getDirImagen() != "")
		{
			System.out.println(jugador.getPreguntaRecibida().getDirImagen());
			icon = new ImageIcon(Main.class.getClassLoader().getResource(jugador.getPreguntaRecibida().getDirImagen()));
			do{
				try{
					a = Integer.parseInt((String) JOptionPane.showInputDialog(null,stringPregunta(jugador,contador,usoComo),"",JOptionPane.PLAIN_MESSAGE,icon,null,null));
					goodInput=true;
					}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Inserte un número porfavor.");
					}
				}while(!goodInput);
			return a;
		}
		else{
			do{
				try{
					b = Integer.parseInt(JOptionPane.showInputDialog(null,stringPregunta(jugador,contador,usoComo)));
					goodInput=true;
					}
					catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Inserte un número porfavor.");
					}
				}while(!goodInput);
			return b;
			} 
	}
}