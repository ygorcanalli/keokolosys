package catalago;

public final class MantenedorDeCatalagos {

	private static CatalagoDeEventos catalagoDeEventos = CatalagoDeEventos.obterInsancia();
	private static CatalagoDeInstituicoes catalagoDeInstituicoes = CatalagoDeInstituicoes.obterInstancia();
	private static CatalagoDeAutenticaveis catalagoDeAutenticaveis = CatalagoDeAutenticaveis.obterInstancia();
	
	public static CatalagoDeAutenticaveis obterCatalagoDeAutenticaveis(){
		return catalagoDeAutenticaveis;
	}
	
	public static CatalagoDeInstituicoes obterCatalagoDeInstituicoes(){
		return catalagoDeInstituicoes;
	}
	
	public static CatalagoDeEventos obterCatalagoDeEventos(){
		return catalagoDeEventos;
	}
	
}
