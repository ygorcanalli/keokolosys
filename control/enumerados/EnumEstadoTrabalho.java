package enumerados;

public enum EnumEstadoTrabalho {
	ACEITO("aceito"),REJEITADO("rejeitado"),NAO_AVALIADO("nao avaliado");
	
	private final String valor;
	
	EnumEstadoTrabalho (String valorOpcao){
		valor = valorOpcao;
	}
	public String getValor(){
		return valor;
	}




}
