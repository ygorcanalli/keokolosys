package controladorGRASP;

import catalago.CatalagoDeEventos;
import catalago.CatalagoDeInstituicoes;
import dominio.Evento;
import dominio.Instituicao;
import excecao.ExcecaoDeCadastro;

public class ControladorAdministrativo {

	public void deferirEvento(CatalagoDeEventos catalagoDeEventos, Evento evento) {
		catalagoDeEventos.deferirEvento(evento);
	}
	
	public void indeferirEvento(CatalagoDeEventos catalagoDeEventos, Evento evento) {
		catalagoDeEventos.indeferirEvento(evento);
	}
	
	public void cancelarEvento(CatalagoDeEventos catalagoDeEventos, Evento evento) {
		catalagoDeEventos.cancelarEvento(evento);
	}
	
	public void finalizarEvento(CatalagoDeEventos catalagoDeEventos, Evento evento) {
		catalagoDeEventos.finalizarEvento(evento);
	}
	
	public void atualizarInstituicao(CatalagoDeInstituicoes catalagoDeInstituicoes, Instituicao instituicao, String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
		catalagoDeInstituicoes.atualizarInstituicao(instituicao, nome, sigla, localizacao);
	}
}
