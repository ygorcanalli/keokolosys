package transferobject;

public class TrabalhoTO {
	
	public enum Estado {
		ACEITO, REJEITADO
	}
	
    private UsuarioTO submissor;
    private String titulo;
    private String resumo;
    private String autores;
    private String caminhoArquivoSubmissao;
    private String caminhoArquivoFinal;
    private Estado estado;
    private Boolean flagtrabalhoComVersaoFinal;
    private BancaExaminadoraTO bancaExaminadoraResponsavel;
    
	public UsuarioTO getSubmissor() {
		return submissor;
	}
	public void setSubmissor(UsuarioTO submissor) {
		this.submissor = submissor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getAutores() {
		return autores;
	}
	public void setAutores(String autores) {
		this.autores = autores;
	}
	public String getCaminhoArquivoSubmissao() {
		return caminhoArquivoSubmissao;
	}
	public void setCaminhoArquivoSubmissao(String caminhoArquivoSubmissao) {
		this.caminhoArquivoSubmissao = caminhoArquivoSubmissao;
	}
	public String getCaminhoArquivoFinal() {
		return caminhoArquivoFinal;
	}
	public void setCaminhoArquivoFinal(String caminhoArquivoFinal) {
		this.caminhoArquivoFinal = caminhoArquivoFinal;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Boolean getFlagtrabalhoComVersaoFinal() {
		return flagtrabalhoComVersaoFinal;
	}
	public void setFlagtrabalhoComVersaoFinal(Boolean flagtrabalhoComVersaoFinal) {
		this.flagtrabalhoComVersaoFinal = flagtrabalhoComVersaoFinal;
	}
	public BancaExaminadoraTO getBancaExaminadoraResponsavel() {
		return bancaExaminadoraResponsavel;
	}
	public void setBancaExaminadoraResponsavel(
			BancaExaminadoraTO bancaExaminadoraResponsavel) {
		this.bancaExaminadoraResponsavel = bancaExaminadoraResponsavel;
	}
    
    

}
