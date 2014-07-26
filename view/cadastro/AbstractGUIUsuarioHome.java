package cadastro;

import java.util.Collection;

import transferobject.EventoTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUIUsuarioHome extends AbstractGUI {
	
	public void atualizarListaEventosDisponiveis(Collection<EventoTO> eventosDisponiveis);
	public void atualizarListaParticipacao(Collection<EventoTO> eventosInscritos);
	public void atualizarListaAdministracao(Collection<EventoTO> eventosComPerfilDeChair);
	public void atualizarListaExames(Collection<EventoTO> eventosComPerfilDeExaminador);
	public void atualizarListaMeusEventos(Collection<EventoTO> meusEventos);
	public void atualizarPerfil(UsuarioTO suarioLogado);
	public void atualizarExibicaoSelecionada();
	public void fechar();
	

}
