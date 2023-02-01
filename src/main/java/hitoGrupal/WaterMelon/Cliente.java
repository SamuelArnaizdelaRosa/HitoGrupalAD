package hitoGrupal.WaterMelon;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{

	private String nombre;
	private String apellidos;
	private int telefono;
	private ArrayList<Llamada> llamadas;
	
	public Cliente(String nombre, String apellidos, int telefono) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.llamadas = new ArrayList<Llamada>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public void setLlamadas(ArrayList<Llamada> llamadas) {
		this.llamadas = llamadas;
	}
	
	public ArrayList<Llamada> getLlamadas() {
		return llamadas;
	}
	
	public void addLlamada(Llamada l) {
		this.llamadas.add(l);
	}
}
