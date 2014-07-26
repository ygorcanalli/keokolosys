package administrativo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import catalago.CatalagoDeEventos;
import controladorGRASP.ControladorAdministrativo;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Administrador;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import administrativo.AbstractGUIAdministradorHome;
import administrativo.SwingAdministradorHome;
import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;
import util.Sessao;

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
		inicializarAbasDeEvento();
		atualizarListaDeUsuarios();
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
	
	private void inicializarAbasDeEvento()
	{
		atualizarListaDeEventosParaDeferir();
		atualizarListaDeEventosParaCancelar();
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
	
	public void atualizarListaDeEventosParaDeferir()
	{
		viewAdministradorHome.atualizarListaDeEventosParaDeferir(obterEventosAguardandoAprovacao());
	}
	
	public void atualizarListaDeEventosParaCancelar()
	{
		viewAdministradorHome.atualizarListaDeEventosParaCancelar(obterEventosDeferidos());
	}
	
	public void atualizarListaDeUsuarios()
	{
		viewAdministradorHome.atualizarListaDeUsuarios(obterUsuariosDoSistema());
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
	
	private Collection<EventoTO> obterEventosAguardandoAprovacao(){
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventosAguardandoAprovacao();
		Collection<EventoTO> eventosTO = new ArrayList<EventoTO>();
		EventoTO eventoTO;
		
		for (Evento evento : eventos) {
			Instituicao instituicao = evento.getInstituicao();
			InstituicaoTO instTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
			
			Usuario resp = evento.getUsuarioResponsavel();
			Instituicao instituicaoResp = resp.getInstituicao();
			InstituicaoTO instituicaoRespTO = new InstituicaoTO(instituicaoResp.getNome(), instituicaoResp.getSigla(), instituicaoResp.getLocalizacao());
			
			UsuarioTO respTO = new UsuarioTO(resp.getEmail(),resp.getNome(), resp.getUltimoNome(), instituicaoRespTO);
			eventoTO = new EventoTO(evento.getNome(), instTO, respTO,evento.getDataMaximaParaSubmissaoDeTrabalhos(), 
					evento.getDataMaximaParaAceitacaoDeTrabalhos(),evento.getDataDeInicio(), evento.getDataDeFim(), evento.obterParticipantes() == null ? 0 : evento.obterParticipantes().size());
			
			eventosTO.add(eventoTO);
		}
		
		return eventosTO;
	}
	
	private Collection<EventoTO> obterEventosDeferidos(){
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosTO = new ArrayList<EventoTO>();
		EventoTO eventoTO;
		
		for (Evento evento : eventos) {
			Instituicao instituicao = evento.getInstituicao();
			InstituicaoTO instTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
			
			Usuario resp = evento.getUsuarioResponsavel();
			Instituicao instituicaoResp = resp.getInstituicao();
			InstituicaoTO instituicaoRespTO = new InstituicaoTO(instituicaoResp.getNome(), instituicaoResp.getSigla(), instituicaoResp.getLocalizacao());
			
			UsuarioTO respTO = new UsuarioTO(resp.getEmail(),resp.getNome(), resp.getUltimoNome(), instituicaoRespTO);
			eventoTO = new EventoTO(evento.getNome(), instTO, respTO,evento.getDataMaximaParaSubmissaoDeTrabalhos(), 
					evento.getDataMaximaParaAceitacaoDeTrabalhos(),evento.getDataDeInicio(), evento.getDataDeFim(), evento.obterParticipantes() == null ? 0 : evento.obterParticipantes().size());
			
			eventosTO.add(eventoTO);
		}
		
		return eventosTO;
	}
	
	private Collection<UsuarioTO> obterUsuariosDoSistema(){
		Collection<Usuario> usuarios = ControladorDeCadastro.obterTodosUsuarios();
		Collection<UsuarioTO> usuariosTO = new ArrayList<UsuarioTO>();
		UsuarioTO usuarioTO;
		
		for (Usuario usuario : usuarios) {
			Instituicao inst = usuario.getInstituicao();
			InstituicaoTO instituicao = new InstituicaoTO(inst.getNome(), inst.getSigla(), inst.getLocalizacao());
			usuarioTO = new UsuarioTO(usuario.getEmail(),usuario.getNome(),usuario.getUltimoNome(), instituicao);
			
			usuariosTO.add(usuarioTO);
		}
		
		return usuariosTO;
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
				//viewAdministradorHome.habilitarAcaoEditarInstituicao();
				//viewAdministradorHome.habilitarAcaoExcluirInstituicao();
				viewAdministradorHome.desabilitarAcaoEditarInstituicao();
				viewAdministradorHome.desabilitarAcaoExcluirInstituicao();
				
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
	
	public void acaoDeferirEvento(){
		EventoTO eventoTO = viewAdministradorHome.obterEventoAguardandoAvaliacaoSelecionado();
		
		try{
			int confirmacao = viewAdministradorHome.exibirMensagemDeConfirmacao("Deseja deferir o evento?", "Aviso", null, null);
			if(confirmacao == 0)
			{
				Evento evento = CatalagoDeEventos.obterInstancia().obterEventoPorNome(eventoTO.getNome());
				ControladorAdministrativo.deferirEvento(evento);
				viewAdministradorHome.exibirMensagemDeAviso("Evento deferido com sucesso!", "Sucesso");
				
				atualizarListaDeEventosParaDeferir();
				atualizarListaDeEventosParaCancelar();
			}
		}
		catch (ExcecaoDeCadastro e){
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	public void acaoIndeferirEvento(){
		EventoTO eventoTO = viewAdministradorHome.obterEventoAguardandoAvaliacaoSelecionado();
		
		try{
			int confirmacao = viewAdministradorHome.exibirMensagemDeConfirmacao("Deseja indeferir o evento?", "Aviso", null, null);
			if(confirmacao == 0)
			{
				Evento evento = CatalagoDeEventos.obterInstancia().obterEventoPorNome(eventoTO.getNome());
				ControladorAdministrativo.indeferirEvento(evento);
				viewAdministradorHome.exibirMensagemDeAviso("Evento indeferido com sucesso!", "Sucesso");
				
				atualizarListaDeEventosParaDeferir();
				atualizarListaDeEventosParaCancelar();
			}
		}
		catch (ExcecaoDeCadastro e){
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	public void acaoCancelarEvento(){
		EventoTO eventoTO = viewAdministradorHome.obterEventoDeferidoSelecionado();
		
		try{
			int confirmacao = viewAdministradorHome.exibirMensagemDeConfirmacao("Deseja cancelar o evento?", "Aviso", null, null);
			if(confirmacao == 0)
			{
				Evento evento = CatalagoDeEventos.obterInstancia().obterEventoPorNome(eventoTO.getNome());
				ControladorAdministrativo.cancelarEvento(evento);
				viewAdministradorHome.exibirMensagemDeAviso("Evento cancelado com sucesso!", "Sucesso");
				
				atualizarListaDeEventosParaDeferir();
				atualizarListaDeEventosParaCancelar();
			}
		}
		catch (ExcecaoDeCadastro e){
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	public void AlterarSenha(String senhaAntiga, String senhaNova)
	{
		Administrador adminLogado = Sessao.getAdminLogado();

		if(adminLogado.realizarAutenticacao(senhaAntiga))
		{
			try
			{
				adminLogado.atualizarDados(adminLogado.getEmail(), senhaNova);
				viewAdministradorHome.exibirMensagemDeAviso("Sua senha foi alterada com sucesso!", "Sucesso");
			}
			catch(ExcecaoDeCadastro e)
			{
				viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "");
			}
		}
		else
		{
			viewAdministradorHome.exibirMensagemDeErro("Senha Incorreta!", "Erro");
		}
	}
	
	public void ConcederPrivilegioDeAdministrador(){
		UsuarioTO usuarioTO = viewAdministradorHome.obterUsuarioSelecionado();
		
		try 
		{
			Usuario usuario = ControladorDeCadastro.obterUsuarioPorEmail(usuarioTO.getEmail());
			ControladorDeCadastro.criarAdministrador(usuario.getEmail(), usuario.getSenha());
			
			viewAdministradorHome.exibirMensagemDeAviso("Privil�gio concedido!", "Sucesso");
			atualizarListaDeUsuarios();
		} 
		catch (ExcecaoDeCadastro e) 
		{
			viewAdministradorHome.exibirMensagemDeErro(e.getMessage(), "Erro");
		}
	}
	
	public void fechar(){
		encerrarGUI();
	}
	
	public void acaoDeslogar() {
		Sessao.encerrarSessao();
		caller.desbloquearGUI();
		caller.tornarGUIVisivel();
		encerrarGUI();
	}

	/*Nao deveria ter esta regra de negocio aqui*/
	public int compareInstituicoes(InstituicaoTO instituicaoTO1,	InstituicaoTO instituicaoTO2) {
		return instituicaoTO1.getSigla().compareTo(instituicaoTO2.getSigla());
	}
}
