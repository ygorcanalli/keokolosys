package keokolosys;

import java.util.Collection;

public interface AbstractGUICadastrarEvento extends AbstractGUI {
	
	public void tonarVisivel();
	public void fechar();
	public void carregarInstituicoes(Collection<String> instituicoes);
	public void definirUsuarioResponsavel(String nomeDoUsuarioResponsavel);
	public void criarEvento();
	
	

}
