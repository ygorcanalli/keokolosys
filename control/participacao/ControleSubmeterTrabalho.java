package participacao;

import transferobject.TrabalhoTO;
import util.AbstractControle;
import util.Sessao;
import dominio.Evento;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeParticipacao;


public class ControleSubmeterTrabalho  implements AbstractControle
{
	private AbstractGUISubmeterTrabalho viewSubmeterTrabalho;
	private AbstractControle caller;
	private Evento evento;
	
	public ControleSubmeterTrabalho(AbstractControle caller,Evento evento) {
		viewSubmeterTrabalho = new SwingSubmeterTrabalho(this);		
		this.caller = caller;
		this.evento = evento;
		caller.bloquearGUI();
		
		Usuario usuario = Sessao.getUsuarioLogado();
		
		viewSubmeterTrabalho.setNomeusuariosubmissor(usuario.getNome());
	}
	public void sair()
	{
		encerrarGUI();		
		caller.desbloquearGUI();
	}
	public void submeterTrabalho(TrabalhoTO trabalhoTO) throws ExcecaoDeParticipacao {
		Usuario usuario = Sessao.getUsuarioLogado();
		PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) usuario.obterPerfilDe(evento, PerfilDeParticipante.class);
		Trabalho.criarTrabalho(perfilParticipante, trabalhoTO.getTitulo(), trabalhoTO.getResumo(), usuario.getNome(),trabalhoTO.getCaminhoArquivoSubmissao());
		viewSubmeterTrabalho.exibirMensagemDeInformacao("Submetido com sucesso !!!", "Sucesso");
		encerrarGUI();		
		caller.desbloquearGUI();
	}

	@Override
	public void inicializarGUI() {
		viewSubmeterTrabalho.inicializar();
		
	}

	@Override
	public void tornarGUIVisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tornarGUIInvisivel() {
		viewSubmeterTrabalho.tornarInvisivel();		
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
		viewSubmeterTrabalho.tornarInvisivel();
		
	}
}
