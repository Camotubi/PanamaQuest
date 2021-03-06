package interfaz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import entidades.Jugador;
import entidades.Preguntador;

public class Main {
	

	

	public static void main(String[] args)   {
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugadorAct;
		Preguntador preg = cargarPreguntas(new Preguntador());
		ImageIcon eldojo = new ImageIcon(Main.class.getClassLoader().getResource("Img/2.jpg"));
		int cantJugador=0;
		int controlRespuesta =-1;//utilizada para saber si el jugador tuvo buena o mala la respuesta
		int contadorPreguntas=0; 
		int turno= 0; //dicta que jugador le toca
		int resp = 0; // recibe respuesta de los jugadores
		int contvic=0; // contador de victorias for memes reasons

		int categoriaAct = 1; // cat 1 = geografia, cat2 = historia, cat 3 = cultura		
		String[] categoriaTextual = {"Geografia","Historia","Cultura"}; //Utilizado para mostrar la Categoria Actual
		boolean UsoComodin = false; //control sobre los comodines para mostrar opciones
		boolean jugadoresDisponibles = true;
		boolean goodInput=false; //Utilizada para controlar si el usuario introdujo algo esperado ( por ejemplo un numero en vez de letra)
		int contadorlJugadoresRetidaros = 0;
		String entradaTeclado = null;
		JOptionPane.showMessageDialog(null, "PanamaQuest 1.0");
		do{
			try{
				entradaTeclado = JOptionPane.showInputDialog(null,"Cuantos jugadores jugaran esta vez?");
				cantJugador = Integer.parseInt(entradaTeclado);
				goodInput=true;
			}
			catch(NumberFormatException nfe){
				if(entradaTeclado == null)
				{
					JOptionPane.showMessageDialog(null,"Adios","Cerrando....." , JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}else JOptionPane.showMessageDialog(null, "Inserte un n�mero porfavor.");
			}

		}while(!goodInput);
	
		for(int i =0; i<cantJugador ;i++)
		{
			jugadores.add(new Jugador(JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador "+(i+1))));
		}
		for(int i=0; i<jugadores.size(); i++){
			JOptionPane.showMessageDialog(null, "Jugador "+(i+1)+"\n\nNombre: "+jugadores.get(i).getNombre()+"\n\nDinero: "+jugadores.get(i).getDinero(),"Informacion del jugador",JOptionPane.INFORMATION_MESSAGE);
			}

		preg.cambiarCategoria(1);
		JOptionPane.showMessageDialog(null,categoriaTextual[categoriaAct-1],"Categoria Actual",JOptionPane.INFORMATION_MESSAGE);
		do {
			if((contadorPreguntas%10==0 && contadorPreguntas !=0))
			{
				preg.cambiarCategoria(++categoriaAct);
				JOptionPane.showMessageDialog(null,categoriaTextual[categoriaAct-1],"Categoria Actual",JOptionPane.INFORMATION_MESSAGE);
			}
			contadorlJugadoresRetidaros = 0;
			do
			{
				
				turno = (turno+1)%cantJugador;
				jugadorAct = jugadores.get(turno);
				if(jugadorAct.isRetirado()) 
				{
					contadorlJugadoresRetidaros++;
					if(contadorlJugadoresRetidaros>= jugadores.size()) jugadoresDisponibles = false;

				}
			}while(jugadorAct.isRetirado() && jugadoresDisponibles);
			if(jugadoresDisponibles)
			{
				jugadorAct.setPreguntaRecibida(preg.preguntar());

				resp = mostrarPantallaPregunta(jugadorAct, contadorPreguntas+1, UsoComodin);
				if(resp == -1) break;//Si introduce -1 se termina el juego
				controlRespuesta = jugadorAct.responderPregunta(resp);
				if(resp == 10 && jugadorAct.getComodin()>=0) // alternativa si decide usar el   comodin 
				{
					JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+ ", te quedan  " + jugadorAct.getComodin()+ " comodin(es) restante(s).");
					UsoComodin = true;
					resp = mostrarPantallaPregunta(jugadorAct,contadorPreguntas+1,UsoComodin);
					controlRespuesta = jugadorAct.responderPregunta(resp);
				}

				try {
				Clip sonido = AudioSystem.getClip(); // test de sonido

				switch(controlRespuesta)
				{

				case 1://Caso de respuesta correcta

				contvic++;
				if(contvic ==3)
				{
					AudioInputStream Audio3vic = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/3goods.wav"));
					sonido.open(Audio3vic);
					sonido.start();
					JOptionPane.showMessageDialog(null,"Felicidades " + jugadorAct.getNombre()+", la respuesta ha sido correcta", "Mensaje",JOptionPane.INFORMATION_MESSAGE);

				}
				else {
				AudioInputStream Audioac = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/Acierto.wav"));
				sonido.open(Audioac);
				sonido.start();
				

				JOptionPane.showMessageDialog(null,"Felicidades " + jugadorAct.getNombre()+", la respuesta ha sido correcta", "Mensaje",JOptionPane.INFORMATION_MESSAGE);


				}
				break;
				case 2: //Caso de Respuesta incorrecta
				contvic=0;
				AudioInputStream Audiofall = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/Perdio.wav"));
				sonido.open(Audiofall);
				sonido.start();
				JOptionPane.showMessageDialog(null, jugadorAct.getNombre()+", su respuesta ha sido incorrecta");
				break;
				case 0://Caso de retirarse del juego
				Jugador temph = jugadores.get(turno);
				temph.setRetirado(true);
				AudioInputStream Audioret = AudioSystem.getAudioInputStream(Main.class.getClassLoader().getResource("audio/retiro.wav"));
				sonido.open(Audioret);
				sonido.start();
				JOptionPane.showMessageDialog(null, "El jugador "+temph.getNombre()+" se retiro. \n Pregunta en la que se retiro: "+contadorPreguntas+"\n Dinero acumulado: "+temph.getDinero());
				break;
				default :
				System.out.print("Opcion invalida");
				break;

	
				}
				}catch(LineUnavailableException Aude)
				{
					System.out.print("Error de conexion de audio");
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

		
		
		for(int i=0; i<jugadores.size(); i++){//Pantalla de el dinero final de los jugadores
			if(i == 0) {
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
			JOptionPane.showMessageDialog(null, "Jugador "+(i+1)+"\n\nNombre: "+jugadores.get(i).getNombre()+"\n\nDinero: "+jugadores.get(i).getDinero());
		}
		
		JOptionPane.showMessageDialog(null, "Esto fue un trabajo coperativo del DOJO DEL SOFTWARE","Gracias",JOptionPane.PLAIN_MESSAGE,eldojo);

	}

	public static Preguntador cargarPreguntas(Preguntador preguntador)//Carga las preguntas desde una archivo
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
	public static String stringPregunta(Jugador jugador,int contador,boolean usoComo)//Genera el string que se mostrar en la pantalla de preguntas
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
	
	
	public static Integer mostrarPantallaPregunta(Jugador jugador,int contador,boolean usoComo){//Muestra la pregunta, las posibles opciones y la imagen si la tiene de la pregunta
		Integer retorno=0;
		boolean goodInput = false;
		String entradaTeclado = null;
		ImageIcon icon;
		if(jugador.getPreguntaRecibida().getDirImagen() != "")
		{
			icon = new ImageIcon(Main.class.getClassLoader().getResource(jugador.getPreguntaRecibida().getDirImagen()));
			do{
				try{
					entradaTeclado =(String) JOptionPane.showInputDialog(null,stringPregunta(jugador,contador,usoComo),"",JOptionPane.PLAIN_MESSAGE,icon, null,"") ;
					retorno = Integer.parseInt(entradaTeclado);
					goodInput=true;
					}
				catch(NumberFormatException nfe){
					if(entradaTeclado == null )
					{
						JOptionPane.showMessageDialog(null, "Adios","Cerrando....." , JOptionPane.PLAIN_MESSAGE);
						System.exit(0);
					}else JOptionPane.showMessageDialog(null, "Inserte un n�mero porfavor.");

					}
				}while(!goodInput);
			return retorno;
		}
		else{
			do{
				try{
					entradaTeclado=JOptionPane.showInputDialog(null,stringPregunta(jugador,contador,usoComo));
					 retorno= Integer.parseInt(entradaTeclado);
					goodInput=true;
					}
					catch(NumberFormatException nfe){

						if(entradaTeclado == null  )
						{
							JOptionPane.showMessageDialog(null, "Adios","Cerrando....." , JOptionPane.PLAIN_MESSAGE);
							System.exit(0);
							
						}else JOptionPane.showMessageDialog(null, "Inserte un n�mero porfavor.");

					}
				}while(!goodInput);
			return retorno;
			} 
	}
}