package avaliacao;

import java.util.Collection;

import transferobject.BancaExaminadoraTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarBancaExaminadora extends AbstractGUI{

	public void atualizarListaDeBancasExaminadoras(Collection<BancaExaminadoraTO> instituicoes);
	public void atualizarListaDeExaminadores(Collection<UsuarioTO> examinadores);
	public BancaExaminadoraTO obterDadosDaBancaExaminadoraPreenchida();
	public BancaExaminadoraTO obterBancaExaminadoraSelecionada();
	public void definirSelecaoBancaExaminadora(BancaExaminadoraTO bancaExaminadora);
	public void definirSelecaoExaminador(UsuarioTO examinador);
	public void removerSelecaoBancaExaminadora();
	public void removerSelecaoExaminador();
	public void habilitarAcaoSelecionar();
	public void habilitarAcaoAdicionarExaminador();
	public void habilitarAcaoNovo();
	public void habilitarAcaoSalvar();
	public void habilitarAcaoEditar();
	public void habilitarAcaoAtualizar();
	public void habilitarAcaoExcluir();
	public void habilitarAcaoCancelar();
	public void desabilitarAcaoSelecionar();
	public void desabilitarAcaoAdicionarExaminador();
	public void desabilitarAcaoNovo();
	public void desabilitarAcaoSalvar();
	public void desabilitarAcaoEditar();
	public void desabilitarAcaoAtualizar();
	public void desabilitarAcaoExcluir();
	public void desabilitarAcaoCancelar();
	public void limparFormulario();
	public void exibirBancaExaminadora(BancaExaminadoraTO bancaExaminadora);

}
