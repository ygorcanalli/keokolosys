package cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import participacao.ControleSubmeterTrabalho;
import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;
import util.Sessao;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public class ControleUsuarioHome implements AbstractControle{
	
	private Map<String, Evento> mapaDeEventosInscritos;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	
	public ControleUsuarioHome(AbstractControle caller) {
		
		
	}
	
	@Override
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
		viewUsuarioHome.tornarVisivel();
	}
	
	private Collection<EventoTO> obterEventosDeferidosNaoInscritos() {
		
		EventoTO eventoVO = null;
		UsuarioTO usuarioVO = null;
		InstituicaoTO instituicaoVO = null;
		
		mapaDeEventosInscritos = new TreeMap<String, Evento>();
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventosDeferidos();
		Collection<EventoTO> eventosDeferidosNaoInscritos = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (!ControladorDeParticipacao.possuiInscricao(Sessao.getUsuarioLogado(), evento)) {
				eventoVO = new EventoTO();
				eventoVO.setNome(evento.getNome());
				
				usuarioVO = new UsuarioTO();
				usuarioVO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoVO.setUsuarioResponsavel(usuarioVO);
				
				instituicaoVO = new InstituicaoTO();
				instituicaoVO.setSigla(evento.getInstituicao().getSigla());
				eventoVO.setInstituicao(instituicaoVO);
				
				mapaDeEventosInscritos.put(eventoVO.getNome(), evento);
				eventosDeferidosNaoInscritos.add(eventoVO);
			}
		}
		
		return eventosDeferidosNaoInscritos;
	}
	
	private Collection<EventoTO> obterEventosInscritos() {
		
		EventoTO eventoVO = null;
		UsuarioTO usuarioVO = null;
		InstituicaoTO instituicaoVO = null;
		
		mapaDeEventosInscritos = new TreeMap<String, Evento>();
		
		Collection<Evento> eventosDeferidos = ControladorDeCadastro.obterTodosEventos();
		Collection<EventoTO> eventosDeferidosInscritos = new ArrayList<EventoTO>();
		
		for (Evento evento: eventosDeferidos) {
			if (ControladorDeParticipacao.possuiInscricao(Sessao.getUsuarioLogado(), evento)) {
				eventoVO = new EventoTO();
				eventoVO.setNome(evento.getNome());
				
				usuarioVO = new UsuarioTO();
				usuarioVO.setEmail(evento.getUsuarioResponsavel().getEmail());
				eventoVO.setUsuarioResponsavel(usuarioVO);
				
				instituicaoVO = new InstituicaoTO();
				instituicaoVO.setSigla(evento.getInstituicao().getSigla());
				eventoVO.setInstituicao(instituicaoVO);
				
				mapaDeEventosInscritos.put(eventoVO.getNome(), evento);
				eventosDeferidosInscritos.add(eventoVO);
			}
		}
		
		return eventosDeferidosInscritos;
	}
	
	public void realizarInscricaoEmEvento(EventoTO eventoVO) {
		
		Usuario usuario = Sessao.getUsuarioLogado();
		Evento evento = mapaDeEventosInscritos.get(eventoVO.getNome());
			
			try {
				ControladorDeParticipacao.realizarInscricaoEmEvento(evento, usuario);
				mapaDeEventosInscritos.remove(evento.getNome());
				viewUsuarioHome.exibirMensagemDeInformacao("Inscrição no evento \"" + eventoVO.getNome() + "\" realizada com sucesso.", "Inscrição realizada com sucesso!");
			} catch (ExcecaoDeCadastro e) {
				// TODO Auto-generated catch block
				viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro!");
			} finally {
				viewUsuarioHome.atualizarListaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
			}

	}
	
	public void gerenciarTrabalhosSubmetidos(EventoTO eventoTO) throws ExcecaoDeParticipacao 
	{
		Evento evento = mapaDeEventosInscritos.get(eventoTO.getNome());
		new ControleGerenciarTrabalhosSubmetidos(this, evento);
		bloquearGUI();
	}
	
	public void submeterTrabalhos(EventoTO eventoTO)
	{
		 Evento evento = mapaDeEventosInscritos.get(eventoTO.getNome());
		new ControleSubmeterTrabalho(this,evento).inicializarGUI();
	}
	
	public void exibirEventosDisponiveis() {
		viewUsuarioHome.atualizarListaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
	}
	
	public void exibirParticipacao() {
		viewUsuarioHome.atualizarListaParticipacao(obterEventosInscritos());
	}

	@Override
	public void tornarGUIVisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tornarGUIInvisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bloquearGUI() {
		viewUsuarioHome.bloquear();
		
	}

	@Override
	public void desbloquearGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarGUI() {
		// TODO Auto-generated method stub
		
	}
}
