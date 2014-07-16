package avaliacao;

import java.util.Collection;

import excecao.ExcecaoDeAvaliacao;
import transferobject.AvaliacaoTO;
import util.AbstractGUI;

public interface AbstractGUIAvaliarTrabalho extends AbstractGUI{
	public void setTituloTrabalho(String titulo);
	public void setResumoTrabalho(String resumo);
	public void setNomeArquivo(String nomeArquivo);
	public void setAvaliacoes(Collection<AvaliacaoTO> nomeArquivo);
	public void avaliarTrabalho() throws ExcecaoDeAvaliacao;

}
