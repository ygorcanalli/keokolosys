package avaliacao;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeAvaliacao;
import dominio.BancaExaminadora;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import dominio.Usuario;
import excecao.ExcecaoDeAvaliacao;
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
		atualizarListaDeBancasExaminadoras();
		atualizarListaDeExaminadores();
		
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
		atualizarListaDeBancasExaminadoras();
		atualizarListaDeExaminadores();
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
			
			examinadorTO = new UsuarioTO();
			
			examinadorTO.setEmail(email);
			examinadorTO.setNome(nome);
			examinadorTO.setUltimoNome(ultimoNome);
		}
		
		return examinadoresTO;
	}
	

	private void atualizarListaDeBancasExaminadoras(){
		viewCadastroBancaExaminadora.atualizarListaDeBancasExaminadoras(obterBancasExaminadoras());
	}
	
	
	private Collection<BancaExaminadoraTO> obterBancasExaminadoras(){
		Collection<BancaExaminadora> bancasExaminadora = ControladorDeAvaliacao.obterTodasAsBancasExaminadorasDoEvento(evento);
		Collection<BancaExaminadoraTO> bancasExaminadoraTO = new ArrayList<BancaExaminadoraTO>();
		Collection<UsuarioTO> examinadoresTO;
		
		for (BancaExaminadora bancaExaminadora : bancasExaminadora) {
			
			examinadoresTO = new ArrayList<UsuarioTO>();
			
			for (PerfilDeExaminador examinador : bancaExaminadora.getExaminadores()) {
				examinadoresTO.add(converterPerfilDeExaminadorParaUsuarioTO(examinador));
			}
			
			bancasExaminadoraTO.add(new BancaExaminadoraTO(examinadoresTO));
		}
		
		return bancasExaminadoraTO;
	}
	
	private void cadastrarBancaExaminadora(Collection<UsuarioTO> examinadoresTO) throws ExcecaoDeAvaliacao{
		Collection<PerfilDeExaminador> examinadores = new ArrayList<PerfilDeExaminador>();
		PerfilDeExaminador examinador;
		String emailDoExaminador = null;
		
		for (UsuarioTO examinadorTO : examinadoresTO) {
			
			emailDoExaminador = examinadorTO.getEmail();
			
			examinador = ControladorDeAvaliacao.obterExaminadorDoEventoPorEmail(evento, emailDoExaminador);
			examinadores.add(examinador);
		}
		
		ControladorDeAvaliacao.criarBancaExaminadora(evento, examinadores);
	}
	
	
	private void atualizarBancaExaminadora(BancaExaminadoraTO bancaExaminadoraTO, Collection<UsuarioTO> examinadores) throws ExcecaoDeAvaliacao{
		// TODO Auto-generated method stub
		
	}
	
	
	private void excluirBancaExaminadora(BancaExaminadoraTO bancaExaminadoraTO) throws ExcecaoDeAvaliacao{
		// TODO Auto-generated method stub
		
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
	
	private void exibirBancaExaminadoraSelecionada() {
		// TODO Auto-generated method stub
		
	}
	
	private UsuarioTO converterPerfilDeExaminadorParaUsuarioTO(PerfilDeExaminador examinador){
		UsuarioTO examinadorTO;
		Usuario usuarioReferenteAoExaminador;
		
		usuarioReferenteAoExaminador = examinador.getUsuario();
		
		String email = usuarioReferenteAoExaminador.getEmail();
		String nome = usuarioReferenteAoExaminador.getNome();
		String ultimoNome = usuarioReferenteAoExaminador.getUltimoNome();
		
		examinadorTO = new UsuarioTO();
		
		examinadorTO.setEmail(email);
		examinadorTO.setNome(nome);
		examinadorTO.setUltimoNome(ultimoNome);
		examinadorTO.setInstituicao(converterInstituicaoParaInstituicaoTO(usuarioReferenteAoExaminador.getInstituicao()));
		
		return examinadorTO;
	}
	
	private InstituicaoTO converterInstituicaoParaInstituicaoTO(Instituicao instituicao){
		return new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
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
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida();
		try {
			cadastrarBancaExaminadora(bancaExaminadoraTO.getExaminadores());
			
			viewCadastroBancaExaminadora.definirSelecaoBancaExaminadora(bancaExaminadoraTO);
			
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();
			viewCadastroBancaExaminadora.habilitarAcaoNovo();

			
		} catch (ExcecaoDeAvaliacao e) {
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
		
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		String informacaoDosExaminadores = "";
		
		for (UsuarioTO examinador : bancaExaminadoraTO.getExaminadores()) {
			informacaoDosExaminadores += examinador.getNome() + ":" + examinador.getEmail();  
		}
		
		opcaoSelecionada = viewCadastroBancaExaminadora.exibirMensagemDeConfirmacao("Realmente deseja remover a banca examinadora formanda pelos examinadores:" + informacaoDosExaminadores + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirBancaExaminadora(bancaExaminadoraTO);
				
				viewCadastroBancaExaminadora.removerSelecaoBancaExaminadora();
				viewCadastroBancaExaminadora.limparFormulario();
				viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
				viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
				viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
				
				viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
				viewCadastroBancaExaminadora.habilitarAcaoNovo();
				viewCadastroBancaExaminadora.habilitarAcaoEditar();
				viewCadastroBancaExaminadora.habilitarAcaoExcluir();
				
			} catch (ExcecaoDeAvaliacao e) {
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
		
		if(viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada() != null)
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
		BancaExaminadoraTO bancaExaminadoraAtualizadaTO = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida();
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		
		try {

			atualizarBancaExaminadora(bancaExaminadoraTO, bancaExaminadoraAtualizadaTO.getExaminadores());
			
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.habilitarAcaoSelecionar();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();

		} catch (ExcecaoDeAvaliacao e) {
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
