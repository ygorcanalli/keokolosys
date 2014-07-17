package catalago;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class Pessoal {
	
	private Map<String, Autenticavel> autenticaveis;
	private Map<String, Instituicao> instituicoes;
	private static Pessoal instancia;
	
	public Pessoal() {
		this.autenticaveis = new HashMap<String, Autenticavel>();
		this.instituicoes = new HashMap<String, Instituicao>();
	}	
	
	public static synchronized Pessoal obterInstancia() {
		if (instancia == null)
			instancia =  new Pessoal();
		
		return instancia;
	}
		
	public Usuario criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Usuario usuario = Usuario.criarUsuario(nome, ultimoNome, email, senha, instituicao);
		autenticaveis.put(email, usuario);
		return usuario;
    }
		
	public void atualizarUsuario(Usuario usuario, String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws ExcecaoDeCadastro{
		if(usuario.getEmail().compareTo(email) != 0)
			validarEmailComoUnico(email);
		
		usuario.atualizarDados(email, senha, nome, ultimoNome, instituicao);
	}
	
	public void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		validarEmailComoUnico(email);
		
		Administrador administrador = Administrador.criarAdministrador(email, senha);
		autenticaveis.put(email, administrador);
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
			throw new ExcecaoDeCadastro("pessoal.autenticavel.senha_incorreta");
		
		return autenticavel;
	}
	
	public Autenticavel obterAutenticavelPorEmail(String email) throws ExcecaoDeCadastro{
		Autenticavel autenticavel = autenticaveis.get(email);
		
		if(autenticavel == null)
			throw new ExcecaoDeCadastro("pessoal.autenticavel.nao_localizado");
		
		return autenticavel;
	}
	
	public Usuario obterUsuarioPorEmail(String email) throws ExcecaoDeCadastro{
		Usuario usuario;
		Autenticavel autenticavel = autenticaveis.get(email);
		
		if(Usuario.class.isInstance(autenticavel))
			usuario = (Usuario) autenticavel;
		else
			throw new ExcecaoDeCadastro("pessoal.email.nao_se_refere_a_um_usuario");
			
		if(autenticavel == null)
			throw new ExcecaoDeCadastro("pessoal.autenticavel.nao_localizado");
		
		return usuario;
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

	
	public Instituicao criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		validarSiglaComoUnica(sigla);
		
		Instituicao instituicao = Instituicao.criarInstituicao(nome, sigla, localizacao);
        instituicoes.put(instituicao.getSigla(), instituicao);
        return instituicao;
    }
	
	public Collection<Instituicao> obterInstituicoes(){
		return instituicoes.values();
	}
	
	public Instituicao obterInstituicaoPorSigla(String sigla) throws ExcecaoDeCadastro{
		Instituicao instituicao = instituicoes.get(sigla); 
		
		if(instituicao == null)
			throw new ExcecaoDeCadastro("pessoal.instituicao.nao_localizado");
		
		return instituicao;
	}
	
	public void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		if(sigla.compareTo(instituicao.getSigla()) != 0)
			validarSiglaComoUnica(sigla);
		
		instituicao.atualizarDados(nome, sigla, localizacao);
	}
	
	
	public void removerInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
		Collection<Evento> eventos = CatalagoDeEventos.obterInstancia().obterEventos();
		Collection<Usuario> usuarios = obterUsuarios();
		
		for (Evento evento : eventos) {
			if(evento.getInstituicao().compareTo(instituicao) == 0)
				throw new ExcecaoDeCadastro("pessoal.instituicao.vinculada_a_evento");
		}
		
		for (Usuario usuario: usuarios) {
			if(usuario.getInstituicao().compareTo(instituicao) == 0)
				throw new ExcecaoDeCadastro("pessoal.instituicao.vinculada_a_usuario");
		}
		
		instituicoes.remove(instituicao.getSigla());
	}
	
	
	private void validarSiglaComoUnica(String sigla) throws ExcecaoDeCadastro{
        if(instituicoes.containsKey(sigla))
        	throw new ExcecaoDeCadastro("pessoal.sigla.existente");
	}
	
	
	private void validarEmailComoUnico(String email) throws ExcecaoDeCadastro{
		if(autenticaveis.containsKey(email))
			throw new ExcecaoDeCadastro("pessoal.email.existente");
	}
	
}
