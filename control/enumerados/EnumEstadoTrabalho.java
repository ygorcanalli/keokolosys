package enumerados;

public enum EnumEstadoTrabalho {
	ACEITO("Aceito"),REJEITADO("Rejeitado"),NAO_AVALIADO("Não avaliado");
	
	private final String valor;
	
	EnumEstadoTrabalho (String valorOpcao){
		valor = valorOpcao;
	}
	public String getValor(){
		return valor;
	}




}
