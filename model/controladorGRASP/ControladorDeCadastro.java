package controladorGRASP;

import java.util.Collection;
import java.util.Date;

import catalago.MantenedorDeCatalagos;
import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;


public final class ControladorDeCadastro {
	
	public static void criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeEventos().criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
	}
	
	public static Collection<Evento> obterTodosEventosDeferidos() {
		return MantenedorDeCatalagos.obterCatalagoDeEventos().obterEventosDeferidos();
	}
	
	public static Collection<Evento> obterTodosEventosIndeferidos() {
		return MantenedorDeCatalagos.obterCatalagoDeEventos().obterEventosIndeferidos();
	}

	public static Collection<Evento> obterTodosEventosAguardandoAprovacao() {
		return MantenedorDeCatalagos.obterCatalagoDeEventos().obterEventosAguardandoAprovacao();
	}
	
	public static Collection<Evento> obterTodosEventos() {
		return MantenedorDeCatalagos.obterCatalagoDeEventos().obterEventos();
	}
	
	public static void atualizarDadosDoEvento(Evento evento, Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeEventos().atualizarDadosEvento(evento, instituicao, nome, dataDeInicio, dataDeFim, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos);
	}
	
	public static void criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().criarUsuario(email, senha, nome, ultimoNome, instituicao);
	}

	public static void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().criarAdministrador(email, senha);
	}
	
	public static void atualizarDadosDoUsuario(Usuario usuario, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().atualizarUsuario(usuario, email, senha, nome, ultimoNome, instituicao);
	}
	
	public static void atualizarDadosDoAdministrador(Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().atualizarAdministrador(administrador, email, senha);
	}
	
	public static Collection<Autenticavel> obterTodosAutenticaveis(){
		return MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().obterAutenticaveis();
	}
	
	public static Collection<Usuario> obterTodosUsuarios(){
		return MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().obterUsuarios();
	}
	
	public static Collection<Administrador> obterTodosAdministradores(){
		return MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().obterAdministradores();
	}
	
	public static void criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterCatalagoDeInstituicoes().criarInstituicao(nome, sigla, localizacao);
	}
	
	public static Collection<Instituicao> obterTodasInstituicoes(){
		return MantenedorDeCatalagos.obterCatalagoDeInstituicoes().obterInstituicoes();
	}
	
	public static Autenticavel entrarNoSistema(String email, String senha) throws ExcecaoDeCadastro{
		return MantenedorDeCatalagos.obterCatalagoDeAutenticaveis().entrarNoSistema(email, senha);
	}
}
