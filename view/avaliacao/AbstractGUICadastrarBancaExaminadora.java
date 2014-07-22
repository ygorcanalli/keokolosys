package avaliacao;

import java.util.Collection;

import transferobject.BancaExaminadoraTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarBancaExaminadora extends AbstractGUI{

	public void atualizarListaDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras);
	public void atualizarListaDeExaminadoresNaoPertencentesABanca(Collection<UsuarioTO> examinadores);
	public void atualizarListaDeExaminadoresPertencesABanca(Collection<UsuarioTO> examinadores);
	public BancaExaminadoraTO obterDadosDaBancaExaminadoraPreenchida();
	public BancaExaminadoraTO obterBancaExaminadoraSelecionada();
	public UsuarioTO obterExaminadorPertencenteSelecionado();
	public UsuarioTO obterExaminadorNaoPertencenteSelecionado();
	public void definirSelecaoBancaExaminadora(BancaExaminadoraTO bancaExaminadora);
	public void removerSelecaoBancaExaminadora();
	public void removerSelecaoExaminadorNaoPertencenteABanca();
	public void habilitarAcaoSelecionarBanca();
	public void habilitarAcaoSelecionarExaminadorNaoPertencente();
	public void habilitarAcaoAdicionarExaminador();
	public void habilitarAcaoNovo();
	public void habilitarAcaoSalvar();
	public void habilitarAcaoEditar();
	public void habilitarAcaoAtualizar();
	public void habilitarAcaoExcluir();
	public void habilitarAcaoCancelar();
	public void desabilitarAcaoSelecionarBanca();
	public void desabilitarAcaoSelecionarExaminadorNaoPertencente();
	public void desabilitarAcaoAdicionarExaminador();
	public void desabilitarAcaoNovo();
	public void desabilitarAcaoSalvar();
	public void desabilitarAcaoEditar();
	public void desabilitarAcaoAtualizar();
	public void desabilitarAcaoExcluir();
	public void desabilitarAcaoCancelar();
	public void limparFormulario();
	public void exibirBancaExaminadora(BancaExaminadoraTO bancaExaminadora);
	public void habilitarAcaoRemoverExaminador();
	public void desabilitarAcaoRemoverExaminador();
}
