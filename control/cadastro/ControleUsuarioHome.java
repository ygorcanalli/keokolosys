package cadastro;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import valueobject.EventoVO;
import valueobject.InstituicaoVO;
import valueobject.UsuarioVO;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class ControleUsuarioHome {
	
	private Map<EventoVO, Evento> eventosDisponiveis;
	private AbstractGUIUsuarioHome viewUsuarioHome;
	
	public ControleUsuarioHome() {
		
		eventosDisponiveis = new TreeMap<EventoVO, Evento>();
	}
	
	public void inicializarGUI() {
		viewUsuarioHome = new SwingUsuarioHome(this);
		viewUsuarioHome.inicializar();
	}
	
	public Collection<EventoVO> obterEventosDisponiveis() {
		
		EventoVO eventoVO = null;
		UsuarioVO usuarioVO = null;
		InstituicaoVO instituicaoVO = null;
		
		Collection<Evento> eventos = ControladorDeCadastro.obterTodosEventosDeferidos();
		
		for (Evento evento: eventos) {
			eventoVO = new EventoVO();
			eventoVO.setNome(evento.getNome());
			
			usuarioVO = new UsuarioVO();
			usuarioVO.setEmail(evento.getUsuarioResponsavel().getEmail());
			eventoVO.setUsuarioResponsavel(usuarioVO);
			
			instituicaoVO = new InstituicaoVO();
			instituicaoVO.setSigla(evento.getInstituicao().getSigla());
			
			eventoVO.setInstituicao(instituicaoVO);
			eventosDisponiveis.put(eventoVO, evento);
		}
		
		return eventosDisponiveis.keySet();
	}
	
	public void realizarInscricaoEmEvento(EventoVO eventoVO) {
		Evento evento = eventosDisponiveis.get(eventoVO);
		Usuario usuario = ControladorDeCadastro.obterTodosUsuarios().iterator().next();
		try {
			ControladorDeParticipacao.realizarInscricaoEmEvento(evento, usuario);
		} catch (ExcecaoDeCadastro e) {
			// TODO Auto-generated catch block
			viewUsuarioHome.exibirMensagemDeErro(e.getMessage(), "Erro!");
		}
	}

}
