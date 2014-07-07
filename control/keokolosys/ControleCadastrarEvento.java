package keokolosys;

import java.sql.Date;

public class ControleCadastrarEvento {
	private AbstractGUICadastrarEvento viewCadastroDeEvento;
	
	
	public void inicializarGUI(){
		viewCadastroDeEvento = new SwingCadastrarEvento(this);
		viewCadastroDeEvento.inicializar();
		viewCadastroDeEvento.tonarVisivel();
	}

	public void criarEvento(String nomeDoEvento, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos, Date dataDeInicioDoEvento, Date dataDeFimDoEvento) {
		
	}
	
	
}
