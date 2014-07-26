package controladorGRASP;

import catalago.CatalagoDeEventos;
import catalago.Pessoal;
import dominio.Administrador;
import dominio.Evento;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public final class ControladorAdministrativo {
	
	public static void deferirEvento(Evento evento) {
		CatalagoDeEventos.obterInstancia().deferirEvento(evento);
	}
	
	public static void indeferirEvento(Evento evento) {
		CatalagoDeEventos.obterInstancia().indeferirEvento(evento);
	}
	
	public static void cancelarEvento(Evento evento) {
		CatalagoDeEventos.obterInstancia().cancelarEvento(evento);
	}
	
	public static void finalizarEvento(Evento evento) {
		CatalagoDeEventos.obterInstancia().finalizarEvento(evento);
	}
	
	public static void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		Pessoal.obterInstancia().atualizarInstituicao(instituicao, nome, sigla, localizacao);
	}
	
	public static Administrador criarAdministrador(String email, String senha) throws ExcecaoDeCadastro{
		return Pessoal.obterInstancia().criarAdministrador(email, senha);
	}
}
