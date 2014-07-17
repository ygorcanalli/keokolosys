package avaliacao;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeAvaliacao;
import controladorGRASP.ControladorDeCadastro;
import dominio.BancaExaminadora;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import excecao.ExcecaoDeCadastro;
import transferobject.BancaExaminadoraTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;


public class ControleCadastrarBancaExaminadora implements AbstractControle{
	private AbstractGUICadastrarBancaExaminadora viewCadastroBancaExaminadora;
	private Evento evento;
	private AbstractControle caller;
	
	public ControleCadastrarBancaExaminadora(AbstractControle caller, Evento evento) {
		this.caller = caller;
		this.evento = evento;
	}

	@Override
	public void inicializarGUI() {
		viewCadastroBancaExaminadora = new SwingCadastrarBancaExaminadora(this);
		viewCadastroBancaExaminadora.inicializar();
		
		tornarGUIVisivel();
	}

	@Override
	public void tornarGUIVisivel() {
		viewCadastroBancaExaminadora.tornarVisivel();	
	}

	@Override
	public void tornarGUIInvisivel() {
		viewCadastroBancaExaminadora.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewCadastroBancaExaminadora.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewCadastroBancaExaminadora.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}
	
	private void atualizarListaDeExaminadores(){
		viewCadastroBancaExaminadora.atualizarListaDeExaminadores(obterExaminadores());
	}
	
	
	private Collection<UsuarioTO> obterExaminadores() {
		Collection<PerfilDeExaminador> examinadores = ControladorDeAvaliacao.obterTodosExaminadoresDoEvento(evento);
		Collection<UsuarioTO> examinadoresTO = new ArrayList<UsuarioTO>();
		UsuarioTO examinadorTO;
		
		for (PerfilDeExaminador	examinador : examinadores) {
			String email = examinador.getUsuario().getEmail();
			String nome = examinador.getUsuario().getNome();
			String ultimoNome = examinador.getUsuario().getUltimoNome();
			String instituicao = examinador.getUsuario().getInstituicao();
			
			examinadorTO = new UsuarioTO(, nome, ultimoNome, instituicao)
		}
		
		return bancasExaminadoraTO;
	}
	

	private void atualizarListaDeBancasExaminadoras(){
		viewCadastroBancaExaminadora.atualizarListaDeBancasExaminadoras(obterBancasExaminadoras());
	}
	
	
	private Collection<BancaExaminadoraTO> obterBancasExaminadoras(){
		Collection<BancaExaminadora> bancasExaminadora = ControladorDeAvaliacao.obterTodasAsBancasExaminadorasDoEvento(evento);
		Collection<BancaExaminadoraTO> bancasExaminadoraTO = new ArrayList<BancaExaminadoraTO>();
		BancaExaminadoraTO bancaExaminadoraTO;
		
		for (BancaExaminadora bancaExaminadora : bancasExaminadora) {
			bancaExaminadoraTO = new BancaExaminadoraTO();
		}
		
		return bancasExaminadoraTO;
	}
	
	
	public void acaoSelecionar(){
		viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		
		viewCadastroBancaExaminadora.habilitarAcaoNovo();
		viewCadastroBancaExaminadora.habilitarAcaoExcluir();
		viewCadastroBancaExaminadora.habilitarAcaoEditar();
		
		exibirBancaExaminadoraSelecionada();
	}
	
	public void acaoNovo(){
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionar();
		viewCadastroBancaExaminadora.removerSelecaoBancaExaminadora();
		viewCadastroBancaExaminadora.limparFormulario();
		viewCadastroBancaExaminadora.desabilitarAcaoNovo();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoEditar();
		viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
		
		viewCadastroBancaExaminadora.habilitarAcaoSalvar();
		viewCadastroBancaExaminadora.habilitarAcaoCancelar();
	}
	
	public void acaoSalvar(){		
		InstituicaoTO instituicaoTO = viewCadastroBancaExaminadora.obterDadosDaInstituicaoPreenchida();
		try {
			cadastrarInstituicao(instituicaoTO.getNome(), instituicaoTO.getSigla(), instituicaoTO.getLocalizacao());
			
			viewCadastroBancaExaminadora.definirSelecaoBancaExaminadora(instituicaoTO);
			
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();
			viewCadastroBancaExaminadora.habilitarAcaoNovo();

			
		} catch (ExcecaoDeCadastro e) {
			viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void acaoEditar(){		
		viewCadastroBancaExaminadora.desabilitarAcaoEditar();
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionar();
		viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		viewCadastroBancaExaminadora.desabilitarAcaoNovo();
		
		viewCadastroBancaExaminadora.habilitarAcaoCancelar();
		viewCadastroBancaExaminadora.habilitarAcaoAtualizar();	
	}
	
	public void acaoExcluir(){
		
		InstituicaoTO instituicaoTO = viewCadastroBancaExaminadora.obterInstituicaoSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		
		opcaoSelecionada = viewCadastroBancaExaminadora.exibirMensagemDeConfirmacao("Realmente deseja remover a instituicao: " + instituicaoTO.getSigla() + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirInstituicao(instituicaoTO);
				
				viewCadastroBancaExaminadora.removerSelecao();
				viewCadastroBancaExaminadora.limparFormulario();
				viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
				viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
				viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
				
				viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
				viewCadastroBancaExaminadora.habilitarAcaoNovo();
				viewCadastroBancaExaminadora.habilitarAcaoEditar();
				viewCadastroBancaExaminadora.habilitarAcaoExcluir();
				
			} catch (ExcecaoDeCadastro e) {
				viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
	}
	
	public void acaoCancelar(){
		viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		
		viewCadastroBancaExaminadora.habilitarAcaoNovo();
		viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
		
		if(viewCadastroBancaExaminadora.obterInstituicaoSelecionada() != null)
		{
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();			
		}
		else
		{
			viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
			viewCadastroBancaExaminadora.desabilitarAcaoEditar();						
		}
	}
	
	public void acaoAtualizar(){				
		InstituicaoTO instituicaoAtualizadaTO = viewCadastroBancaExaminadora.obterDadosDaInstituicaoPreenchida();
		InstituicaoTO instituicaoTO = viewCadastroBancaExaminadora.obterInstituicaoSelecionada();
		
		try {
			atualizarInstituicao(instituicaoTO, instituicaoAtualizadaTO.getNome(), instituicaoAtualizadaTO.getSigla(), instituicaoAtualizadaTO.getLocalizacao());
			atualizarListaDeInstituicoes();
			
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();

		} catch (ExcecaoDeCadastro e) {
			viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	
	public void acaoRemoverExaminador(){
		
	}
	
	public void acaoAdicionarExaminador(){
		
	}
	
	public void fechar(){
		encerrarGUI();
	}
}
