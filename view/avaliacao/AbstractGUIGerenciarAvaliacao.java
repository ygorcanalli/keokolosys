package avaliacao;

import java.util.Collection;

import transferobject.TrabalhoTO;
import util.AbstractGUI;

public interface AbstractGUIGerenciarAvaliacao extends AbstractGUI{
	
	public void setTrabalhosNaoAvaliados(Collection<TrabalhoTO> trabalhos);

}
