package transferobject;

import dominio.EstadoAvaliacao;

public class AvaliacaoTO 
{
	private String nomeExaminador;
	private String emailExaminador;
	private EstadoAvaliacao enumEstadoAvaliacao;
	
	
	public String getNomeExaminador() {
		return nomeExaminador;
	}
	public void setNomeExaminador(String nomeExaminador) {
		this.nomeExaminador = nomeExaminador;
	}
	public EstadoAvaliacao getEnumEstadoTrabalho() {
		return enumEstadoAvaliacao;
	}
	public void setEstadoAvaliacao(EstadoAvaliacao enumEstadoTrabalho) {
		this.enumEstadoAvaliacao = enumEstadoTrabalho;
	}
	public String getEmailExaminador() {
		return emailExaminador;
	}
	public void setEmailExaminador(String emailExaminador) {
		this.emailExaminador = emailExaminador;
	}
	
}
