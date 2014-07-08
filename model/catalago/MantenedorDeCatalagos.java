package catalago;


public final class MantenedorDeCatalagos {

	private CatalagoDeEventos catalagoDeEventos = CatalagoDeEventos.obterInstancia();
	private CatalagoDeInstituicoes catalagoDeInstituicoes = CatalagoDeInstituicoes.obterInstancia();
	private CatalagoDeAutenticaveis catalagoDeAutenticaveis = CatalagoDeAutenticaveis.obterInstancia();
	
	private static MantenedorDeCatalagos instancia;
	
	private MantenedorDeCatalagos() {
		catalagoDeEventos = CatalagoDeEventos.obterInstancia();
		catalagoDeInstituicoes = CatalagoDeInstituicoes.obterInstancia();
		catalagoDeAutenticaveis = CatalagoDeAutenticaveis.obterInstancia();
	}
	
	public static synchronized MantenedorDeCatalagos obterInstancia() {
		if (instancia == null)
			instancia = new MantenedorDeCatalagos();
		return instancia;
	}
	
	public CatalagoDeAutenticaveis obterCatalagoDeAutenticaveis(){
		return catalagoDeAutenticaveis;
	}
	
	public CatalagoDeInstituicoes obterCatalagoDeInstituicoes(){
		return catalagoDeInstituicoes;
	}
	
	public CatalagoDeEventos obterCatalagoDeEventos(){
		return catalagoDeEventos;
	}
	
}
