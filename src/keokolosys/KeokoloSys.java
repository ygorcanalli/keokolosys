package keokolosys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import administrativo.ControleCadastrarInstituicao;
import avaliacao.ControleAvaliarTrabalho;
import util.Sessao;
import cadastro.ControleAtualizarUsuario;
import cadastro.ControleCadastrarEvento;
import cadastro.ControleLogin;
import cadastro.ControleUsuarioHome;
import catalago.CatalagoDeEventos;
import controladorGRASP.ControladorAdministrativo;
import controladorGRASP.ControladorDeAvaliacao;
import controladorGRASP.ControladorDeCadastro;
import dominio.BancaExaminadora;
import dominio.EstadoAvaliacao;
import dominio.Evento;
import dominio.Instituicao;
import dominio.PerfilDeExaminador;
import dominio.PerfilDeParticipante;
import dominio.Trabalho;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;
import excecao.ExcecaoDeParticipacao;

public class KeokoloSys {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ControladorDeCadastro.criarInstituicao("Stark Industries,", "SI", "USA");
		Instituicao si = ControladorDeCadastro.obterInstituicaoPorSigla("SI");
		
		Usuario stark = ControladorDeCadastro.criarUsuario("stark@stark.com", "stark", "Tony", "Stark", si);
		Usuario pepper = ControladorDeCadastro.criarUsuario("peeper@stark.com", "stark", "Virginia", "Pepper", si);
		Usuario obadiah = ControladorDeCadastro.criarUsuario("obadiah@stark.com", "stark", "Obadiah", "Stane", si);
		Usuario rhodey = ControladorDeCadastro.criarUsuario("rhodey@USAForce.com", "USAForce", "James", "Rhodey", si);
		
		Sessao.iniciarSessao(rhodey);
		
		Evento evento = ControladorDeCadastro.criarEvento("Evento Iron Man", si, stark, new Date(2014, 07, 01), new Date(2014,07,02), new Date(2014,8,01), new Date(2014,8,02));
		
		evento.concederPrivilegioDeExaminador(obadiah);
		evento.concederPrivilegioDeExaminador(rhodey);
		evento.concederPrivilegioDeExaminador(pepper);		
		
		
		
		PerfilDeExaminador perfilrhodey = (PerfilDeExaminador) rhodey.obterPerfilDe(evento, PerfilDeExaminador.class);	
		PerfilDeExaminador perfilPepper = (PerfilDeExaminador) pepper.obterPerfilDe(evento, PerfilDeExaminador.class);
		PerfilDeExaminador perfilObadiah = (PerfilDeExaminador) obadiah.obterPerfilDe(evento, PerfilDeExaminador.class);
		
		PerfilDeParticipante perfilParticipante = new PerfilDeParticipante(stark, evento);
		
		Trabalho markIV = Trabalho.criarTrabalho(perfilParticipante, "Mark IV", "Traje de defesa pessoal, Mark IV", "Tony Stark","/home/stark/ironMan.pdf");
		
		Collection<PerfilDeExaminador> examinadores = new ArrayList<>();
		
		examinadores.add(perfilObadiah);
		examinadores.add(perfilPepper);		
		examinadores.add(perfilrhodey);
		BancaExaminadora bancaExaminadora = evento.criarBancaExaminadora(examinadores);	
		bancaExaminadora.associarTrabalho(markIV);
		
		ControladorDeAvaliacao.avaliarTrabalho(markIV, perfilPepper, EstadoAvaliacao.ACEITO);
		ControladorDeAvaliacao.avaliarTrabalho(markIV, perfilObadiah, EstadoAvaliacao.REJEITADO);
		new ControleAvaliarTrabalho(null, markIV,evento);
		/*ControleCadastrarEvento c;
		Instituicao i;
		Usuario u;
		Evento evento;
		
		
		try {
			ControladorDeCadastro.criarInstituicao("Universidade Federal Rural do Rio de Janeiro", "UFRRJ", "Seropedica");
			ControladorDeCadastro.criarInstituicao("Universidade Federal do Rio de Janeiro", "UFRJ", "Fundão");
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
