package cadastro;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import controladorGRASP.ControladorDeCadastro;
import dominio.Evento;

public class ControleUsuarioHome {
	
	private Map<String, Evento> eventosDisponiveis;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	
	public ControleUsuarioHome() {
		
		eventosDisponiveis = new TreeMap<String, Evento>();
	}
	
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
	}
	
	public Collection<String> obterEventosDisponiveis() {
		
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventos();
		for (Evento evento: eventos) {
			eventosDisponiveis.put(evento.getNome(), evento);
		}
		
		return eventosDisponiveis.keySet();
	}

}
