package keokolosys;

public interface AbstractGUI {
	

	public void inicializar();
	public void exibirMensagemDeErro(String mensagem, String titulo);
	public void exibirMensagemDeAviso(String mensagem, String titulo);
	public void exibirMensagemDeInformacao(String mensagem, String titulo);
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo, Object[] opcoes, Object opcaoPadrao);
	
}
