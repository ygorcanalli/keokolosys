package keokolosys;

import java.util.Date;

import util.Sessao;
import cadastro.ControleCadastrarEvento;
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
			ControladorDeCadastro.criarInstituicao("Rural", "UFRRJ", "NI");
			ControladorDeCadastro.criarInstituicao("Queridinha", "UFRJ", "Fundão");
			ControladorDeCadastro.criarInstituicao("Poderosa", "COPPE", "Fundão");
			
			i = ControladorDeCadastro.obterTodasInstituicoes().iterator().next();
			Usuario user = ControladorDeCadastro.criarUsuario("ygor.canalli@gmail.com", "rogy", "Ygor", "Canalli", i);
			Sessao.iniciarSessao(user);
			c = new ControleCadastrarEvento(null);
			c.inicializarGUI();
			
		} catch (ExcecaoDeCadastro e) {

			e.printStackTrace();
		}
		
		

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
		
		ControleUsuarioHome controleUsuarioHome = new ControleUsuarioHome();
		controleUsuarioHome.inicializarGUI();*/
		
	}

}
