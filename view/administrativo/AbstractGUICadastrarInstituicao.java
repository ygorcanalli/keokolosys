package administrativo;

import java.util.Collection;

import transferobject.InstituicaoTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarInstituicao extends AbstractGUI {

	public void fechar();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public InstituicaoTO obterDadosDaInstituicaoPreenchida();
	public InstituicaoTO obterInstituicaoSelecionada();
	public void definirSelecao(InstituicaoTO instituicao);
	public void removerSelecao();
	public void habilitarAcaoSelecionar();
	public void habilitarAcaoNovo();
	public void habilitarAcaoSalvar();
	public void habilitarAcaoEditar();
	public void habilitarAcaoAtualizar();
	public void habilitarAcaoExcluir();
	public void habilitarAcaoCancelar();
	public void desabilitarAcaoSelecionar();
	public void desabilitarAcaoNovo();
	public void desabilitarAcaoSalvar();
	public void desabilitarAcaoEditar();
	public void desabilitarAcaoAtualizar();
	public void desabilitarAcaoExcluir();
	public void desabilitarAcaoCancelar();
	public void limparFormulario();
	public void exibirInstituicao(InstituicaoTO instituicao);
}
