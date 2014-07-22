package administrativo;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;
import administrativo.AbstractGUIAdministradorHome;
import administrativo.SwingAdministradorHome;
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
		
		inicializarCadastroDeInstituicoes();
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
	
	private void inicializarCadastroDeInstituicoes(){
		viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
		viewAdministradorHome.desabilitarAcaoEditarInstituicao();
		viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
		viewAdministradorHome.desabilitarAcaoExcluirInstituicao();
		viewAdministradorHome.habilitarAcaoNovoInstituicao();
		viewAdministradorHome.habilitarAcaoSelecionarInstituicao();
		
		atualizarListaDeInstituicoes();
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
	
	
	public void acaoSelecionarInstituicao(){
		viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
		viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
		
		viewAdministradorHome.habilitarAcaoNovoInstituicao();
		viewAdministradorHome.habilitarAcaoExcluirInstituicao();
		viewAdministradorHome.habilitarAcaoEditarInstituicao();
		
		exibirInstituicaoSelecionada();
	}
	
	public void acaoNovoInstituicao(){
		viewAdministradorHome.desabilitarAcaoSelecionarInstituicao();
		viewAdministradorHome.removerSelecaoInstituicao();
		viewAdministradorHome.limparFormularioInstituicao();
		viewAdministradorHome.desabilitarAcaoNovoInstituicao();
		viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		viewAdministradorHome.desabilitarAcaoEditarInstituicao();
		viewAdministradorHome.desabilitarAcaoExcluirInstituicao();
		
		viewAdministradorHome.habilitarAcaoSalvarInstituicao();
		viewAdministradorHome.habilitarAcaoCancelarInstituicao();
	}
	
	public void acaoSalvarInstituicao(){		
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterDadosDaInstituicaoPreenchida();
		try {
			cadastrarInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
			
			viewAdministradorHome.definirSelecaoInstituicao(instituicaoTO);
			
			viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
			viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
			viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		
			viewAdministradorHome.habilitarAcaoSelecionarInstituicao();
			viewAdministradorHome.habilitarAcaoEditarInstituicao();
			viewAdministradorHome.habilitarAcaoExcluirInstituicao();
			viewAdministradorHome.habilitarAcaoNovoInstituicao();

			
		} catch (ExcecaoDeCadastro e) {
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void acaoEditarInstituicao(){		
		viewAdministradorHome.desabilitarAcaoEditarInstituicao();
		viewAdministradorHome.desabilitarAcaoSelecionarInstituicao();
		viewAdministradorHome.desabilitarAcaoExcluirInstituicao();
		viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
		viewAdministradorHome.desabilitarAcaoNovoInstituicao();
		
		viewAdministradorHome.habilitarAcaoCancelarInstituicao();
		viewAdministradorHome.habilitarAcaoAtualizarInstituicao();	
	}
	
	public void acaoExcluirInstituicao(){
		
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterInstituicaoSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		
		opcaoSelecionada = viewAdministradorHome.exibirMensagemDeConfirmacao("Realmente deseja remover a instituicao: " + instituicaoTO.getSigla() + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirInstituicao(instituicaoTO);
				
				viewAdministradorHome.removerSelecaoInstituicao();
				viewAdministradorHome.limparFormularioInstituicao();
				viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
				viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
				viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
				
				viewAdministradorHome.habilitarAcaoSelecionarInstituicao();
				viewAdministradorHome.habilitarAcaoNovoInstituicao();
				viewAdministradorHome.habilitarAcaoEditarInstituicao();
				viewAdministradorHome.habilitarAcaoExcluirInstituicao();
				
			} catch (ExcecaoDeCadastro e) {
				viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
	}
	
	public void acaoCancelarInstituicao(){
		viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
		viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
		
		viewAdministradorHome.habilitarAcaoNovoInstituicao();
		viewAdministradorHome.habilitarAcaoSelecionarInstituicao();
		
		if(viewAdministradorHome.obterInstituicaoSelecionada() != null)
		{
			viewAdministradorHome.habilitarAcaoExcluirInstituicao();
			viewAdministradorHome.habilitarAcaoEditarInstituicao();			
		}
		else
		{
			viewAdministradorHome.desabilitarAcaoExcluirInstituicao();
			viewAdministradorHome.desabilitarAcaoEditarInstituicao();						
		}
	}
	
	public void acaoAtualizarInstituicao(){				
		InstituicaoTO instituicaoAtualizadaTO = viewAdministradorHome.obterDadosDaInstituicaoPreenchida();
		InstituicaoTO instituicaoTO = viewAdministradorHome.obterInstituicaoSelecionada();
		
		try {
			atualizarInstituicao(instituicaoTO, instituicaoAtualizadaTO.getNome(), instituicaoAtualizadaTO.getSigla(), instituicaoAtualizadaTO.getLocalizacao());
			atualizarListaDeInstituicoes();
			
			viewAdministradorHome.desabilitarAcaoSalvarInstituicao();
			viewAdministradorHome.desabilitarAcaoCancelarInstituicao();
			viewAdministradorHome.desabilitarAcaoAtualizarInstituicao();
		
			viewAdministradorHome.habilitarAcaoSelecionarInstituicao();
			viewAdministradorHome.habilitarAcaoEditarInstituicao();
			viewAdministradorHome.habilitarAcaoExcluirInstituicao();

		} catch (ExcecaoDeCadastro e) {
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void fechar(){
		encerrarGUI();
	}
}
