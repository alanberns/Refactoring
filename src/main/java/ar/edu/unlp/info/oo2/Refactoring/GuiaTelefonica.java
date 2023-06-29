package ar.edu.unlp.info.oo2.Refactoring;

import java.util.TreeSet;
import java.util.SortedSet;

public class GuiaTelefonica {
	private SortedSet<String> numerosTelefonicos = new TreeSet<String>();
	
	public SortedSet<String> getNumerosTelefonicos() {
		return this.numerosTelefonicos;
	}
	public void addNumeroTelefonico(String telefono) {
		this.numerosTelefonicos.add(telefono);
	}
	public void removeNumeroTelefonico(String telefono) {
		this.numerosTelefonicos.remove(telefono);
	}
}
