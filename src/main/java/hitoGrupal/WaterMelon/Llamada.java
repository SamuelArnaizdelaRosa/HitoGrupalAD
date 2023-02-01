package hitoGrupal.WaterMelon;

import java.io.Serializable;
import java.util.Date;

public class Llamada implements Serializable{
	
	private Date fecha;
	private String motivoLlamada;
	private String problema;
	private boolean reparacionFisica;
	private boolean solucionado;
	
	public Llamada(Date fecha, String motivoLlamada, String problema, boolean reparacionFisica, boolean solucionado) {
		this.fecha = fecha;
		this.motivoLlamada = motivoLlamada;
		this.problema = problema;
		this.reparacionFisica = reparacionFisica;
		this.solucionado = solucionado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
