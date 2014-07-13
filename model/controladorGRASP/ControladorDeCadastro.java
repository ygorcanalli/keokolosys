package controladorGRASP;

import java.util.Collection;
import java.util.Date;

import catalago.CatalagoDeEventos;
import catalago.Pessoal;
import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;


public final class ControladorDeCadastro {
	
	public static Evento criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
		return CatalagoDeEventos.obterInstancia().criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
	}
	
	public static Collection<Evento> obterTodosEventosDeferidos() {
		return CatalagoDeEventos.obterInstancia().obterEventosDeferidos();
	}
	
	public static Collection<Evento> obterTodosEventosIndeferidos() {
		return CatalagoDeEventos.obterInstancia().obterEventosIndeferidos();
	}

	public static Collection<Evento> obterTodosEventosAguardandoAprovacao() {
		return CatalagoDeEventos.obterInstancia().obterEventosAguardandoAprovacao();
	}
	
	public static Collection<Evento> obterTodosEventos() {
		return CatalagoDeEventos.obterInstancia().obterEventos();
	}
	
	public static void atualizarDadosDoEvento(Evento evento, Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
		evento.atualizarDados(instituicao, nome, dataDeInicio, dataDeFim, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos);
	}
	
	public static Usuario criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().criarUsuario(email, senha, nome, ultimoNome, instituicao);
	}

	public static void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().criarAdministrador(email, senha);
	}
	
	public static void atualizarDadosDoUsuario(Usuario usuario, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().atualizarUsuario(usuario, nome, ultimoNome, email, senha, instituicao);
	}
	
	public static void atualizarDadosDoAdministrador(Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().atualizarAdministrador(administrador, email, senha);
	}
	
	public static Collection<Autenticavel> obterTodosAutenticaveis(){
		return Pessoal.obterInstancia().obterAutenticaveis();
	}
	
	public static Autenticavel obterAutenticavelPorEmail(String email) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().obterAutenticavelPorEmail(email);
	}
	
	public static Usuario obterUsuarioPorEmail(String email) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().obterUsuarioPorEmail(email);
	}
	
	public static Collection<Usuario> obterTodosUsuarios(){
		return Pessoal.obterInstancia().obterUsuarios();
	}
	
	public static Collection<Administrador> obterTodosAdministradores(){
		return Pessoal.obterInstancia().obterAdministradores();
	}
	
	public static Instituicao criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().criarInstituicao(nome, sigla, localizacao);
	}
	
	public static void atualizarDadosDaInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().atualizarInstituicao(instituicao, nome, sigla, localizacao);
	}
	
	public static void removerInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().removerInstituicao(instituicao);
	}
	
	public static Collection<Instituicao> obterTodasInstituicoes(){
		return Pessoal.obterInstancia().obterInstituicoes();
	}
	
	public static Instituicao obterInstituicaoPorSigla(String sigla) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().obterInstituicaoPorSigla(sigla);
	}
	
	public static Autenticavel entrarNoSistema(String email, String senha) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().entrarNoSistema(email, senha);
	}
}
