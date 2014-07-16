package cadastro;

import transferobject.AutenticavelTO;
import util.AbstractGUI;

public interface AbstractGUILogin extends AbstractGUI{

	public void fechar();
	public void realizarLogin();
	public void realizarCadastro();
	public AutenticavelTO obterAutenticavel();
	
}
