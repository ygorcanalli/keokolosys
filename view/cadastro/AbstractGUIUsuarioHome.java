package cadastro;

import java.util.Collection;

import transferobject.EventoTO;
import util.AbstractGUI;

public interface AbstractGUIUsuarioHome extends AbstractGUI {
	
	public void atualizarListaEventosDisponiveis(Collection<EventoTO> eventosDisponiveis);
	public void atualizarListaParticipacao(Collection<EventoTO> eventosInscritos);
	

}
