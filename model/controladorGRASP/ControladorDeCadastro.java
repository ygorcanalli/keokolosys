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

	public void criarEvento(CatalagoDeEventos catalagoDeEventos, String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
		catalagoDeEventos.criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
	}
	
	public Collection<Evento> obterTodosEventosDeferidos(CatalagoDeEventos catalagoDeEventos) {
		return catalagoDeEventos.obterEventosDeferidos();
	}
	
	public Collection<Evento> obterTodosEventosIndeferidos(CatalagoDeEventos catalagoDeEventos) {
		return catalagoDeEventos.obterEventosIndeferidos();
	}

	public Collection<Evento> obterTodosEventosAguardandoAprovacao(CatalagoDeEventos catalagoDeEventos) {
		return catalagoDeEventos.obterEventosAguardandoAprovacao();
	}
	
	public Collection<Evento> obterTodosEventos(CatalagoDeEventos catalagoDeEventos) {
		return catalagoDeEventos.obterEventos();
	}
	
	public void atualizarDadosDoEvento(CatalagoDeEventos catalagoDeEventos, Evento evento, Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
		catalagoDeEventos.atualizarDadosEvento(evento, instituicao, nome, dataDeInicio, dataDeFim, dataMaximaParaSubmissaoDeTrabalhos, dataMaximaParaAceitacaoDeTrabalhos);
	}
	
	public void criarUsuario(CatalagoDeAutenticaveis catalagoDeAutenticaveis, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.criarUsuario(email, senha, nome, ultimoNome, instituicao);
	}

	public void criarAdministrador(CatalagoDeAutenticaveis catalagoDeAutenticaveis, String email, String senha) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.criarAdministrador(email, senha);
	}
	
	public void atualizarDadosDoUsuario(CatalagoDeAutenticaveis catalagoDeAutenticaveis, Usuario usuario, String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.atualizarUsuario(usuario, email, senha, nome, ultimoNome, instituicao);
	}
	
	public void atualizarDadosDoAdministrador(CatalagoDeAutenticaveis catalagoDeAutenticaveis, Administrador administrador, String email, String senha) throws ExcecaoDeCadastro{
		catalagoDeAutenticaveis.atualizarAdministrador(administrador, email, senha);
	}
	
	public Collection<Autenticavel> obterTodosAutenticaveis(CatalagoDeAutenticaveis catalagoDeAutenticaveis){
		return catalagoDeAutenticaveis.obterAutenticaveis();
	}
	
	public Collection<Usuario> obterTodosUsuarios(CatalagoDeAutenticaveis catalagoDeAutenticaveis){
		return catalagoDeAutenticaveis.obterUsuarios();
	}
	
	public Collection<Administrador> obterTodosAdministradores(CatalagoDeAutenticaveis catalagoDeAutenticaveis){
		return catalagoDeAutenticaveis.obterAdministradores();
	}
	
	public void criarInstituicao(CatalagoDeInstituicoes catalagoDeInstituicoes, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		catalagoDeInstituicoes.criarInstituicao(nome, sigla, localizacao);
	}
	
	public Collection<Instituicao> obterTodasInstituicoes(CatalagoDeInstituicoes catalagoDeInstituicoes){
		return catalagoDeInstituicoes.obterInstituicoes();
	}
}
