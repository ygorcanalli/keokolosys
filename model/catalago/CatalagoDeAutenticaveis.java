package catalago;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class CatalagoDeAutenticaveis {
	
	private Map<String, Autenticavel> autenticaveis;	
	private static CatalagoDeAutenticaveis instancia;
	
	public CatalagoDeAutenticaveis() {
		this.autenticaveis = new HashMap<String, Autenticavel>();
	}	
	
	public static synchronized CatalagoDeAutenticaveis obterInstancia() {
		if (instancia == null)
			instancia =  new CatalagoDeAutenticaveis();
		
		return instancia;
	}
		
	public Usuario criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Usuario usuario = Usuario.criarUsuario(nome, ultimoNome, email, senha, instituicao);
		autenticaveis.put(email, usuario);
		return usuario;
    }
	
	public void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Administrador administrador = Administrador.criarAdministrador(email, senha);
		autenticaveis.put(email, administrador);
    }
	
	public void atualizarUsuario(Usuario usuario, String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws ExcecaoDeCadastro{
		if(usuario.getEmail().compareTo(email) != 0)
			validarEmailComoUnico(email);
		
		usuario.atualizarDados(email, senha, nome, ultimoNome, instituicao);
	}
	
	public void atualizarAdministrador(Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		if(administrador.getEmail().compareTo(email) != 0)
			validarEmailComoUnico(email);

		administrador.atualizarDados(email, senha);
	}

	public Collection<Autenticavel> obterAutenticaveis(){		
		return autenticaveis.values();
	}
	
	public Autenticavel entrarNoSistema(String email, String senha) throws ExcecaoDeCadastro{
		Autenticavel autenticavel = obterAutenticavelPorEmail(email);
		Boolean senhaCorreta = autenticavel.realizarAutenticacao(senha);
		
		if(!senhaCorreta)
			throw new ExcecaoDeCadastro("catalago_de_autenticaveis.autenticavel.senha_incorreta");
		
		return autenticavel;
	}
	
	public Autenticavel obterAutenticavelPorEmail(String email) throws ExcecaoDeCadastro{
		Autenticavel autenticavel = autenticaveis.get(email);
		
		if(autenticavel == null)
			throw new ExcecaoDeCadastro("catalago_de_autenticaveis.autenticavel.nao_localizado");
		
		return autenticavel;
	}
	
	public Collection<Usuario> obterUsuarios(){
		Class<Usuario> tipoUsuario = Usuario.class;
		Collection<Usuario> usuarios = new ArrayList<Usuario>();
		Collection<Autenticavel> autenticaveisDoTipoUsuario = obterAutenticaveis();
		
		for (Autenticavel autenticavel : autenticaveisDoTipoUsuario) {
			if(tipoUsuario.isInstance(autenticavel))
				usuarios.add((Usuario) autenticavel);
		}
		
		return usuarios;
	}
	
	public Collection<Administrador> obterAdministradores(){
		Class<Administrador> tipoAdministrador = Administrador.class;
		Collection<Administrador> administradores = new ArrayList<Administrador>();
		Collection<Autenticavel> autenticaveisDoTipoAdministrador = obterAutenticaveis();
		
		for (Autenticavel autenticavel : autenticaveisDoTipoAdministrador) {
			if(tipoAdministrador.isInstance(autenticavel))
				administradores.add((Administrador) autenticavel);
		}
		
		return administradores;
	}

	
	private void validarEmailComoUnico(String email) throws ExcecaoDeCadastro{
		if(autenticaveis.containsKey(email))
			throw new ExcecaoDeCadastro("catalago_de_autenticaveis.email.existente");
	}
	
}
