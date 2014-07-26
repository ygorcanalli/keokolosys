package administrativo;

import java.util.Collection;

import transferobject.AdministradorTO;
import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractGUI;

public interface AbstractGUIAdministradorHome extends AbstractGUI {

	public void fechar();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public InstituicaoTO obterDadosDaInstituicaoPreenchida();
	public InstituicaoTO obterInstituicaoSelecionada();
	public EventoTO obterEventoAguardandoAvaliacaoSelecionado();
	public EventoTO obterEventoDeferidoSelecionado();
	public UsuarioTO obterUsuarioSelecionado();
	public void definirSelecaoInstituicao(InstituicaoTO instituicao);
	public void removerSelecaoInstituicao();
	public void habilitarAcaoSelecionarInstituicao();
	public void habilitarAcaoNovoInstituicao();
	public void habilitarAcaoSalvarInstituicao();
	public void habilitarAcaoEditarInstituicao();
	public void habilitarAcaoAtualizarInstituicao();
	public void habilitarAcaoExcluirInstituicao();
	public void habilitarAcaoCancelarInstituicao();
	public void desabilitarAcaoSelecionarInstituicao();
	public void desabilitarAcaoNovoInstituicao();
	public void desabilitarAcaoSalvarInstituicao();
	public void desabilitarAcaoEditarInstituicao();
	public void desabilitarAcaoAtualizarInstituicao();
	public void desabilitarAcaoExcluirInstituicao();
	public void desabilitarAcaoCancelarInstituicao();
	public void limparFormularioInstituicao();
	public void exibirInstituicao(InstituicaoTO instituicao);
	public void atualizarListaDeEventosParaDeferir(Collection<EventoTO> eventos);
	public void atualizarListaDeEventosParaCancelar(Collection<EventoTO> eventos);
	public AdministradorTO obterDadosDoNovoAdministrador();
	public String obterConfirmacaoDeSenhaNovoAdministrador();
	public void atualizarListaDeUsuarios(Collection<UsuarioTO> usuarios);
}
