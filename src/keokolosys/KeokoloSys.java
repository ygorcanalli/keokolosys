package keokolosys;

import java.util.Date;

import cadastro.ControleCadastrarEvento;
import cadastro.ControleUsuarioHome;
import catalago.CatalagoDeEventos;
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
		
		try {
			ControladorDeCadastro.criarInstituicao("Rural", "UFRRJ", "NI");
			ControladorDeCadastro.criarInstituicao("Queridinha", "UFRJ", "Fundão");
			ControladorDeCadastro.criarInstituicao("Poderosa", "COPPE", "Fundão");
			
			i = ControladorDeCadastro.obterTodasInstituicoes().iterator().next();
			ControladorDeCadastro.criarUsuario("ygor.canalli@gmail.com", "rogy", "Ygor", "Canalli", i);
			u = ControladorDeCadastro.obterTodosUsuarios().iterator().next();
			
			c = new ControleCadastrarEvento(u);
			c.inicializarGUI();
			
		} catch (ExcecaoDeCadastro e) {

			e.printStackTrace();
		}
	
		

		/*try {
			ControladorDeCadastro.criarInstituicao("RUral", "UFRRJ", "Rua S/N");
		} catch (ExcecaoDeCadastro e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ControladorDeCadastro.criarUsuario("Hugo", "Rebelo", "email", "senha", ControladorDeCadastro.obterTodasInstituicoes().iterator().next());
		} catch (ExcecaoDeCadastro e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for (int cout = 0; cout < 10; cout++) {
				ControladorDeCadastro.criarEvento("Evento " + cout , ControladorDeCadastro.obterTodasInstituicoes().iterator().next(), ControladorDeCadastro.obterTodosUsuarios().iterator().next(), new Date(2014, 1, 1), new Date(2014, 2, 1), new Date(2014, 3, 1), new Date(2014, 4, 1));	
			}	
		} catch (ExcecaoDeCadastro e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ControleUsuarioHome c = new ControleUsuarioHome();
		c.inicializarGUI();*/
		
	}

}
