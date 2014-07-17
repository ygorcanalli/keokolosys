package participacao;

import util.AbstractControle;


public class ControleSubmeterVersaoFinalDeTrabalho implements AbstractControle {
	private AbstractGUISubmeterVersaoFinalDeTrabalho viewSubmeterVersaoFinalDeTrabalho;

	AbstractControle caller;
	SwingSubmeterVersaoFinalDeTrabalho swingSubmeterVersaoFinalDeTrabalho = new SwingSubmeterVersaoFinalDeTrabalho();
	public ControleSubmeterVersaoFinalDeTrabalho(AbstractControle caller) {
		this.caller = caller;
		inicializarGUI();
		
	}
	
	@Override
	public void inicializarGUI() {
		swingSubmeterVersaoFinalDeTrabalho = new SwingSubmeterVersaoFinalDeTrabalho();
		tornarGUIVisivel();
		
	}

	@Override
	public void tornarGUIVisivel() {
		swingSubmeterVersaoFinalDeTrabalho.inicializar();
		
	}

	@Override
	public void tornarGUIInvisivel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bloquearGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desbloquearGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarGUI() {
		// TODO Auto-generated method stub
		
	}
}
