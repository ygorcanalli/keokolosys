package transferobject;

import java.util.Date;

public class EventoTO {
	
	public enum Estado {
		AGUARDANDO, DEFERIDO, INDEFERIDO, CANCELADO, FINALIZADO
	}

	
    private String nome;
    private InstituicaoTO instituicao;
    private UsuarioTO usuarioResponsavel;
    private Date dataMaximaParaSubmissaoDeTrabalhos;
    private Date dataMaximaParaAceitacaoDeTrabalhos;
    private Date dataDeInicio;
    private Date dataDeFim;
    private Estado estado;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public InstituicaoTO getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}
	public UsuarioTO getUsuarioResponsavel() {
		return usuarioResponsavel;
	}
	public void setUsuarioResponsavel(UsuarioTO usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	public Date getDataMaximaParaSubmissaoDeTrabalhos() {
		return dataMaximaParaSubmissaoDeTrabalhos;
	}
	public void setDataMaximaParaSubmissaoDeTrabalhos(
			Date dataMaximaParaSubmissaoDeTrabalhos) {
		this.dataMaximaParaSubmissaoDeTrabalhos = dataMaximaParaSubmissaoDeTrabalhos;
	}
	public Date getDataMaximaParaAceitacaoDeTrabalhos() {
		return dataMaximaParaAceitacaoDeTrabalhos;
	}
	public void setDataMaximaParaAceitacaoDeTrabalhos(
			Date dataMaximaParaAceitacaoDeTrabalhos) {
		this.dataMaximaParaAceitacaoDeTrabalhos = dataMaximaParaAceitacaoDeTrabalhos;
	}
	public Date getDataDeInicio() {
		return dataDeInicio;
	}
	public void setDataDeInicio(Date dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}
	public Date getDataDeFim() {
		return dataDeFim;
	}
	public void setDataDeFim(Date dataDeFim) {
		this.dataDeFim = dataDeFim;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
    
    

}
