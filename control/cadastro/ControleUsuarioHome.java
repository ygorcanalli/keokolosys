package cadastro;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import avaliacao.ControleAvaliarTrabalho;
import avaliacao.ControleCadastrarBancaExaminadora;
import avaliacao.ControleGerenciarAvaliacao;
import participacao.ControleConcederPrivilegios;
import participacao.ControleSubmeterTrabalho;
import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;
import util.Sessao;
import controladorGRASP.ControladorDeAvaliacao;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.BancaExaminadora;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Perfil;
import dominio.PerfilDeExaminador;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public class ControleUsuarioHome implements AbstractControle{
	
	private Map<String, Evento> mapaDeEventosDeferidosNaoInscritos;
	private Map<String, Evento> mapaDeEventosInscritos;
	private Map<String, Evento> mapaDeEventosComPerfilDeChair;
	private Map<String, Evento> mapaDeEventosComPerfilDeExaminador;
	private Map<String, Evento> mapaDeMeusEventos;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	private AbstractControle caller;
	
	public ControleUsuarioHome(AbstractControle caller) {
		this.caller = caller;
	}
	
	@Override
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
		viewUsuarioHome.tornarVisivel();
	}
	
	private Collection<EventoTO> obterEventosDeferidosNaoInscritos() {
		
		EventoTO eventoTO = null;
		UsuarioTO usuarioTO = null;
		InstituicaoTO instituicaoTO = null;
		
		mapaDeEventosDeferidosNaoInscritos = new TreeMap<String, Evento>();
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosDeferidosNaoInscritos = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (!ControladorDeParticipacao.possuiPerfil(Sessao.getUsuarioLogado(), evento)) {
				eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				
				usuarioTO = new UsuarioTO();
				usuarioTO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoTO.setUsuarioResponsavel(usuarioTO);
				
				instituicaoTO = new InstituicaoTO();
				instituicaoTO.setSigla(evento.getInstituicao().getSigla());
				eventoTO.setInstituicao(instituicaoTO);
				
				mapaDeEventosDeferidosNaoInscritos.put(eventoTO.getNome(), evento);
				eventosDeferidosNaoInscritos.add(eventoTO);
			}
		}
		
		return eventosDeferidosNaoInscritos;
	}
	
	private Collection<EventoTO> obterEventosInscritos() {
		
		EventoTO eventoTO = null;
		UsuarioTO usuarioTO = null;
		InstituicaoTO instituicaoTO = null;
		
		mapaDeEventosInscritos = new TreeMap<String, Evento>();
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosDeferidosInscritos = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (ControladorDeParticipacao.possuiInscricao(Sessao.getUsuarioLogado(), evento)) {
				eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				eventoTO.setEstado(null);
				
				usuarioTO = new UsuarioTO();
				usuarioTO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoTO.setUsuarioResponsavel(usuarioTO);
				
				instituicaoTO = new InstituicaoTO();
				instituicaoTO.setSigla(evento.getInstituicao().getSigla());
				eventoTO.setInstituicao(instituicaoTO);
				
				mapaDeEventosInscritos.put(eventoTO.getNome(), evento);
				eventosDeferidosInscritos.add(eventoTO);
			}
		}
		
		return eventosDeferidosInscritos;
	}
	
	private Collection<EventoTO> obterEventosComPerfilDeChair() {
		// TODO Auto-generated method stub
		mapaDeEventosComPerfilDeChair = new TreeMap<String, Evento>();
		
		EventoTO eventoTO = null;
		UsuarioTO usuarioTO = null;
		InstituicaoTO instituicaoTO = null;
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosDeferidosComperfilDeChair = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (ControladorDeParticipacao.possuiPrivilegioDeChair(Sessao.getUsuarioLogado(), evento)) {
				eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				
				usuarioTO = new UsuarioTO();
				usuarioTO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoTO.setUsuarioResponsavel(usuarioTO);
				
				instituicaoTO = new InstituicaoTO();
				instituicaoTO.setSigla(evento.getInstituicao().getSigla());
				eventoTO.setInstituicao(instituicaoTO);
				
				mapaDeEventosComPerfilDeChair.put(eventoTO.getNome(), evento);
				eventosDeferidosComperfilDeChair.add(eventoTO);
			}
		}
		
		return eventosDeferidosComperfilDeChair;

	}
	
	private Collection<EventoTO> obterEventosComPerfilDeExaminador() {
		// TODO Auto-generated method stub
		mapaDeEventosComPerfilDeExaminador = new TreeMap<String, Evento>();
		
		EventoTO eventoTO = null;
		UsuarioTO usuarioTO = null;
		InstituicaoTO instituicaoTO = null;
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosDeferidosComperfilDeExaminador = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (ControladorDeParticipacao.possuiPrivilegioDeExaminador(Sessao.getUsuarioLogado(), evento)) {
				eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				
				usuarioTO = new UsuarioTO();
				usuarioTO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoTO.setUsuarioResponsavel(usuarioTO);
				
				instituicaoTO = new InstituicaoTO();
				instituicaoTO.setSigla(evento.getInstituicao().getSigla());
				eventoTO.setInstituicao(instituicaoTO);
				
				mapaDeEventosComPerfilDeExaminador.put(eventoTO.getNome(), evento);
				eventosDeferidosComperfilDeExaminador.add(eventoTO);
			}
		}
		
		return eventosDeferidosComperfilDeExaminador;
	}
	
	private Collection<EventoTO> obterMeusEventos() {
		// TODO Auto-generated method stub
		mapaDeMeusEventos = new TreeMap<String, Evento>();
		EventoTO eventoTO = null;
		InstituicaoTO instituicaoTO = null;
		
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventos();
		Collection<EventoTO> meusEventos = new ArrayList<EventoTO>();
		
		for (Evento evento: eventos) {
			if (evento.getUsuarioResponsavel() == Sessao.getUsuarioLogado()) {
				eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				eventoTO.setEstado(evento.obterEstado().obterTipoEnumerado());
				
				instituicaoTO = new InstituicaoTO();
				instituicaoTO.setSigla(evento.getInstituicao().getSigla());
				instituicaoTO.setNome(evento.getInstituicao().getNome());
				eventoTO.setInstituicao(instituicaoTO);
				
				mapaDeMeusEventos.put(eventoTO.getNome(), evento);
				meusEventos.add(eventoTO);
			}
		}
		
		return meusEventos;
	}
	
	private UsuarioTO obterUsuarioLogado() {
		UsuarioTO usuarioTO = new UsuarioTO();
		Usuario usuarioLogado = Sessao.getUsuarioLogado();
		
		usuarioTO.setEmail(usuarioLogado.getEmail());
		usuarioTO.setNome(usuarioLogado.getNome());
		usuarioTO.setUltimoNome(usuarioLogado.getUltimoNome());
		
		InstituicaoTO instituicaoTO = new InstituicaoTO();
		Instituicao instituicao = usuarioLogado.getInstituicao();
		instituicaoTO.setSigla(instituicao.getSigla());
		instituicaoTO.setNome(instituicao.getNome());
		instituicaoTO.setLocalizacao(instituicao.getLocalizacao());
		usuarioTO.setInstituicao(instituicaoTO);	
		
		return usuarioTO;
	}
	
	private Collection<EventoTO> obterEventosQueSouBancaExaminadora(){
		
		Usuario usuario = Sessao.getUsuarioLogado();
		Collection<EventoTO> eventosQueSouBancaExaminadora = new ArrayList<EventoTO>();
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventos();
		for (Evento evento : eventos) {
			Perfil perfilDeExaminador = usuario.obterPerfilDe(evento, PerfilDeExaminador.class);
			if (perfilDeExaminador != null){
				EventoTO eventoTO = new EventoTO();
				eventoTO.setNome(evento.getNome());
				eventosQueSouBancaExaminadora.add(eventoTO);
			}
			
		}
		return eventosQueSouBancaExaminadora;
		
		
	}
	
	public void acaoRealizarInscricaoEmEvento(EventoTO eventoTO) {
		
		Usuario usuario = Sessao.getUsuarioLogado();
		Evento evento = mapaDeEventosDeferidosNaoInscritos.get(eventoTO.getNome());
			
			try {
				ControladorDeParticipacao.realizarInscricaoEmEvento(evento, usuario);
				mapaDeEventosDeferidosNaoInscritos.remove(evento.getNome());
				viewUsuarioHome.exibirMensagemDeInformacao("Inscrição no evento \"" + eventoTO.getNome() + "\" realizada com sucesso.", "Inscrição realizada com sucesso!");
			} catch (ExcecaoDeCadastro e) {
				// TODO Auto-generated catch block
				viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro!");
			} finally {
				viewUsuarioHome.atualizarListaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
			}

	}
	
	public void acaoRealizarAvaliacao(EventoTO eventoTO) {
		
		Evento evento = mapaDeEventosComPerfilDeExaminador.get(eventoTO.getNome());
		
		
	}	
	
	public void acaoGerenciarTrabalhosSubmetidos(EventoTO eventoTO)
	{
		Evento evento = mapaDeEventosInscritos.get(eventoTO.getNome());
		try {
			new ControleGerenciarTrabalhosSubmetidos(this, evento);
			bloquearGUI();
		} catch (ExcecaoDeParticipacao e) {
			// TODO Auto-generated catch block
			viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro ao gerenciar trabalhos submetidos");
		}
		
	}
	
	public void acaoGerenciarBancasExaminadoras(EventoTO eventoTO) 
	{
		Evento evento = mapaDeEventosComPerfilDeChair.get(eventoTO.getNome());
		new ControleCadastrarBancaExaminadora(this, evento);
		bloquearGUI();
	}
	
	public void acaoConcederPrivilegios(EventoTO eventoTO) 
	{
		Evento evento = mapaDeEventosComPerfilDeChair.get(eventoTO.getNome());
		new ControleConcederPrivilegios(this, evento);
		bloquearGUI();
	}
	
	public void acaoCriarEvento() {
		ControleCadastrarEvento controleCadastrarEvento = new ControleCadastrarEvento(this);
		controleCadastrarEvento.inicializarGUI();
		bloquearGUI();
		
	}
	
	public void acaoSubmeterTrabalhos(EventoTO eventoTO)
	{
		 Evento evento = mapaDeEventosInscritos.get(eventoTO.getNome());
		new ControleSubmeterTrabalho(this,evento).inicializarGUI();
	}
	
	public void acaoRealizarAvliacoes(EventoTO eventoTO)
	{
		
		 Evento evento = mapaDeEventosComPerfilDeExaminador.get(eventoTO.getNome());
		 new ControleGerenciarAvaliacao(this,evento);
	}
	
	public void exibirEventosDisponiveis() {
		viewUsuarioHome.atualizarListaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
	}
	
	public void exibirParticipacao() {
		viewUsuarioHome.atualizarListaParticipacao(obterEventosInscritos());
	}
	
	public void exibirExames() {
		viewUsuarioHome.atualizarListaExames(obterEventosComPerfilDeExaminador());
	}
	
	public void exibirAdministracao() {
		viewUsuarioHome.atualizarListaAdministracao(obterEventosComPerfilDeChair());
	}
	
	public void exibirMeusEventos() {
		viewUsuarioHome.atualizarListaMeusEventos(obterMeusEventos());
	}
	
	public void exibirPerfil() {
		viewUsuarioHome.atualizarPerfil(obterUsuarioLogado());
		
	}
	
	public void acaoDeslogar() {
		Sessao.encerrarSessao();
		caller.desbloquearGUI();
		encerrarGUI();
	}


	@Override
	public void tornarGUIVisivel() {
		viewUsuarioHome.tornarVisivel();
	}


	@Override
	public void tornarGUIInvisivel() {
		viewUsuarioHome.tornarInvisivel();
	}


	@Override
	public void bloquearGUI() {
		viewUsuarioHome.bloquear();
	}


	@Override
	public void desbloquearGUI() {
		viewUsuarioHome.desbloquear();
		viewUsuarioHome.atualizarExibicaoSelecionada();
	}

	@Override
	public void encerrarGUI() {
		viewUsuarioHome.tornarInvisivel();
		//caller.desbloquearGUI();
		caller.tornarGUIVisivel();
	}

}
