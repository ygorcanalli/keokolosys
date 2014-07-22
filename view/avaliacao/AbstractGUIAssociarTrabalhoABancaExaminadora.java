package avaliacao;

import java.util.Collection;

import transferobject.BancaExaminadoraTO;
import transferobject.TrabalhoTO;
import util.AbstractGUI;

public interface AbstractGUIAssociarTrabalhoABancaExaminadora extends AbstractGUI {

	public void atualizarListDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras);

	public void definirTrabalho(TrabalhoTO trabalho);

	public BancaExaminadoraTO obterBancaExaminadoraSelecionada();

}
