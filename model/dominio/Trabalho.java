package dominio;

import java.util.ArrayList;
import java.util.Collection;

import excecao.ExcecaoDeAvaliacao;
import excecao.ExcecaoDeParticipacao;


public class Trabalho {
    private PerfilDeParticipante submissor;
    private String titulo;
    private String resumo;
    private String autores;
    private String caminhoArquivoSubmissao;
    private String caminhoArquivoFinal;
    private Collection<Avaliacao> avaliacoes;
    private Boolean flagtrabalhoComVersaoFinal;
    private BancaExaminadora bancaExaminadoraResponsavel;

    private Trabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao){
            this.submissor = submissor;
            this.titulo = titulo;
            this.resumo = resumo;
            this.autores = autores;
            this.caminhoArquivoSubmissao = caminhoArquivoSubmissao;
            this.caminhoArquivoFinal = "";
            this.avaliacoes = new ArrayList<Avaliacao>();
            this.flagtrabalhoComVersaoFinal = false;
    }
    
    public static Trabalho criarTrabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
    	validarDados(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
    	return new Trabalho(submissor, titulo, resumo, autores, caminhoArquivoSubmissao);
    }


    
    public EstadoAvaliacao obterEstado() {
		Integer frequenciaAceito = 0;
		Integer frequenciaRejeitado = 0;
		
		if (avaliacoes.size() < bancaExaminadoraResponsavel.obterNumeroDeExaminadores())
			return EstadoAvaliacao.NAO_AVALIADO;
		
		for (Avaliacao avaliacao : avaliacoes) {
			if(avaliacao.getResultado() == EstadoAvaliacao.ACEITO)
				frequenciaAceito++;
			else
				frequenciaRejeitado++;			
		}
		
		if(frequenciaAceito > frequenciaRejeitado)
			return EstadoAvaliacao.ACEITO;
		
		return EstadoAvaliacao.REJEITADO;
    }
    
    
  	public void submeterVersaoFinal(String caminhoArquivoFinal) throws ExcecaoDeParticipacao {
  		validarArquivoVersaoFinal(caminhoArquivoFinal);
  		
  		this.caminhoArquivoFinal = caminhoArquivoFinal;
  		this.flagtrabalhoComVersaoFinal = true;
  	}
  	
  	public void atribuirAvaliacao(Avaliacao avaliacao) throws ExcecaoDeAvaliacao {
  		validarAvaliacao(avaliacao);
  		avaliacoes.add(avaliacao);
  	}


    private static void validarDados(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeParticipacao{
        Boolean submissorVazio = (submissor == null);
        Boolean tituloVazio = (titulo == null || titulo.equals(""));
        Boolean resumoVazio = (resumo == null || resumo.equals(""));
        Boolean autoresVazio = (autores == null || autores.equals(""));
        Boolean caminhoArquivoSubmissaoVazio = (caminhoArquivoSubmissao == null || caminhoArquivoSubmissao .equals(""));
        
        if(submissorVazio)
        	throw new ExcecaoDeParticipacao("trabalho.submissor.vazio");
        
        if(tituloVazio)
        	throw new ExcecaoDeParticipacao("trabalho.titulo.vazio");
        
        if(resumoVazio)
        	throw new ExcecaoDeParticipacao("trabalho.resumo.vazio");
        
        if(autoresVazio)
        	throw new ExcecaoDeParticipacao("trabalho.autores.vazio");
        
        if(caminhoArquivoSubmissaoVazio)
        	throw new ExcecaoDeParticipacao("trabalho.arquivo_submissao.vazio");

    }

    private void validarArquivoVersaoFinal(String caminhoArquivoFinal) throws ExcecaoDeParticipacao{
        Boolean caminhoArquivoFinalVazio = (caminhoArquivoFinal == null || caminhoArquivoFinal.equals(""));
        Boolean versaoFinalEnviada = trabalhoComVersaoFinal();

        if(caminhoArquivoFinalVazio)
        	throw new ExcecaoDeParticipacao("trabalho.versao_final.caminho_vazio");
        
        if(versaoFinalEnviada)
        	throw new ExcecaoDeParticipacao("trabalho.versao_final.ja_enviada");
    }

    private void validarAvaliacao(Avaliacao avaliacao) throws ExcecaoDeAvaliacao {
		Boolean avaliacaoVazia = avaliacao == null;
		Boolean avaliacaoTrabalhoInvalido = avaliacao.getTrabalho() == this;
		
		if (avaliacaoVazia)
			throw new ExcecaoDeAvaliacao("trabalho.avaliacao.vazia");
		
		if (avaliacaoTrabalhoInvalido)
			throw new ExcecaoDeAvaliacao("trabalho.avaliacao.trabalho.invalido");
		
	}

	public Boolean trabalhoComVersaoFinal(){
        return flagtrabalhoComVersaoFinal;
    }

    public PerfilDeParticipante getSubmissor() {
        return submissor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getAutores() {
        return autores;
    }

    public String getCaminhoArquivoSubmissao() {
        return caminhoArquivoSubmissao;
    }

    public String getCaminhoArquivoFinal() {
        return caminhoArquivoFinal;
    }
    
    public BancaExaminadora getBancaExaminadoraResponsavel() {
    	return bancaExaminadoraResponsavel;
    }
    
    public Boolean associadoABancaExaminadora(){
    	return this.bancaExaminadoraResponsavel != null;
    }
    
    public void atribuirBancaExaminadoraResponsavel(BancaExaminadora bancaExaminadoraResponsavel) {
    	this.bancaExaminadoraResponsavel = bancaExaminadoraResponsavel;
    }
}
