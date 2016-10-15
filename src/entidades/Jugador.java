package entidades;

public class Jugador {
	
	
	
	private double dinero;
	private String nombre;
	private int comodines;  // 2 comodines por jugador
	
	
	public Jugador(String nom)
	{
		nombre = nom;
		comodines = 2;
	}
	
	
	
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
