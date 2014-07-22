package keokolosys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import avaliacao.ControleCadastrarBancaExaminadora;
import util.Sessao;
import cadastro.ControleLogin;
import cadastro.ControleUsuarioHome;
import catalago.CatalagoDeEventos;
import controladorGRASP.ControladorDeCadastro;
import controladorGRASP.ControladorDeParticipacao;
import dominio.BancaExaminadora;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;

public class KeokoloSysTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ControleLogin controleLogin = new ControleLogin();
		//controleLogin.inicializarGUI();
		
		
		
		ControladorDeCadastro.criarInstituicao("Stark Industries,", "SI", "USA");
		Instituicao si = ControladorDeCadastro.obterInstituicaoPorSigla("SI");
		ControladorDeCadastro.criarAdministrador("god@ceu.com", "god");
		
		Usuario stark = ControladorDeCadastro.criarUsuario("stark@stark.com", "stark", "Tony", "Stark", si);
		Usuario pepper = ControladorDeCadastro.criarUsuario("peeper@stark.com", "stark", "Virginia", "Pepper", si);
		Usuario obadiah = ControladorDeCadastro.criarUsuario("obadiah@stark.com", "stark", "Obadiah", "Stane", si);
		Usuario rhodey = ControladorDeCadastro.criarUsuario("rhodey@USAForce.com", "USAForce", "James", "Rhodey", si);
		Usuario hulk = ControladorDeCadastro.criarUsuario("hulk@hulk.com", "stark", "Bruce", "Banner", si);
		Usuario nick = ControladorDeCadastro.criarUsuario("nick@SHIELD.com", "shield", "nick", "fury", si);
		
		
		Evento evento = ControladorDeCadastro.criarEvento("Evento Iron Man", si, nick, new Date(2014, 07, 01), new Date(2014,07,02), new Date(2014,8,01), new Date(2014,8,02));
		CatalagoDeEventos.obterInstancia().deferirEvento(evento);
		
		evento.concederPrivilegioDeExaminador(obadiah);
		evento.concederPrivilegioDeExaminador(rhodey);
		evento.concederPrivilegioDeExaminador(hulk);
		
		PerfilDeExaminador perfilRhodey = (PerfilDeExaminador) rhodey.obterPerfilDe(evento, PerfilDeExaminador.class);	
		PerfilDeExaminador perfilObadiah = (PerfilDeExaminador) obadiah.obterPerfilDe(evento, PerfilDeExaminador.class);
		PerfilDeExaminador perfilHulk = (PerfilDeExaminador) hulk.obterPerfilDe(evento, PerfilDeExaminador.class);
		
		evento.inscreverParticipante(pepper);
		
		PerfilDeParticipante perfilPepper = (PerfilDeParticipante) pepper.obterPerfilDe(evento, PerfilDeParticipante.class);
		
		ControladorDeParticipacao.subtmeterTrabalho(evento,perfilPepper, "Mark IV", "Traje de defesa pessoal, Mark IV", "Tony Stark","/home/stark/ironMan.pdf");
		Collection<Trabalho> trabalhos = ControladorDeParticipacao.obterTodosTrabalhosSubmetidosPeloParticipante(evento, perfilPepper);
		Collection<PerfilDeExaminador> examinadores = new ArrayList<>();
		
		examinadores.add(perfilHulk);
		examinadores.add(perfilObadiah);		
		examinadores.add(perfilRhodey);
		BancaExaminadora bancaExaminadora = evento.criarBancaExaminadora(examinadores);	
		/*bancaExaminadora.associarTrabalho(markIV);
		
		ControladorDeAvaliacao.avaliarTrabalho(markIV, perfilPepper, EstadoAvaliacao.ACEITO);
		ControladorDeAvaliacao.avaliarTrabalho(markIV, perfilObadiah, EstadoAvaliacao.REJEITADO);*/
		
		
		Sessao.iniciarSessao(stark);
		//new ControleUsuarioHome(controleLogin).inicializarGUI();
		
		new ControleCadastrarBancaExaminadora(null, evento).inicializarGUI();
		
		
		/*new ControleAvaliarTrabalho(null, markIV,evento);*/
		/*ControleCadastrarEvento c;
		Instituicao i;
		Usuario u;
		Evento evento;
		
		
		try {
			ControladorDeCadastro.criarInstituicao("Universidade Federal Rural do Rio de Janeiro", "UFRRJ", "Seropedica");
			ControladorDeCadastro.criarInstituicao("Universidade Federal do Rio de Janeiro", "UFRJ", "Fund�o");
			ControladorDeCadastro.criarInstituicao("Universidade Federal do Estado do Rio de Janeiro", "UNIRIO", "Urca");
			ControladorDeCadastro.criarInstituicao("Universidade Federal Fluminense", "UFF", "Niteroi");
			
			Instituicao rural = ControladorDeCadastro.obterInstituicaoPorSigla("UFRRJ");
			Instituicao ufrj = ControladorDeCadastro.obterInstituicaoPorSigla("UFRJ");
			
			Usuario ygor = ControladorDeCadastro.criarUsuario("ygor.canalli@gmail.com", "rogy", "Ygor", "Canalli", ufrj);
			Usuario alexsander = ControladorDeCadastro.criarUsuario("alexsander.a.m@gmail.com", "rogy", "Alexsander", "Melo", rural);
			
			ControleLogin controleLogin = new ControleLogin();
			controleLogin.inicializarGUI();
			
			Sessao.iniciarSessao(ygor);
			ControleAtualizarUsuario ca = new ControleAtualizarUsuario(null);
			ca.inicializarGUI();

			
			//Sessao.iniciarSessao(ygor);
			//c = new ControleCadastrarEvento(null);
			//c.inicializarGUI();
			
		} catch (ExcecaoDeCadastro e) {

			e.printStackTrace();
		}*/
				
		
		/*ControleCadastrarInstituicao controleCadastrarInstituicao = new ControleCadastrarInstituicao(null);
		controleCadastrarInstituicao.inicializarGUI();*/
		

		/*try {
			i = ControladorDeCadastro.criarInstituicao("Rural", "UFRRJ", "Rua S/N");
			u = ControladorDeCadastro.criarUsuario("Hugo", "Rebelo", "email", "senha", i);
			
			for (int count = 0; count < 10; count++) {
				evento = ControladorDeCadastro.criarEvento("Evento " + count , i, u,  new Date(2014, 2, 1), new Date(2014, 3, 1), new Date(2014, 4, 1), new Date(2014, 5, 1));
				ControladorAdministrativo.deferirEvento(evento);
			}
			
			Sessao.iniciarSessao(u);
		} catch (ExcecaoDeCadastro e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ControleUsuarioHome controleUsuarioHome = new ControleUsuarioHome(null);
		controleUsuarioHome.inicializarGUI();*/

	}

}
