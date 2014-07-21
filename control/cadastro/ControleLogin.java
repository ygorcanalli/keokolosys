package cadastro;


import controladorGRASP.ControladorDeCadastro;
import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import transferobject.AutenticavelTO;
import util.AbstractControle;
import util.Sessao;


public class ControleLogin implements AbstractControle{

	private AbstractGUILogin viewLogin;
	
	public ControleLogin(){
		
	}

	public void fechar() {
		encerrarGUI();
	}

	@Override
	public void inicializarGUI() {
		viewLogin = new SwingLogin(this);
		viewLogin.inicializar();
		tornarGUIVisivel();
	}

	@Override
	public void tornarGUIVisivel() {
		viewLogin.tornarVisivel();
	}

	@Override
	public void tornarGUIInvisivel() {
		viewLogin.tornarInvisivel();
	}

	@Override
	public void bloquearGUI() {
		viewLogin.bloquear();
	}

	@Override
	public void desbloquearGUI() {
		viewLogin.desbloquear();
	}

	@Override
	public void encerrarGUI() {
		Sessao.encerrarSessao();
		tornarGUIInvisivel();
		System.exit(0);
	}

	public void realizarLogin() {
		AutenticavelTO autenticavel = viewLogin.obterAutenticavel();
		entrarNoSistema(autenticavel);
	}

	public void realizarCadastro() {
		cadastrarUsuario();
	}
	
	private void entrarNoSistema(AutenticavelTO autenticavelTo){
		try {
			Autenticavel autenticado = ControladorDeCadastro.entrarNoSistema(autenticavelTo.getEmail(), autenticavelTo.getSenha());
			
			if(Usuario.class.isInstance(autenticado)){
				Usuario usuario = (Usuario) autenticado;
				
				Sessao.iniciarSessao(usuario);
				viewLogin.exibirMensagemDeInformacao("Seja bem vindo: " + usuario.getNomeCompleto() + "!", "");
			}
			else{
				viewLogin.exibirMensagemDeInformacao("Seja bem vindo!", "");
			}
			
			AbstractControle calling = obterCalling(autenticado);
			tornarGUIInvisivel();
			calling.inicializarGUI();
			
		} catch (ExcecaoDeCadastro e) {
			viewLogin.exibirMensagemDeErro(e.getMessage(), "");
		}
	}
	
	private void cadastrarUsuario(){
		AbstractControle cadastrarUsuario = new ControleCadastrarUsuario(this);
		cadastrarUsuario.inicializarGUI();
		bloquearGUI();
	}
	
	
	private AbstractControle	obterCalling(Autenticavel autenticavel){
		if(Usuario.class.isInstance(autenticavel))
			return new ControleUsuarioHome(this);
		else if (Administrador.class.isInstance(autenticavel))
		{
			return new ControleAdministradorHome();
		}
		
		return null; 
	}
}
