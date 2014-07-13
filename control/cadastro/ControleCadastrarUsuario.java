package cadastro;

import java.util.ArrayList;
import java.util.Collection;

import controladorGRASP.ControladorDeCadastro;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import util.AbstractControle;


public class ControleCadastrarUsuario implements AbstractControle{
	private AbstractGUICadastrarUsuario viewCadastroUsuario;
	private AbstractControle caller;
	
	public ControleCadastrarUsuario(AbstractControle caller) {
		this.caller = caller;
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
		viewCadastroUsuario.atualizarListaDeInstituicoes(obterInstituicoes());
	}

	@Override
	public void inicializarGUI() {
		viewCadastroUsuario = new SwingCadastrarUsuario(this);
		atualizarListaDeInstituicoes();
		tornarGUIVisivel();
	}

	@Override
	public void tornarGUIVisivel() {
		viewCadastroUsuario.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewCadastroUsuario.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewCadastroUsuario.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		atualizarListaDeInstituicoes();
		viewCadastroUsuario.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		tornarGUIInvisivel();
		caller.desbloquearGUI();
	}
	
	public void realizarCadastro(){
		UsuarioTO usuarioTO = viewCadastroUsuario.obterUsuarioCriado();
		cadastrarUsuario(usuarioTO);
	}
	
	public void fechar(){
		encerrarGUI();
	}

	
	private void cadastrarUsuario(UsuarioTO usuarioTO){
		Instituicao instituicao = null;

		if(usuarioTO.getSenha().compareTo(viewCadastroUsuario.obterConfirmacaoSenha()) == 0){
			try {
				instituicao = ControladorDeCadastro.obterInstituicaoPorSigla(usuarioTO.getInstituicao().getSigla());
				ControladorDeCadastro.criarUsuario(usuarioTO.getEmail(), usuarioTO.getSenha(), usuarioTO.getNome(), usuarioTO.getUltimoNome(), instituicao);
				viewCadastroUsuario.exibirMensagemDeInformacao("Usuario: " + usuarioTO.getNome() + " cadatrado com sucesso!", "");
			} catch (ExcecaoDeCadastro e) {
				viewCadastroUsuario.exibirMensagemDeErro(e.getMessage(), "");
			}			
		}
		else
			viewCadastroUsuario.exibirMensagemDeErro("Senhas nao conferem!", "");		
	}

	public void incluirInstituicao() {
		bloquearGUI();
		AbstractControle controleIncluirInstituicao = new ControleIncluirInstituicao(this);
		controleIncluirInstituicao.inicializarGUI();
	}
	
}
