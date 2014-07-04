package keokolosys;

import java.util.ArrayList;
import java.util.Collection;


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

    private Trabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeCadastro{
            this.submissor = submissor;
            this.titulo = titulo;
            this.resumo = resumo;
            this.autores = autores;
            this.caminhoArquivoSubmissao = caminhoArquivoSubmissao;
            this.caminhoArquivoFinal = "";
            this.avaliacoes = new ArrayList<Avaliacao>();
            this.flagtrabalhoComVersaoFinal = false;
    }
    
    public static Trabalho criarTrabalho(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeCadastro{
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


    private static void validarDados(PerfilDeParticipante submissor, String titulo, String resumo, String autores, String caminhoArquivoSubmissao) throws ExcecaoDeCadastro{
        Boolean submissorVazio = (submissor == null);
        Boolean tituloVazio = (titulo == null || titulo.equals(""));
        Boolean resumoVazio = (resumo == null || resumo.equals(""));
        Boolean autoresVazio = (autores == null || autores.equals(""));
        Boolean caminhoArquivoSubmissaoVazio = (caminhoArquivoSubmissao == null || caminhoArquivoSubmissao .equals(""));
        
        if(submissorVazio)
        	throw new ExcecaoDeCadastro("trabalho.submissor.vazio");
        
        if(tituloVazio)
        	throw new ExcecaoDeCadastro("trabalho.titulo.vazio");
        
        if(resumoVazio)
        	throw new ExcecaoDeCadastro("trabalho.resumo.vazio");
        
        if(autoresVazio)
        	throw new ExcecaoDeCadastro("trabalho.autores.vazio");
        
        if(caminhoArquivoSubmissaoVazio)
        	throw new ExcecaoDeCadastro("trabalho.arquivo_submissao.vazio");

    }

    private void validarArquivoVersaoFinal(String caminhoArquivoFinal) throws ExcecaoDeCadastro{
        Boolean caminhoArquivoFinalVazio = (caminhoArquivoFinal == null || caminhoArquivoFinal.equals(""));
        Boolean versaoFinalEnviada = trabalhoComVersaoFinal();

        if(caminhoArquivoFinalVazio)
        	throw new ExcecaoDeCadastro("trabalho.versao_final.caminho_vazio");
        
        if(versaoFinalEnviada)
        	throw new ExcecaoDeCadastro("trabalho.versao_final.ja_enviada");
    }
    
	public void submeterVersaoFinal(String caminhoArquivoFinal) throws ExcecaoDeCadastro {
		validarArquivoVersaoFinal(caminhoArquivoFinal);
		
		this.caminhoArquivoFinal = caminhoArquivoFinal;
		this.flagtrabalhoComVersaoFinal = true;
	}
	
	public void atribuirAvaliacao(Avaliacao avaliacao) throws ExcecaoDeAvaliacao {
		validarAvaliacao(avaliacao);
		avaliacoes.add(avaliacao);
	}

    private void validarAvaliacao(Avaliacao avaliacao) throws ExcecaoDeAvaliacao {
		// TODO Auto-generated method stub
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
    protected void atribuirBancaExaminadoraResponsavel(BancaExaminadora bancaExaminadoraResponsavel) {
    	this.bancaExaminadoraResponsavel = bancaExaminadoraResponsavel;
    }
}
