package ar.edu.unlp.info.oo2.Refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class Persoona {
	private List<Llamada> llamadas = new ArrayList<Llamada>();
	public String tipo;
	public String nombre;
	public String telefono;
	public String cuit;
	public String documento;
	public Persoonal sistema;
	
	public List<Llamada> getLlamadas() {
		return this.llamadas;
	}
	public void addLlamada(Llamada llamada) {
		this.llamadas.add(llamada);
	}
	public void removeLlamada(Llamada llamada) {
		this.llamadas.remove(llamada);
	}
	public void setLlamadas(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	

	
}
