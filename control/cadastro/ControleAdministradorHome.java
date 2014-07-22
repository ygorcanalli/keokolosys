package cadastro;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;
import administrativo.AbstractGUIAdministradorHome;
import administrativo.AbstractGUICadastrarInstituicao;
import administrativo.SwingAdministradorHome;
import administrativo.SwingCadastrarInstituicao;
import transferobject.InstituicaoTO;
import util.AbstractControle;

public class ControleAdministradorHome implements AbstractControle{

	private AbstractGUIAdministradorHome viewAdministradorHome;
	private AbstractControle caller;
	
	public ControleAdministradorHome(AbstractControle caller){
		this.caller = caller;
	}
	
	@Override
	public void inicializarGUI(){
		viewAdministradorHome = new SwingAdministradorHome(this);
		viewAdministradorHome.inicializar();
		
		viewAdministradorHome.desabilitarAcaoAtualizar();
		viewAdministradorHome.desabilitarAcaoCancelar();
		viewAdministradorHome.desabilitarAcaoEditar();
		viewAdministradorHome.desabilitarAcaoSalvar();
		viewAdministradorHome.desabilitarAcaoExcluir();
		viewAdministradorHome.habilitarAcaoNovo();
		viewAdministradorHome.habilitarAcaoSelecionar();
		
		atualizarListaDeInstituicoes();
		
		tornarGUIVisivel();
	}
	
	@Override
	public void encerrarGUI(){
		viewAdministradorHome.tornarInvisivel();
		caller.desbloquearGUI();
	}
	
	@Override
	public void tornarGUIVisivel() {
		viewAdministradorHome.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewAdministradorHome.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewAdministradorHome.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewAdministradorHome.desbloquear();
	}
	
	private void cadastrarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
		atualizarListaDeInstituicoes();
		viewAdministradorHome.exibirMensagemDeInformacao("Instituição: " + sigla + " incluída com sucesso!", "");
	}
	
	private void excluirInstituicao(InstituicaoTO instituicaoTO) throws ExcecaoDeCadastro{
		Instituicao instituicao;
		instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(instituicaoTO.getSigla());
		
		ControladorDeCadastro.removerInstituicao(instituicao);			
		atualizarListaDeInstituicoes();
		
		viewAdministradorHome.exibirMensagemDeInformacao("Instituicao removida com sucesso!", "");
	}
	
	private void exibirInstituicaoSelecionada(){
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterInstituicaoSelecionada();
		viewAdministradorHome.exibirInstituicao(instituicaoTO);
	}
	
	
	private void atualizarInstituicao(InstituicaoTO instituicaoTO, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		Instituicao instituicao;
		
		instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(instituicaoTO.getSigla());
		ControladorDeCadastro.atualizarDadosDaInstituicao(instituicao, nome, sigla, localizacao);
		atualizarListaDeInstituicoes();
		
		viewAdministradorHome.exibirMensagemDeInformacao("Instituicao: " + sigla + " atualizada com sucesso!", "");
	}
	
	
	public void atualizarListaDeInstituicoes(){
		viewAdministradorHome.atualizarListaDeInstituicoes(obterInstituicoes());
	}
	
	
	private Collection<InstituicaoTO> obterInstituicoes(){
		Collection<Instituicao> instituicoes = ControladorDeCadastro.obterTodasInstituicoes();
		Collection<InstituicaoTO> instituicoesTO = new ArrayList<InstituicaoTO>();
		InstituicaoTO instituicaoTO;
		
		for (Instituicao instituicao : instituicoes) {
			instituicaoTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
			instituicoesTO.add(instituicaoTO);
		}
		
		return instituicoesTO;
	}
	
	
	public void acaoSelecionar(){
		viewAdministradorHome.desabilitarAcaoCancelar();
		viewAdministradorHome.desabilitarAcaoAtualizar();
		viewAdministradorHome.desabilitarAcaoSalvar();
		
		viewAdministradorHome.habilitarAcaoNovo();
		viewAdministradorHome.habilitarAcaoExcluir();
		viewAdministradorHome.habilitarAcaoEditar();
		
		exibirInstituicaoSelecionada();
	}
	
	public void acaoNovo(){
		viewAdministradorHome.desabilitarAcaoSelecionar();
		viewAdministradorHome.removerSelecao();
		viewAdministradorHome.limparFormulario();
		viewAdministradorHome.desabilitarAcaoNovo();
		viewAdministradorHome.desabilitarAcaoAtualizar();
		viewAdministradorHome.desabilitarAcaoEditar();
		viewAdministradorHome.desabilitarAcaoExcluir();
		
		viewAdministradorHome.habilitarAcaoSalvar();
		viewAdministradorHome.habilitarAcaoCancelar();
	}
	
	public void acaoSalvar(){		
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterDadosDaInstituicaoPreenchida();
		try {
			cadastrarInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
			
			viewAdministradorHome.definirSelecao(instituicaoTO);
			
			viewAdministradorHome.desabilitarAcaoSalvar();
			viewAdministradorHome.desabilitarAcaoCancelar();
			viewAdministradorHome.desabilitarAcaoAtualizar();
		
			viewAdministradorHome.habilitarAcaoSelecionar();
			viewAdministradorHome.habilitarAcaoEditar();
			viewAdministradorHome.habilitarAcaoExcluir();
			viewAdministradorHome.habilitarAcaoNovo();

			
		} catch (ExcecaoDeCadastro e) {
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void acaoEditar(){		
		viewAdministradorHome.desabilitarAcaoEditar();
		viewAdministradorHome.desabilitarAcaoSelecionar();
		viewAdministradorHome.desabilitarAcaoExcluir();
		viewAdministradorHome.desabilitarAcaoSalvar();
		viewAdministradorHome.desabilitarAcaoNovo();
		
		viewAdministradorHome.habilitarAcaoCancelar();
		viewAdministradorHome.habilitarAcaoAtualizar();	
	}
	
	public void acaoExcluir(){
		
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterInstituicaoSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		
		opcaoSelecionada = viewAdministradorHome.exibirMensagemDeConfirmacao("Realmente deseja remover a instituicao: " + instituicaoTO.getSigla() + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirInstituicao(instituicaoTO);
				
				viewAdministradorHome.removerSelecao();
				viewAdministradorHome.limparFormulario();
				viewAdministradorHome.desabilitarAcaoSalvar();
				viewAdministradorHome.desabilitarAcaoCancelar();
				viewAdministradorHome.desabilitarAcaoAtualizar();
				
				viewAdministradorHome.habilitarAcaoSelecionar();
				viewAdministradorHome.habilitarAcaoNovo();
				viewAdministradorHome.habilitarAcaoEditar();
				viewAdministradorHome.habilitarAcaoExcluir();
				
			} catch (ExcecaoDeCadastro e) {
				viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
	}
	
	public void acaoCancelar(){
		viewAdministradorHome.desabilitarAcaoCancelar();
		viewAdministradorHome.desabilitarAcaoAtualizar();
		viewAdministradorHome.desabilitarAcaoSalvar();
		
		viewAdministradorHome.habilitarAcaoNovo();
		viewAdministradorHome.habilitarAcaoSelecionar();
		
		if(viewAdministradorHome.obterInstituicaoSelecionada() != null)
		{
			viewAdministradorHome.habilitarAcaoExcluir();
			viewAdministradorHome.habilitarAcaoEditar();			
		}
		else
		{
			viewAdministradorHome.desabilitarAcaoExcluir();
			viewAdministradorHome.desabilitarAcaoEditar();						
		}
	}
	
	public void acaoAtualizar(){				
		InstituicaoTO instituicaoAtualizadaTO = viewAdministradorHome.obterDadosDaInstituicaoPreenchida();
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterInstituicaoSelecionada();
		
		try {
			atualizarInstituicao(instituicaoTO, instituicaoAtualizadaTO.getNome(), instituicaoAtualizadaTO.getSigla(), instituicaoAtualizadaTO.getLocalizacao());
			atualizarListaDeInstituicoes();
			
			viewAdministradorHome.desabilitarAcaoSalvar();
			viewAdministradorHome.desabilitarAcaoCancelar();
			viewAdministradorHome.desabilitarAcaoAtualizar();
		
			viewAdministradorHome.habilitarAcaoSelecionar();
			viewAdministradorHome.habilitarAcaoEditar();
			viewAdministradorHome.habilitarAcaoExcluir();

		} catch (ExcecaoDeCadastro e) {
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void fechar(){
		encerrarGUI();
	}
}
