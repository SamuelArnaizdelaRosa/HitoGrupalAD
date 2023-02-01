package hitoGrupal.WaterMelon;

import java.io.Serializable;
import java.util.Date;

public class Llamada implements Serializable{
	
	private String motivoLlamada;
	private String problema;
	private boolean reparacionFisica;
	private boolean solucionado;
	
	public Llamada(String motivoLlamada, String problema, boolean reparacionFisica, boolean solucionado) {
		this.motivoLlamada = motivoLlamada;
		this.problema = problema;
		this.reparacionFisica = reparacionFisica;
		this.solucionado = solucionado;
	}

	
	public String getMotivoLlamada() {
		return motivoLlamada;
	}

	public void setMotivoLlamada(String motivoLlamada) {
		this.motivoLlamada = motivoLlamada;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public boolean isReparacionFisica() {
		return reparacionFisica;
	}

	public void setReparacionFisica(boolean reparacionFisica) {
		this.reparacionFisica = reparacionFisica;
	}

	public boolean isSolucionado() {
		return solucionado;
	}

	public void setSolucionado(boolean solucionado) {
		this.solucionado = solucionado;
	}
	
	

}
