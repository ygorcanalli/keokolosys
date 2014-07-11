package administrativo;

import java.util.Collection;

import transferobject.InstituicaoTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarInstituicao extends AbstractGUI {

	public void fechar();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public InstituicaoTO obterInstituicaoCriada();
	public void habilitarAcaoNovo();
	public void habilitarAcaoSalvar();
	public void habilitarAcaoEditar();
	public void habilitarAcaoAtualizar();
	public void habilitarAcaoExcluir();
	public void habilitarAcaoCancelar();
}
