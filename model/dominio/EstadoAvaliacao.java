package dominio;

public enum EstadoAvaliacao {
    ACEITO("Aceito"), REJEITADO("Rejeitado"), NAO_AVALIADO("Nao Avaliado");

	private final String valor;
		
	EstadoAvaliacao (String valorOpcao){
			valor = valorOpcao;
		}
		public String getValor(){
			return valor;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getValor();
		}
    
}
