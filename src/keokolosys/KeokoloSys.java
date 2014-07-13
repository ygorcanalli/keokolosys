package keokolosys;

import java.util.Date;

import administrativo.ControleCadastrarInstituicao;
import util.Sessao;
import cadastro.ControleAtualizarUsuario;
import cadastro.ControleCadastrarEvento;
import cadastro.ControleLogin;
import cadastro.ControleUsuarioHome;
import catalago.CatalagoDeEventos;
import controladorGRASP.ControladorAdministrativo;
import controladorGRASP.ControladorDeCadastro;
import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class KeokoloSys {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		ControleCadastrarEvento c;
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
			
			/*ControleLogin controleLogin = new ControleLogin();
			controleLogin.inicializarGUI();*/
			
			Sessao.iniciarSessao(ygor);
			ControleAtualizarUsuario ca = new ControleAtualizarUsuario(null);
			ca.inicializarGUI();

			
			//Sessao.iniciarSessao(ygor);
			//c = new ControleCadastrarEvento(null);
			//c.inicializarGUI();
			
		} catch (ExcecaoDeCadastro e) {

			e.printStackTrace();
		}
				
		
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
