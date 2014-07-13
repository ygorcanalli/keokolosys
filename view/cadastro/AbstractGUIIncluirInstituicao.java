package cadastro;

import transferobject.InstituicaoTO;
import util.AbstractGUI;

public interface AbstractGUIIncluirInstituicao extends AbstractGUI {

	public void fechar();
	public InstituicaoTO obterDadosDaInstituicaoPreenchida();
	public void incluir();

}
