package ar.edu.unlp.info.oo2.Refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Persoonal {
	List<Persoona> usuarios = new ArrayList<Persoona>();
	List<Llamada> llamadas = new ArrayList<Llamada>();
	GuiaTelefonica guiaTelefonica = new GuiaTelefonica();
	static double DESCUENTO_PERSONA_JURIDICA = 0.15;
	static double DESCUENTO_PERSONA_FISICA = 0;
	
	public boolean agregarTelefono(String telefono) {
		boolean encontre = guiaTelefonica.numerosTelefonicos.contains(telefono);
		if (!encontre) {
			guiaTelefonica.numerosTelefonicos.add(telefono);
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
			String telefono = guiaTelefonica.numerosTelefonicos.last();
			guiaTelefonica.numerosTelefonicos.remove(telefono);
			usuario.setTipo(tipo);
			usuario.setTelefono(telefono);
			usuario.setDocumento(data);
		}
		else if (tipo.equals("juridica")) {
			String telefono = guiaTelefonica.numerosTelefonicos.last();
			guiaTelefonica.numerosTelefonicos.remove(telefono);
			usuario.nombre =nombre;
			usuario.tipo =tipo;
			usuario.telefono = telefono;
			usuario.cuit =data;
		}
		usuario.sistema = this;
		usuarios.add(usuario);
		return usuario;
		
	}
	
	public boolean eliminarUsuario(Persoona usuario) {
		List<Persoona> listaUsuariosActualizada = usuario.sistema.usuarios.stream().filter(persona -> persona != usuario).collect(Collectors.toList());
		boolean borre = false;
		if (listaUsuariosActualizada.size() < usuarios.size()) {
			this.usuarios = listaUsuariosActualizada;
			this.guiaTelefonica.numerosTelefonicos.add(usuario.getTelefono());
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
		llamadas.add(llamada);
		usuarioEmisor.llamadas.add(llamada);
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
			for (Llamada llamada : usuarioRegistrado.llamadas) {
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
