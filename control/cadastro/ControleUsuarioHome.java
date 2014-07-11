package cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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

public class ControleUsuarioHome implements AbstractControle{
	
	private Map<String, Evento> mapaDeEventosDeferidosNaoInscritos;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	
	public ControleUsuarioHome(AbstractControle caller) {
		
		
	}
	
	@Override
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
		
		viewUsuarioHome.atualizarTabelaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
	}
	
	private Collection<EventoTO> obterEventosDeferidosNaoInscritos() {
		
		EventoTO eventoVO = null;
		UsuarioTO usuarioVO = null;
		InstituicaoTO instituicaoVO = null;
		
		mapaDeEventosDeferidosNaoInscritos = new TreeMap<String, Evento>();
		
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
				
				mapaDeEventosDeferidosNaoInscritos.put(eventoVO.getNome(), evento);
				eventosDeferidosNaoInscritos.add(eventoVO);
			}
		}
		
		return eventosDeferidosNaoInscritos;
	}
	
	public void realizarInscricaoEmEvento(EventoTO eventoVO) {
		
		Usuario usuario = Sessao.getUsuarioLogado();
		Evento evento = mapaDeEventosDeferidosNaoInscritos.get(eventoVO.getNome());
			
			try {
				ControladorDeParticipacao.realizarInscricaoEmEvento(evento, usuario);
				mapaDeEventosDeferidosNaoInscritos.remove(evento.getNome());
				viewUsuarioHome.exibirMensagemDeInformacao("Inscrição no evento \"" + eventoVO.getNome() + "\" realizada com sucesso.", "Inscrição realizada com sucesso!");
			} catch (ExcecaoDeCadastro e) {
				// TODO Auto-generated catch block
				viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro!");
			} finally {
				viewUsuarioHome.atualizarTabelaEventosDisponiveis(obterEventosDeferidosNaoInscritos());
			}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desbloquearGUI() {
		// TODO Auto-generated method stub
		
	}
}
