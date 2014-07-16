package cadastro;

import java.util.Collection;
import controladorGRASP.ControladorDeParticipacao;
import dominio.Evento;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeParticipacao;
import transferobject.TrabalhoTO;
import util.AbstractControle;
import util.Sessao;

public class ControleGerenciarTrabalhosSubmetidos  implements AbstractControle{
	
	AbstractControle caller;
	public ControleGerenciarTrabalhosSubmetidos(AbstractControle caller,Evento evento) throws ExcecaoDeParticipacao {
		this.caller = caller;
		Usuario usuario = Sessao.getUsuarioLogado();
		PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);
		Collection<Trabalho> trabalhos = ControladorDeParticipacao.obterTodosTrabalhosSubmetidosPeloParticipante(evento, perfilParticipante);
		
		inicializarGUI();
	}
	
	public void submeterVersaoFinal(TrabalhoTO trabalhoTO) 
	{
		
		bloquearGUI();
	}

	@Override
	public void inicializarGUI() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void encerrarGUI() {
		// TODO Auto-generated method stub
		
	}

}
