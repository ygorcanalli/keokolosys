package cadastro;

import java.util.Collection;
import java.util.Date;

import transferobject.InstituicaoTO;
import util.AbstractGUI;

public interface AbstractGUICadastrarEvento extends AbstractGUI {
	
	public void tonarVisivel();
	public void fechar();
	public void bloquear();
	public void habilitar();
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes);
	public void definirUsuarioResponsavel(String nomeDoUsuarioResponsavel);
	public void criarEvento();
	
	public String obterNomeDoEvento();
	public Date obterDataDeInicioDoEvento();
	public Date obterDataDeFimDoevento();
	public Date obterDataMaximaParaSubmissaoDeTrabalho();
	public Date obterDataMaximaParaAceitacaoDeTrabalho();
	public String obterInstituicao();
	
}
