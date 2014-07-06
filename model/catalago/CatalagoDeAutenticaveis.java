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
		
	public void criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Usuario usuario = Usuario.criarUsuario(nome, ultimoNome, email, senha, instituicao);
		autenticaveis.put(email, usuario);
    }
	
	public void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Administrador administrador = Administrador.criarAdministrador(email, senha);
		autenticaveis.put(email, administrador);
    }
	
	public void atualizarUsuario(Usuario usuario, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		if(usuario.getEmail().compareTo(email) != 0)
			usuario.setEmail(email);
		
		usuario.setSenha(senha);
		
		if(usuario.getNome().compareTo(nome) != 0)
			usuario.setNome(nome);
		
		if(usuario.getUltimoNome().compareTo(ultimoNome) != 0)
			usuario.setUltimoNome(ultimoNome);
		
		if(usuario.getInstituicao().compareTo(instituicao) != 0)
			usuario.setInstituicao(instituicao);
	}
	
	public void atualizarAdministrador(Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		if(administrador.getEmail().compareTo(email) != 0)
			administrador.setEmail(email);
		
		administrador.setSenha(senha);		
	}
	
	public Collection<Autenticavel> obterAutenticaveis(){		
		return autenticaveis.values();
	}
	
	public Autenticavel entrarNoSistema(String email, String senha) throws ExcecaoDeCadastro{
		Autenticavel autenticavel = buscarAutenticavelPorEmail(email);
		Boolean senhaCorreta = autenticavel.realizarAutenticacao(senha);
		
		if(!senhaCorreta)
			throw new ExcecaoDeCadastro("catalago_de_autenticaveis.autenticavel.senha_incorreta");
		
		return autenticavel;
	}
	
	private Autenticavel buscarAutenticavelPorEmail(String email) throws ExcecaoDeCadastro{
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
