package cadastro;

import java.util.Collection;

import transferobject.EventoTO;
import util.AbstractGUI;

public interface AbstractGUIUsuarioHome extends AbstractGUI {
	
	public void atualizarTabelaEventosDisponiveis(Collection<EventoTO> eventosDisponiveis);
	

}
