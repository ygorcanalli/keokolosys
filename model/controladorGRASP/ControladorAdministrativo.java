package controladorGRASP;

import catalago.MantenedorDeCatalagos;
import dominio.Evento;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public final class ControladorAdministrativo {
	
	public static void deferirEvento(Evento evento) {
		MantenedorDeCatalagos.obterInstancia().obterCatalagoDeEventos().deferirEvento(evento);
	}
	
	public static void indeferirEvento(Evento evento) {
		MantenedorDeCatalagos.obterInstancia().obterCatalagoDeEventos().indeferirEvento(evento);
	}
	
	public static void cancelarEvento(Evento evento) {
		MantenedorDeCatalagos.obterInstancia().obterCatalagoDeEventos().cancelarEvento(evento);
	}
	
	public static void finalizarEvento(Evento evento) {
		MantenedorDeCatalagos.obterInstancia().obterCatalagoDeEventos().finalizarEvento(evento);
	}
	
	public static void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		MantenedorDeCatalagos.obterInstancia().obterCatalagoDeInstituicoes().atualizarInstituicao(instituicao, nome, sigla, localizacao);
	}
}
