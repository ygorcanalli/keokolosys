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
		
		inicializarGUI();
	}

	@Override
	public void inicializarGUI() {
		viewCadastroBancaExaminadora = new SwingCadastrarBancaExaminadora(this);
		viewCadastroBancaExaminadora.inicializar();
		atualizarListaDeBancasExaminadoras();
		
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
		viewCadastroBancaExaminadora.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}
	
	private void atualizarListaDeExaminadoresPertencensABanca(BancaExaminadoraTO bancaExaminadoraTO){
		Collection<UsuarioTO> examinadoresTO = bancaExaminadoraTO.getExaminadores();
		viewCadastroBancaExaminadora.atualizarListaDeExaminadoresPertencesABanca(examinadoresTO);
	}
	
	private void atualizarListaDeExaminadoresNaoPertencensABanca(BancaExaminadoraTO bancaExaminadoraTO){
		Collection<UsuarioTO> examinadoresTO;
		
		if(bancaExaminadoraTO != null)
			examinadoresTO = obterExaminadoresTOExceto(bancaExaminadoraTO.getExaminadores());
		else
			examinadoresTO = obterTodosExaminadoresTO();
		
		viewCadastroBancaExaminadora.atualizarListaDeExaminadoresNaoPertencentesABanca(examinadoresTO);
	}
	
	
	private Collection<UsuarioTO> obterTodosExaminadoresTO(){
		Collection<UsuarioTO> examinadoresTO = new ArrayList<UsuarioTO>();
		Collection<PerfilDeExaminador> examinadores = ControladorDeAvaliacao.obterTodosExaminadoresDoEvento(evento);
		
		for (PerfilDeExaminador examinador : examinadores) {
			examinadoresTO.add(converterPerfilDeExaminadorParaUsuarioTO(examinador));
		}
		
		return examinadoresTO;
	}
	
	
	private Collection<UsuarioTO> obterExaminadoresTOExceto(Collection<UsuarioTO> examinadoresExcluidos) {
		Collection<PerfilDeExaminador> examinadores = ControladorDeAvaliacao.obterTodosExaminadoresDoEvento(evento);
		Collection<UsuarioTO> examinadoresTO = new ArrayList<UsuarioTO>();
		
		Boolean examinadorPertenceAosExcluidos;
		
		for (PerfilDeExaminador	examinador : examinadores) {
			
			examinadorPertenceAosExcluidos = false;
			
			for (UsuarioTO examinadorTOExcluido : examinadoresExcluidos) {
				
				if(examinador.getUsuario().getEmail().compareTo(examinadorTOExcluido.getEmail()) == 0){
					examinadorPertenceAosExcluidos = true;
				}
			}
			
			if(!examinadorPertenceAosExcluidos)
				examinadoresTO.add(converterPerfilDeExaminadorParaUsuarioTO(examinador));
		}
		
		return examinadoresTO;
	}
	

	private void atualizarListaDeBancasExaminadoras(){
		viewCadastroBancaExaminadora.atualizarListaDeBancasExaminadoras(obterBancasExaminadorasTO());
	}
	
	
	private Collection<BancaExaminadoraTO> obterBancasExaminadorasTO(){
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
	
	private Collection<PerfilDeExaminador> recuperarExaminadores(Collection<UsuarioTO> examinadoresTO) throws ExcecaoDeAvaliacao{
		Collection<PerfilDeExaminador> examinadores = new ArrayList<PerfilDeExaminador>();
		PerfilDeExaminador examinador;
		String emailDoExaminador = null;
		
		for (UsuarioTO examinadorTO : examinadoresTO) {
			
			emailDoExaminador = examinadorTO.getEmail();
			
			examinador = ControladorDeAvaliacao.obterExaminadorDoEventoPorEmail(evento, emailDoExaminador);
			examinadores.add(examinador);
		}
		
		return examinadores;
	}

	
	private void cadastrarBancaExaminadora(Collection<UsuarioTO> examinadoresTO) throws ExcecaoDeAvaliacao{
		Collection<PerfilDeExaminador> examinadores = recuperarExaminadores(examinadoresTO);
		ControladorDeAvaliacao.criarBancaExaminadora(evento, examinadores);
		
		viewCadastroBancaExaminadora.exibirMensagemDeInformacao("Banca examinadora criada com sucesso!", "");
		atualizarListaDeBancasExaminadoras();
	}
		
	
	private void atualizarBancaExaminadora(BancaExaminadoraTO bancaExaminadoraTO, Collection<UsuarioTO> examinadoresTO) throws ExcecaoDeAvaliacao{
		
		BancaExaminadora bancaExaminadora;
		Collection<PerfilDeExaminador> examinadores;
		Collection<PerfilDeExaminador> examinadoresNovos;
		
		examinadores = recuperarExaminadores(bancaExaminadoraTO.getExaminadores());
		examinadoresNovos = recuperarExaminadores(examinadoresTO);
		
		bancaExaminadora = evento.obterBancaExaminadoraPelosExaminadores(examinadores);
		
		ControladorDeAvaliacao.atualizarBancaExaminadora(bancaExaminadora, examinadoresNovos);
		
		viewCadastroBancaExaminadora.exibirMensagemDeInformacao("Banca examinadora atualizada com sucesso!", "");
		
		atualizarListaDeBancasExaminadoras();
	}
	
	
	private void excluirBancaExaminadora(BancaExaminadoraTO bancaExaminadoraTO) throws ExcecaoDeAvaliacao{
		BancaExaminadora bancaExaminadora;
		Collection<PerfilDeExaminador> examinadores;
		
		examinadores = recuperarExaminadores(bancaExaminadoraTO.getExaminadores());
		
		bancaExaminadora = evento.obterBancaExaminadoraPelosExaminadores(examinadores);
		
		ControladorDeAvaliacao.removerBancaExaminadora(evento, bancaExaminadora);
		viewCadastroBancaExaminadora.exibirMensagemDeInformacao("Banca examinadora removida com sucesso!", "");
		
		atualizarListaDeBancasExaminadoras();
	}

	private void cancelarEdicaoDeBancaExaminadora(){
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		if(bancaExaminadoraTO != null)
			atualizarListaDeExaminadoresPertencensABanca(bancaExaminadoraTO);
		else
			viewCadastroBancaExaminadora.limparFormulario();
	}
	
	private void exibirBancaExaminadoraSelecionada() {
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		viewCadastroBancaExaminadora.exibirBancaExaminadora(bancaExaminadoraTO);		
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
	
	public void acaoSelecionarExaminadorNaoPertencenteABanca(){
		viewCadastroBancaExaminadora.habilitarAcaoAdicionarExaminador();
	}
	
	
	public void acaoSelecionarBancaExaminadora(){
		viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		
		viewCadastroBancaExaminadora.habilitarAcaoNovo();
		viewCadastroBancaExaminadora.habilitarAcaoExcluir();
		viewCadastroBancaExaminadora.habilitarAcaoEditar();
		
		viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
		viewCadastroBancaExaminadora.desabilitarAcaoRemoverExaminador();
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionarExaminadorNaoPertencente();
		
		exibirBancaExaminadoraSelecionada();		
	}

	public void acaoNovo(){
		
		atualizarListaDeExaminadoresNaoPertencensABanca(null);
		
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionarBanca();
		viewCadastroBancaExaminadora.removerSelecaoBancaExaminadora();
		viewCadastroBancaExaminadora.limparFormulario();
		viewCadastroBancaExaminadora.desabilitarAcaoNovo();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoEditar();
		viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
		
		viewCadastroBancaExaminadora.habilitarAcaoSalvar();
		viewCadastroBancaExaminadora.habilitarAcaoCancelar();
		
		viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
		viewCadastroBancaExaminadora.habilitarAcaoRemoverExaminador();
		viewCadastroBancaExaminadora.habilitarAcaoSelecionarExaminadorNaoPertencente();
	}
	
	public void acaoSalvar(){
		
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida();
		
		try {
			cadastrarBancaExaminadora(bancaExaminadoraTO.getExaminadores());
			
			viewCadastroBancaExaminadora.definirSelecaoBancaExaminadora(bancaExaminadoraTO);
			viewCadastroBancaExaminadora.removerSelecaoExaminadorNaoPertencenteABanca();
			
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.desabilitarAcaoSelecionarBanca();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();
			viewCadastroBancaExaminadora.habilitarAcaoNovo();

			viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
			viewCadastroBancaExaminadora.desabilitarAcaoRemoverExaminador();
			viewCadastroBancaExaminadora.desabilitarAcaoSelecionarExaminadorNaoPertencente();
			viewCadastroBancaExaminadora.habilitarAcaoSelecionarBanca();
			
		} catch (ExcecaoDeAvaliacao e) {
			viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}

	public void acaoEditar(){		
		viewCadastroBancaExaminadora.desabilitarAcaoEditar();
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionarBanca();
		viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		viewCadastroBancaExaminadora.desabilitarAcaoNovo();
		
		viewCadastroBancaExaminadora.habilitarAcaoCancelar();
		viewCadastroBancaExaminadora.habilitarAcaoAtualizar();	
		
		viewCadastroBancaExaminadora.habilitarAcaoSelecionarExaminadorNaoPertencente();
		viewCadastroBancaExaminadora.habilitarAcaoRemoverExaminador();
	}
	
	public void acaoExcluir(){
		
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		String opcoes[] = {"Sim", "Não"};
		Integer opcaoSelecionada;
		String informacaoDosExaminadores = "";
		
		for (UsuarioTO examinador : bancaExaminadoraTO.getExaminadores()) {
			informacaoDosExaminadores += examinador.getNome() + ":" + examinador.getEmail() + "\n";  
		}
		
		opcaoSelecionada = viewCadastroBancaExaminadora.exibirMensagemDeConfirmacao("Realmente deseja remover a banca examinadora formanda pelos examinadores:\n" + informacaoDosExaminadores + "?", "", opcoes, "Não");
		
		if(opcaoSelecionada == 0)
		{
			try {
				excluirBancaExaminadora(bancaExaminadoraTO);
				
				viewCadastroBancaExaminadora.removerSelecaoBancaExaminadora();
				viewCadastroBancaExaminadora.removerSelecaoExaminadorNaoPertencenteABanca();
				viewCadastroBancaExaminadora.limparFormulario();
				viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
				viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
				viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
				
				viewCadastroBancaExaminadora.desabilitarAcaoSelecionarBanca();
				viewCadastroBancaExaminadora.habilitarAcaoNovo();
				//viewCadastroBancaExaminadora.habilitarAcaoEditar();
				//viewCadastroBancaExaminadora.habilitarAcaoExcluir();
				viewCadastroBancaExaminadora.desabilitarAcaoEditar();
				viewCadastroBancaExaminadora.desabilitarAcaoExcluir();
				
				viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
				viewCadastroBancaExaminadora.desabilitarAcaoRemoverExaminador();
				viewCadastroBancaExaminadora.desabilitarAcaoSelecionarExaminadorNaoPertencente();
				viewCadastroBancaExaminadora.habilitarAcaoSelecionarBanca();
				
			} catch (ExcecaoDeAvaliacao e) {
				viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
	}
	

	public void acaoCancelar(){
		
		cancelarEdicaoDeBancaExaminadora();
		
		viewCadastroBancaExaminadora.removerSelecaoExaminadorNaoPertencenteABanca();
		viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
		viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
		viewCadastroBancaExaminadora.habilitarAcaoNovo();
		
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
		
		viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
		viewCadastroBancaExaminadora.desabilitarAcaoRemoverExaminador();
		viewCadastroBancaExaminadora.desabilitarAcaoSelecionarExaminadorNaoPertencente();
		viewCadastroBancaExaminadora.habilitarAcaoSelecionarBanca();
	}
	
	
	public void acaoAtualizar(){				
		BancaExaminadoraTO bancaExaminadoraAtualizadaTO = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida();
		BancaExaminadoraTO bancaExaminadoraTO = viewCadastroBancaExaminadora.obterBancaExaminadoraSelecionada();
		
		try {

			atualizarBancaExaminadora(bancaExaminadoraTO, bancaExaminadoraAtualizadaTO.getExaminadores());
			
			viewCadastroBancaExaminadora.removerSelecaoExaminadorNaoPertencenteABanca();
			viewCadastroBancaExaminadora.desabilitarAcaoSalvar();
			viewCadastroBancaExaminadora.desabilitarAcaoCancelar();
			viewCadastroBancaExaminadora.desabilitarAcaoAtualizar();
		
			viewCadastroBancaExaminadora.desabilitarAcaoSelecionarBanca();
			viewCadastroBancaExaminadora.habilitarAcaoEditar();
			viewCadastroBancaExaminadora.habilitarAcaoExcluir();
			
			viewCadastroBancaExaminadora.desabilitarAcaoAdicionarExaminador();
			viewCadastroBancaExaminadora.desabilitarAcaoRemoverExaminador();
			viewCadastroBancaExaminadora.desabilitarAcaoSelecionarExaminadorNaoPertencente();
			viewCadastroBancaExaminadora.habilitarAcaoSelecionarBanca();

		} catch (ExcecaoDeAvaliacao e) {
			viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
		}		
	}
	

	public void acaoRemoverExaminador(){
		BancaExaminadoraTO bancaExaminadoraTOAtualizada = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida(); 
		Collection<UsuarioTO> examinadoresAtuais = bancaExaminadoraTOAtualizada.getExaminadores();
				
		UsuarioTO examinadorTO = viewCadastroBancaExaminadora.obterExaminadorPertencenteSelecionado();
		
		if(examinadorTO != null){
			examinadoresAtuais.remove(examinadorTO);
			atualizarListaDeExaminadoresNaoPertencensABanca(bancaExaminadoraTOAtualizada);
			atualizarListaDeExaminadoresPertencensABanca(bancaExaminadoraTOAtualizada);	
		}		
		else
			viewCadastroBancaExaminadora.exibirMensagemDeErro("Nenhum examinador selecionado!", "");
	}
	
	
	public void acaoAdicionarExaminador(){
		BancaExaminadoraTO bancaExaminadoraTOAtualizada = viewCadastroBancaExaminadora.obterDadosDaBancaExaminadoraPreenchida(); 
		Collection<UsuarioTO> examinadoresAtuais = bancaExaminadoraTOAtualizada.getExaminadores();
		UsuarioTO examinadorTO = viewCadastroBancaExaminadora.obterExaminadorNaoPertencenteSelecionado();
		
		if(examinadorTO != null){
			examinadoresAtuais.add(examinadorTO);
			atualizarListaDeExaminadoresPertencensABanca(bancaExaminadoraTOAtualizada);
			atualizarListaDeExaminadoresNaoPertencensABanca(bancaExaminadoraTOAtualizada);	
		}		
		else
			viewCadastroBancaExaminadora.exibirMensagemDeErro("Nenhum examinador selecionado!", "");		
	}
	
	public void fechar(){
		encerrarGUI();
	}

	public int compareBancas(BancaExaminadoraTO bancaExaminadoraTO1, BancaExaminadoraTO bancaExaminadoraTO2) {
		Collection<PerfilDeExaminador> examinadoresBanca1;
		Collection<PerfilDeExaminador> examinadoresBanca2;
		
		try {
			
			examinadoresBanca1 = recuperarExaminadores(bancaExaminadoraTO1.getExaminadores());
			BancaExaminadora bancaExaminadora1 = ControladorDeAvaliacao.obterBancaExaminadoraPelosExaminadores(evento, examinadoresBanca1);
			
			examinadoresBanca2 = recuperarExaminadores(bancaExaminadoraTO2.getExaminadores());
			BancaExaminadora bancaExaminadora2 = ControladorDeAvaliacao.obterBancaExaminadoraPelosExaminadores(evento, examinadoresBanca2);
			
			return (bancaExaminadora1 == bancaExaminadora2 ? 0 : 1);
			
		} catch (ExcecaoDeAvaliacao e) {
			viewCadastroBancaExaminadora.exibirMensagemDeErro(e.getMessage(), "");
		}
		
		return 1;
	}
}
