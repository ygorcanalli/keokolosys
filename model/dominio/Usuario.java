package dominio;

import java.util.*;

import excecao.ExcecaoDeCadastro;


public class Usuario extends Autenticavel {
    private String nome;
    private String ultimoNome;
    private Instituicao instituicao;
    private Map<Evento, Perfil> perfis;

    private Usuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao){
    	super(email, senha);
        this.nome = nome;
        this.ultimoNome = ultimoNome;
        this.instituicao = instituicao;
        this.perfis = new HashMap<Evento, Perfil>();      
    }
    
    public static Usuario criarUsuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws  ExcecaoDeCadastro{
    	validarDados(nome, ultimoNome, email, senha, instituicao);
    	
    	return new Usuario(nome, ultimoNome, email, senha, instituicao);
    }


    public Map<Class<? extends Perfil>, Collection<Evento>> obterEventosRelacionados() {
        Map<Class<? extends Perfil>, Collection<Evento>> eventosRelacionados = new TreeMap<Class<? extends Perfil>, Collection<Evento>>();

        for (Map.Entry<Evento, Perfil> entry: perfis.entrySet()) {
            Class<? extends Perfil> tipoPerfil = entry.getValue().getClass();
            Evento evento = entry.getKey();

            if (eventosRelacionados.get(tipoPerfil) == null) {
                eventosRelacionados.put(tipoPerfil, new ArrayList<Evento>());
            }

            eventosRelacionados.get(tipoPerfil).add(evento);
        }
        
        return eventosRelacionados;
    }

    public void adicionarPerfil(Perfil perfil) {
        perfis.put(perfil.getEvento(), perfil);
    }

    public Perfil obterPerfil(Evento evento) {
        return perfis.get(evento);
    }

    public Perfil obterPerfilDe(Evento evento, Class<? extends Perfil> tipoPerfil){
        Perfil perfil = obterPerfil(evento);

        if(tipoPerfil.isInstance(perfil))
            return perfil;

        return null;
    }

    public Boolean possuiPerfil(Evento evento, Class<? extends Perfil> tipoPerfil){
        return (obterPerfilDe(evento, tipoPerfil) != null);
    }

    private static void validarDados(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws ExcecaoDeCadastro{
    	validarNome(nome);
    	validarUltimoNome(ultimoNome);
    	validarEmail(email);
    	validarSenha(senha);
    	validarInstituicao(instituicao);
    }
    
    private static void validarNome(String nome) throws ExcecaoDeCadastro{
    	Boolean nomeVazio = (nome == null) || (nome.isEmpty());
    	
    	if (nomeVazio)
        	throw new ExcecaoDeCadastro("usuario.nome.vazio");
    }
    
    private static void validarUltimoNome(String ultimoNome) throws ExcecaoDeCadastro{
    	Boolean ultimoNomeVazio = (ultimoNome == null) || (ultimoNome.isEmpty());
    	
        if (ultimoNomeVazio)
        	throw new ExcecaoDeCadastro("usuario.ultimo_nome.vazio");
    }
    
    private static void validarEmail(String email) throws ExcecaoDeCadastro{
        Boolean emailVazio = (email == null) || (email.isEmpty());
    	
        if (emailVazio)
        	throw new ExcecaoDeCadastro("usuario.email.vazio");
    }
    
    private static void validarSenha(String senha) throws ExcecaoDeCadastro{
        Boolean senhaVazia = (senha == null) || (senha.isEmpty());
    	
        if (senhaVazia)
        	throw new ExcecaoDeCadastro("usuario.senha.vazia");
    }

    private static void validarInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
    	Boolean instituicaoVazia = (instituicao == null);
    	
        if (instituicaoVazia)
        	throw new ExcecaoDeCadastro("usuario.instituicao.vazia");
    }
    

    public String getNome() {
        return nome;
    }


    public String getUltimoNome() {
        return ultimoNome;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }
    
    public void atualizarDados(String email, String senha, String nome, String ultimoNome, Instituicao instituicao) throws ExcecaoDeCadastro{		

    	validarDados(nome, ultimoNome, email, senha, instituicao);
    	
    	if(this.email.compareTo(email) != 0)
			this.email = email;
		
    	if(this.senha.compareTo(senha) != 0)
    		this.senha = senha;
		
		if(this.nome.compareTo(nome) != 0)
			this.nome = nome;
		
		if(this.ultimoNome.compareTo(ultimoNome) != 0)
			this.ultimoNome = ultimoNome;
		
		if(this.instituicao.compareTo(instituicao) != 0)
			this.instituicao = instituicao;
	}
    
    /*public void setNome(String nome) throws ExcecaoDeCadastro{
    	validarNome(nome);
    	this.nome = nome;
    }
    
    public void setUltimoNome(String ultimoNome) throws ExcecaoDeCadastro{
    	validarUltimoNome(ultimoNome);
    	this.ultimoNome = ultimoNome;
    }
    
    public void setEmail(String email) throws ExcecaoDeCadastro{
    	validarEmail(email);
    	this.email = email;
    }
    
    public void setSenha(String senha) throws ExcecaoDeCadastro{
    	validarSenha(senha);
    	this.senha = senha;
    }
    
    public void setInstituicao(Instituicao instituicao) throws ExcecaoDeCadastro{
    	validarInstituicao(instituicao);
    	this.instituicao = instituicao;
    }*/

}