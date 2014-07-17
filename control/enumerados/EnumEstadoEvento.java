package enumerados;

public enum EnumEstadoEvento {
	AGUARDANDO("Aguardando"), DEFERIDO("Deferido"), INDEFERIDO("Indeferido"), CANCELADO("Cancelado"), FINALIZADO("Finalizado");
	
	private final String valor;
	
	EnumEstadoEvento (String valorOpcao){
		valor = valorOpcao;
	}
	public String getValor(){
		return valor;
	}
}
