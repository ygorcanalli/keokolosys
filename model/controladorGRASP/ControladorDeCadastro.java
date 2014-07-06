package controladorGRASP;

import java.util.Collection;
import java.util.Date;

import catalago.CatalagoDeAutenticaveis;
import catalago.CatalagoDeEventos;
import catalago.CatalagoDeInstituicoes;
import dominio.Administrador;
import dominio.Autenticavel;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;


public class ControladorDeCadastro {
	
	private CatalagoDeEventos catalagoDeEventos = CatalagoDeEventos.obterInsancia();
	private CatalagoDeInstituicoes catalagoDeInstituicoes = CatalagoDeInstituicoes.obterInstancia();
	private CatalagoDeAutenticaveis catalagoDeAutenticaveis = CatalagoDeAutenticaveis.obterInstancia();

	public void criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
		catalagoDeEventos.criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
	}
	
	public Collection<Evento> obterTodosEventosDeferidos() {
		return catalagoDeEventos.obterEventosDeferidos();
	}
	
	public Collection<Evento> obterTodosEventosIndeferidos() {
		return catalagoDeEventos.obterEventosIndeferidos();
	}

	public Collection<Evento> obterTodosEventosAguardandoAprovacao() {
		return catalagoDeEventos.obterEventosAguardandoAprovacao();
	}
	
	public Collection<Evento> obterTodosEventos() {
		return catalagoDeEventos.obterEventos();
	}
	
	public void atualizarDadosDoEvento(Evento evento, Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
		catalagoDeEventos.atualizarDadosEvento(evento, instituicao, nome, dataDeInicio, dataDeFim, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos);
	}
	
	public void criarUsuario(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.criarUsuario(email, senha, nome, ultimoNome, instituicao);
	}

	public void criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.criarAdministrador(email, senha);
	}
	
	public void atualizarDadosDoUsuario(Usuario usuario, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.atualizarUsuario(usuario, email, senha, nome, ultimoNome, instituicao);
	}
	
	public void atualizarDadosDoAdministrador(Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.atualizarAdministrador(administrador, email, senha);
	}
	
	public Collection<Autenticavel> obterTodosAutenticaveis(){
		return catalagoDeAutenticaveis.obterAutenticaveis();
	}
	
	public Collection<Usuario> obterTodosUsuarios(){
		return catalagoDeAutenticaveis.obterUsuarios();
	}
	
	public Collection<Administrador> obterTodosAdministradores(){
		return catalagoDeAutenticaveis.obterAdministradores();
	}
	
	public void criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		catalagoDeInstituicoes.criarInstituicao(nome, sigla, localizacao);
	}
	
	public Collection<Instituicao> obterTodasInstituicoes(){
		return catalagoDeInstituicoes.obterInstituicoes();
	}
	
	public Autenticavel entrarNoSistema(String email, String senha) throws ExcecaoDeCadastro{
		return catalagoDeAutenticaveis.entrarNoSistema(email, senha);
	}
}
