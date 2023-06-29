package ar.edu.unlp.info.oo2.Refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Persoonal {
	private List<Persoona> usuarios = new ArrayList<Persoona>();
	private List<Llamada> llamadas = new ArrayList<Llamada>();
	GuiaTelefonica guiaTelefonica = new GuiaTelefonica();
	static double DESCUENTO_PERSONA_JURIDICA = 0.15;
	static double DESCUENTO_PERSONA_FISICA = 0;
	
	
	public List<Llamada> getLlamadas() {
		return this.llamadas;
	}
	public void addLlamada(Llamada llamada) {
		this.llamadas.add(llamada);
	}
	public void removeLlamada(Llamada llamada) {
		this.llamadas.remove(llamada);
	}
	public List<Persoona> getUsuarios() {
		return this.usuarios;
	}
	public void addUsuario(Persoona usuario) {
		this.usuarios.add(usuario);
	}
	public void removeUsuario(Persoona usuario) {
		this.usuarios.remove(usuario);
	}
	
	public boolean agregarTelefono(String telefono) {
		boolean encontre = guiaTelefonica.getNumerosTelefonicos().contains(telefono);
		if (!encontre) {
			guiaTelefonica.addNumeroTelefonico(telefono);
			encontre= true;
			return encontre;
		}
		else {
			encontre= false;
			return encontre;
		}
	}
	
	public Persoona registrarUsuario(String data, String nombre, String tipo) {
		Persoona usuario = new Persoona();
		if (tipo.equals("fisica")) {
			usuario.setNombre(nombre);
			String telefono = guiaTelefonica.getNumerosTelefonicos().last();
			guiaTelefonica.removeNumeroTelefonico(telefono);
			usuario.setTipo(tipo);
			usuario.setTelefono(telefono);
			usuario.setDocumento(data);
		}
		else if (tipo.equals("juridica")) {
			String telefono = guiaTelefonica.getNumerosTelefonicos().last();
			guiaTelefonica.removeNumeroTelefonico(telefono);
			usuario.nombre =nombre;
			usuario.tipo =tipo;
			usuario.telefono = telefono;
			usuario.cuit =data;
		}
		usuario.sistema = this;
		this.addUsuario(usuario);
		return usuario;
		
	}
	
	public boolean eliminarUsuario(Persoona usuario) {
		List<Persoona> listaUsuariosActualizada = usuario.sistema.usuarios.stream().filter(persona -> persona != usuario).collect(Collectors.toList());
		boolean borre = false;
		if (listaUsuariosActualizada.size() < usuarios.size()) {
			this.usuarios = listaUsuariosActualizada;
			this.guiaTelefonica.addNumeroTelefonico(usuario.getTelefono());
			borre = true;
		}
		return borre;
		
	}
	
	public Llamada registrarLlamada(Persoona usuarioEmisor, Persoona usuarioRemitente, String tipoDeLlamada, int duracion) {
		Llamada llamada = new Llamada();
		llamada.tipoDeLlamada = tipoDeLlamada;
		llamada.setEmisor(usuarioEmisor.telefono);
		llamada.setRemitente(usuarioRemitente.getTelefono());
		llamada.duracion= duracion;
		this.addLlamada(llamada);
		usuarioEmisor.addLlamada(llamada);
		return llamada;
		
	}
	
	public double calcularMontoTotalLlamadas(Persoona usuario) {
		double monto = 0;
		Persoona usuarioRegistrado = null;
		for (Persoona usuarioActual : usuarios) {
			if (usuarioActual.telefono == usuario.getTelefono()) {
				usuarioRegistrado = usuarioActual;
				break;
			}
		} if (usuarioRegistrado == null) return monto;
		if (usuarioRegistrado != null) {
			for (Llamada llamada : usuarioRegistrado.getLlamadas()) {
				double montoParcial = 0;
				if (llamada.tipoDeLlamada == "nacional") {
					montoParcial += llamada.duracion *3 + (llamada.duracion*3*0.21);
				} else if (llamada.tipoDeLlamada == "internacional") {
					montoParcial += llamada.duracion *200 + (llamada.duracion*200*0.21);
				}
				
				if (usuarioRegistrado.tipo == "fisica") {
					montoParcial -= montoParcial*DESCUENTO_PERSONA_FISICA;
				} else if(usuarioRegistrado.tipo == "juridica") {
					montoParcial -= montoParcial*DESCUENTO_PERSONA_JURIDICA;
				}
				monto += montoParcial;
			}
		}
		return monto;
	}

	public int cantidadDeUsuarios() {
		return usuarios.size();
	}

	public boolean existeUsuario(Persoona usuario) {
		return usuarios.contains(usuario);
	}
	
}
