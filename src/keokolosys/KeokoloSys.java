package keokolosys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cadastro.ControleLogin;
import catalago.CatalagoDeEventos;
import controladorGRASP.ControladorDeAvaliacao;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.BancaExaminadora;
import dominio.EstadoAvaliacao;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeAvaliacao;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public class KeokoloSys {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		inicializarDados();

		ControleLogin controleLogin = new ControleLogin();
		controleLogin.inicializarGUI();
	}
	
	@SuppressWarnings("deprecation")
	private static void inicializarDados() throws ExcecaoDeCadastro, ExcecaoDeAvaliacao, ExcecaoDeParticipacao {
		Instituicao ufrrj = ControladorDeCadastro.criarInstituicao("Universidade Federal Rural do Rio de Janeiro", "UFRRJ", "Av. Gov. Roberto Silveira S/N");
		
		ControladorDeCadastro.criarAdministrador("admin@keokolo.com", "admin");
		Usuario chair = ControladorDeCadastro.criarUsuario("chair@keokolo.com", "chair", "Usuario", "Chair", ufrrj);
		Usuario participante = ControladorDeCadastro.criarUsuario("participante@keokolo.com", "participante", "Usuario", "Participante", ufrrj);
		Usuario examinador1 = ControladorDeCadastro.criarUsuario("examinador1@keokolo.com", "examinador1", "Usuario", "Examinador Um", ufrrj);
		Usuario examinador2 = ControladorDeCadastro.criarUsuario("examinador2@keokolo.com", "examinador2", "Usuario", "Examinador Dois", ufrrj);
		Usuario examinador3 = ControladorDeCadastro.criarUsuario("examinador3@keokolo.com", "examinador3", "Usuario", "Examinador Três", ufrrj);
		

		Evento eventoPrimeiro = ControladorDeCadastro.criarEvento("Primeira Apresentacao de Projeto de Sistemas", ufrrj, chair, new Date(2014, 07, 01), new Date(2014,07,02), new Date(2014,8,01), new Date(2014,8,02));
		Evento eventoFinal = ControladorDeCadastro.criarEvento("Apresentacao Final de Projeto de Sistemas", ufrrj, chair, new Date(2014, 07, 01), new Date(2014,07,02), new Date(2014,8,01), new Date(2014,8,02));
		CatalagoDeEventos.obterInstancia().deferirEvento(eventoPrimeiro);
		CatalagoDeEventos.obterInstancia().deferirEvento(eventoFinal);
		
		eventoPrimeiro.concederPrivilegioDeExaminador(examinador1);
		eventoPrimeiro.concederPrivilegioDeExaminador(examinador2);
		eventoPrimeiro.concederPrivilegioDeExaminador(examinador3);
		
		PerfilDeExaminador perfilExaminador1 = (PerfilDeExaminador) examinador1.obterPerfilDe(eventoPrimeiro, PerfilDeExaminador.class);	
		PerfilDeExaminador perfilExaminador2 = (PerfilDeExaminador) examinador2.obterPerfilDe(eventoPrimeiro, PerfilDeExaminador.class);
		PerfilDeExaminador perfilExaminador3 = (PerfilDeExaminador) examinador3.obterPerfilDe(eventoPrimeiro, PerfilDeExaminador.class);
		
		Collection<PerfilDeExaminador> examinadores = new ArrayList<>();
		examinadores.add(perfilExaminador1);
		examinadores.add(perfilExaminador2);		
		examinadores.add(perfilExaminador3);
		BancaExaminadora bancaExaminadora = eventoPrimeiro.criarBancaExaminadora(examinadores);	
		
		eventoPrimeiro.inscreverParticipante(participante);	
		PerfilDeParticipante perfilParticipante = (PerfilDeParticipante) participante.obterPerfilDe(eventoPrimeiro, PerfilDeParticipante.class);
		Trabalho trabalho = ControladorDeParticipacao.subtmeterTrabalho(eventoPrimeiro, perfilParticipante, "KeokoloSys 1.0",  "Eventos cientificos", "Tony Stark", "/home/stark/ironMan.pdf");

		bancaExaminadora.associarTrabalho(trabalho);
		
		ControladorDeAvaliacao.avaliarTrabalho(trabalho, perfilExaminador1, EstadoAvaliacao.ACEITO);
		ControladorDeAvaliacao.avaliarTrabalho(trabalho, perfilExaminador2, EstadoAvaliacao.REJEITADO);
	}

}
