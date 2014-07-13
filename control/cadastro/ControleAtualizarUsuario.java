package cadastro;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;
import util.Sessao;


public class ControleAtualizarUsuario implements AbstractControle{
	private AbstractGUIAtualizarUsuario viewAtualizarUsuario;
	private AbstractControle caller;  
	
	public ControleAtualizarUsuario(AbstractControle caller) {
		this.caller = caller;
	}
	
	@Override
	public void inicializarGUI() {
		viewAtualizarUsuario = new SwingAtualizarUsuario(this);
		viewAtualizarUsuario.inicializar();
		atualizarListaDeInstituicoes();
		preencherDadosDoUsuarioLogado();
		tornarGUIVisivel();
	}

	@Override
	public void tornarGUIVisivel() {
		viewAtualizarUsuario.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewAtualizarUsuario.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewAtualizarUsuario.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		atualizarListaDeInstituicoes();
		viewAtualizarUsuario.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}

	public void fechar() {
		encerrarGUI();
	}
	
	private void preencherDadosDoUsuarioLogado(){
		Usuario usuarioLogado = Sessao.getUsuarioLogado();
		InstituicaoTO instituicaoTO = new InstituicaoTO(usuarioLogado.getInstituicao().getNome(), usuarioLogado.getInstituicao().getSigla(), usuarioLogado.getInstituicao().getLocalizacao());
		UsuarioTO usuarioTO = new UsuarioTO(usuarioLogado.getEmail(), usuarioLogado.getNome(), usuarioLogado.getUltimoNome(), instituicaoTO);
		
		viewAtualizarUsuario.exibirDadosDoUsuario(usuarioTO);
	}
	
	private void atualizarUsuario(UsuarioTO usuarioTO){
		Instituicao instituicao = null;
		Usuario usuario = null;

		if(usuarioTO.getSenha().compareTo(viewAtualizarUsuario.obterConfirmacaoSenha()) == 0){
			try {
				
				usuario = Sessao.getUsuarioLogado();
				instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(usuarioTO.getInstituicao().getSigla());
				
				ControladorDeCadastro.atualizarDadosDoUsuario(usuario, usuarioTO.getEmail(), usuarioTO.getSenha(), usuarioTO.getNome(), usuarioTO.getUltimoNome(), instituicao);
				viewAtualizarUsuario.exibirMensagemDeInformacao("Usuario: " + usuarioTO.getNome() + " atualizado com sucesso!", "");
				
			} catch (ExcecaoDeCadastro e) {
				viewAtualizarUsuario.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
		else
			viewAtualizarUsuario.exibirMensagemDeErro("Senhas nao conferem!", "");		
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
	
	public void atualizarListaDeInstituicoes(){
		viewAtualizarUsuario.atualizarListaDeInstituicoes(obterInstituicoes());
	}

	public void incluirInstituicao() {
		bloquearGUI();
		AbstractControle controleIncluirInstituicao = new ControleIncluirInstituicao(this);
		controleIncluirInstituicao.inicializarGUI();
	}

	public void atualizar() {
		UsuarioTO usuarioTO = viewAtualizarUsuario.obterAtualizacaoDosDadosDoUsuario();
		atualizarUsuario(usuarioTO);
	}
}
