package controladorGRASP;

import catalago.CatalagoDeEventos;
import catalago.CatalagoDeInstituicoes;
import dominio.Evento;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public class ControladorAdministrativo {
	
	private CatalagoDeEventos catalagoDeEventos = CatalagoDeEventos.obterInsancia();
	private CatalagoDeInstituicoes catalagoDeInstituicoes = CatalagoDeInstituicoes.obterInstancia();

	public void deferirEvento(Evento evento) {
		catalagoDeEventos.deferirEvento(evento);
	}
	
	public void indeferirEvento(Evento evento) {
		catalagoDeEventos.indeferirEvento(evento);
	}
	
	public void cancelarEvento(Evento evento) {
		catalagoDeEventos.cancelarEvento(evento);
	}
	
	public void finalizarEvento(Evento evento) {
		catalagoDeEventos.finalizarEvento(evento);
	}
	
	public void atualizarInstituicao(Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		catalagoDeInstituicoes.atualizarInstituicao(instituicao, nome, sigla, localizacao);
	}
}
