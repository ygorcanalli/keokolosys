package cadastro;

import java.util.ArrayList;
import java.util.Collection;

import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;
import util.Sessao;
import catalago.Pessoal;
import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class ControleCadastrarEvento implements AbstractControle{
	private AbstractGUICadastrarEvento viewCadastroDeEvento;
	private AbstractControle caller;
	
	public ControleCadastrarEvento(AbstractControle caller){
		this.caller = caller;
	}

	@Override
	public void inicializarGUI(){
		viewCadastroDeEvento = new SwingCadastrarEvento(this);
		viewCadastroDeEvento.inicializar();
		atualizarListaDeInstituicoes();
		definirUsuario();
		
		tornarGUIVisivel();
	}
	
	public void encerrarGUI(){
		viewCadastroDeEvento.tornarInvisivel();
		caller.desbloquearGUI();
	}
	
	public void atualizarListaDeInstituicoes(){
		viewCadastroDeEvento.atualizarListaDeInstituicoes(obterInstituicoes());
	}
	
	private void definirUsuario(){
		Usuario usuario = Sessao.getUsuarioLogado();
		Instituicao instituicao = usuario.getInstituicao();
		InstituicaoTO instituicaoTO = new InstituicaoTO(instituicao.getNome(), instituicao.getSigla(), instituicao.getLocalizacao());
		UsuarioTO usuarioTO = new UsuarioTO(usuario.getEmail(), usuario.getNome(), usuario.getUltimoNome(), instituicaoTO);
		viewCadastroDeEvento.definirUsuarioResponsavel(usuarioTO);
		
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

	
	public void criarEvento(){

		EventoTO eventoTO = viewCadastroDeEvento.obterEventoCriado();
		InstituicaoTO instituicaoTO = eventoTO.getInstituicao(); 
		Instituicao instituicao; 
		
		try {
			instituicao = Pessoal.obterInstancia().obterInstituicaoPorSigla(instituicaoTO.getSigla());
			
			try{
				ControladorDeCadastro.criarEvento(eventoTO.getNome(), instituicao, Sessao.getUsuarioLogado(), eventoTO.getDataMaximaParaSubmissaoDeTrabalhos(), eventoTO.getDataMaximaParaAceitacaoDeTrabalhos(), eventoTO.getDataDeInicio(), eventoTO.getDataDeFim());
				viewCadastroDeEvento.exibirMensagemDeInformacao("Evento: '" + eventoTO.getNome() + "' cadastrado com sucesso!", "");
				
			}
			catch (ExcecaoDeCadastro ec){
				viewCadastroDeEvento.exibirMensagemDeErro(ec.getMessage(), "");
			}
		} catch (ExcecaoDeCadastro ec) {
			viewCadastroDeEvento.exibirMensagemDeErro(ec.getMessage(), "");
		} 		
	}
	
	public void incluirNovaInstituicao(){
		AbstractControle controleIncluirInstituicao = new ControleIncluirInstituicao(this);
		controleIncluirInstituicao.inicializarGUI();
		viewCadastroDeEvento.bloquear();
	}
	
	public void fechar(){
		encerrarGUI();
	}


	@Override
	public void tornarGUIVisivel() {
		viewCadastroDeEvento.tornarVisivel();
	}


	@Override
	public void tornarGUIInvisivel() {
		viewCadastroDeEvento.tornarInvisivel();
	}


	@Override
	public void bloquearGUI() {
		viewCadastroDeEvento.bloquear();
	}


	@Override
	public void desbloquearGUI() {
		atualizarListaDeInstituicoes();
		viewCadastroDeEvento.desbloquear();
	}	
}
