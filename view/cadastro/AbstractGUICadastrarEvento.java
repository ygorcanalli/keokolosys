package cadastro;

import java.sql.Date;
import java.util.Collection;

import util.AbstractGUI;

public interface AbstractGUICadastrarEvento extends AbstractGUI {
	
	public void tonarVisivel();
	public void fechar();
	public void carregarInstituicoes(Collection<String> instituicoes);
	public void definirUsuarioResponsavel(String nomeDoUsuarioResponsavel);
	public void criarEvento();
	
	public String obterNomeDoEvento();
	public Date obterDataDeInicioDoEvento();
	public Date obterDataDeFimDoevento();
	public Date obterDataMaximaParaSubmissaoDeTrabalho();
	public Date obterDataMaximaParaAceitacaoDeTrabalho();
	
}
