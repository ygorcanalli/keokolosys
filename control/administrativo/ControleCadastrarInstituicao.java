package administrativo;

import java.util.ArrayList;
import java.util.Collection;

import transferobject.InstituicaoTO;
import util.AbstractControle;
import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;


public class ControleCadastrarInstituicao implements AbstractControle{

	private AbstractGUICadastrarInstituicao viewCadastrarInstituicao;
	private AbstractControle caller;
	
	public ControleCadastrarInstituicao(AbstractControle caller){
		this.caller = caller;
	}
	
	public void inicializarGUI(){
		viewCadastrarInstituicao = new SwingCadastrarInstituicao(this);
		viewCadastrarInstituicao.inicializar();
		
		viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		viewCadastrarInstituicao.desabilitarAcaoCancelar();
		viewCadastrarInstituicao.desabilitarAcaoEditar();
		viewCadastrarInstituicao.desabilitarAcaoSalvar();
		viewCadastrarInstituicao.desabilitarAcaoExcluir();
		viewCadastrarInstituicao.habilitarAcaoNovo();
		viewCadastrarInstituicao.habilitarAcaoSelecionar();
		
		atualizarListaDeInstituicoes();
		
		viewCadastrarInstituicao.tornarVisivel();
	}
	
	private void cadastrarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		ControladorDeCadastro.criarInstituicao(nome, sigla, localizacao);
		atualizarListaDeInstituicoes();
		viewCadastrarInstituicao.exibirMensagemDeInformacao("Instituição: " + sigla + " incluída com sucesso!", "");
	}
	
	private void excluirInstituicao(InstituicaoTO instituicaoTO) throws ExcecaoDeCadastro{
		Instituicao instituicao;
		instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(instituicaoTO.getSigla());
		
		ControladorDeCadastro.removerInstituicao(instituicao);			
		atualizarListaDeInstituicoes();
		
		viewCadastrarInstituicao.exibirMensagemDeInformacao("Instituicao removida com sucesso!", "");
	}
	
	private void exibirInstituicaoSelecionada(){
		InstituicaoTO instituicaoTO = viewCadastrarInstituicao.obterInstituicaoSelecionada();
		viewCadastrarInstituicao.exibirInstituicao(instituicaoTO);
	}
	
	
	private void atualizarInstituicao(InstituicaoTO instituicaoTO, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		Instituicao instituicao;
		
		instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(instituicaoTO.getSigla());
		ControladorDeCadastro.atualizarDadosDaInstituicao(instituicao, nome, sigla, localizacao);
		atualizarListaDeInstituicoes();
		
		viewCadastrarInstituicao.exibirMensagemDeInformacao("Instituicao: " + sigla + " atualizada com sucesso!", "");
	}
	
	
	public void atualizarListaDeInstituicoes(){
		viewCadastrarInstituicao.atualizarListaDeInstituicoes(obterInstituicoes());
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
		viewCadastrarInstituicao.desabilitarAcaoCancelar();
		viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		viewCadastrarInstituicao.desabilitarAcaoSalvar();
		
		viewCadastrarInstituicao.habilitarAcaoNovo();
		viewCadastrarInstituicao.habilitarAcaoExcluir();
		viewCadastrarInstituicao.habilitarAcaoEditar();
		
		exibirInstituicaoSelecionada();
	}
	
	public void acaoNovo(){
		viewCadastrarInstituicao.desabilitarAcaoSelecionar();
		viewCadastrarInstituicao.removerSelecao();
		viewCadastrarInstituicao.limparFormulario();
		viewCadastrarInstituicao.desabilitarAcaoNovo();
		viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		viewCadastrarInstituicao.desabilitarAcaoEditar();
		viewCadastrarInstituicao.desabilitarAcaoExcluir();
		
		viewCadastrarInstituicao.habilitarAcaoSalvar();
		viewCadastrarInstituicao.habilitarAcaoCancelar();
	}
	
	public void acaoSalvar(){		
		InstituicaoTO instituicaoTO = viewCadastrarInstituicao.obterDadosDaInstituicaoPreenchida();
		try {
			cadastrarInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
			
			viewCadastrarInstituicao.definirSelecao(instituicaoTO);
			
			viewCadastrarInstituicao.desabilitarAcaoSalvar();
			viewCadastrarInstituicao.desabilitarAcaoCancelar();
			viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		
			viewCadastrarInstituicao.habilitarAcaoSelecionar();
			viewCadastrarInstituicao.habilitarAcaoEditar();
			viewCadastrarInstituicao.habilitarAcaoExcluir();
			viewCadastrarInstituicao.habilitarAcaoNovo();

			
		} catch (ExcecaoDeCadastro e) {
			viewCadastrarInstituicao.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void acaoEditar(){		
		viewCadastrarInstituicao.desabilitarAcaoEditar();
		viewCadastrarInstituicao.desabilitarAcaoSelecionar();
		viewCadastrarInstituicao.desabilitarAcaoExcluir();
		viewCadastrarInstituicao.desabilitarAcaoSalvar();
		viewCadastrarInstituicao.desabilitarAcaoNovo();
		
		viewCadastrarInstituicao.habilitarAcaoCancelar();
		viewCadastrarInstituicao.habilitarAcaoAtualizar();	
	}
	
	public void acaoExcluir(){
		
		InstituicaoTO instituicaoTO = viewCadastrarInstituicao.obterInstituicaoSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		
		opcaoSelecionada = viewCadastrarInstituicao.exibirMensagemDeConfirmacao("Realmente deseja remover a instituicao: " + instituicaoTO.getSigla() + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirInstituicao(instituicaoTO);
				
				viewCadastrarInstituicao.removerSelecao();
				viewCadastrarInstituicao.limparFormulario();
				viewCadastrarInstituicao.desabilitarAcaoSalvar();
				viewCadastrarInstituicao.desabilitarAcaoCancelar();
				viewCadastrarInstituicao.desabilitarAcaoAtualizar();
				
				viewCadastrarInstituicao.habilitarAcaoSelecionar();
				viewCadastrarInstituicao.habilitarAcaoNovo();
				viewCadastrarInstituicao.habilitarAcaoEditar();
				viewCadastrarInstituicao.habilitarAcaoExcluir();
				
			} catch (ExcecaoDeCadastro e) {
				viewCadastrarInstituicao.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
	}
	
	public void acaoCancelar(){
		viewCadastrarInstituicao.desabilitarAcaoCancelar();
		viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		viewCadastrarInstituicao.desabilitarAcaoSalvar();
		
		viewCadastrarInstituicao.habilitarAcaoNovo();
		viewCadastrarInstituicao.habilitarAcaoSelecionar();
		
		if(viewCadastrarInstituicao.obterInstituicaoSelecionada() != null)
		{
			viewCadastrarInstituicao.habilitarAcaoExcluir();
			viewCadastrarInstituicao.habilitarAcaoEditar();			
		}
		else
		{
			viewCadastrarInstituicao.desabilitarAcaoExcluir();
			viewCadastrarInstituicao.desabilitarAcaoEditar();						
		}
	}
	
	public void acaoAtualizar(){				
		InstituicaoTO instituicaoAtualizadaTO = viewCadastrarInstituicao.obterDadosDaInstituicaoPreenchida();
		InstituicaoTO instituicaoTO = viewCadastrarInstituicao.obterInstituicaoSelecionada();
		
		try {
			atualizarInstituicao(instituicaoTO, instituicaoAtualizadaTO.getNome(), instituicaoAtualizadaTO.getSigla(), instituicaoAtualizadaTO.getLocalizacao());
			atualizarListaDeInstituicoes();
			
			viewCadastrarInstituicao.desabilitarAcaoSalvar();
			viewCadastrarInstituicao.desabilitarAcaoCancelar();
			viewCadastrarInstituicao.desabilitarAcaoAtualizar();
		
			viewCadastrarInstituicao.habilitarAcaoSelecionar();
			viewCadastrarInstituicao.habilitarAcaoEditar();
			viewCadastrarInstituicao.habilitarAcaoExcluir();

		} catch (ExcecaoDeCadastro e) {
			viewCadastrarInstituicao.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void fechar(){
		encerrarGUI();
	}
	
	public void encerrarGUI(){
		viewCadastrarInstituicao.tornarInvisivel();
		caller.desbloquearGUI();
	}
	
	@Override
	public void tornarGUIVisivel() {
		viewCadastrarInstituicao.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewCadastrarInstituicao.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewCadastrarInstituicao.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewCadastrarInstituicao.desbloquear();
	}
}

