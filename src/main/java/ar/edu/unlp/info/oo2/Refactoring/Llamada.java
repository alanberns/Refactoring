package ar.edu.unlp.info.oo2.Refactoring;

public class Llamada {
	protected String tipoDeLlamada;
	private String emisor;
	private String remitente;
	public int duracion;
	
	public Llamada() {
		
	}
	public Llamada(String tipoLlamada, String emisor, String remitente, Persoona p3, int duracion) {
		this.tipoDeLlamada = tipoLlamada;
		this.emisor= emisor;
		this.remitente= remitente;
		this.duracion = duracion;
	}

	public String getTipoDeLlamada() {
		return tipoDeLlamada;
	}

	public void setTipoDeLlamada(String tipoDeLlamada) {
		this.tipoDeLlamada = tipoDeLlamada;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	
	
	
	
}
