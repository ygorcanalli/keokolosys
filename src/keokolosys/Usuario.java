package keokolosys;

import java.util.*;

/**
 * Created by keokolo on 07/06/14.
 */
public class Usuario extends Autenticavel {
    private String nome;
    private String ultimoNome;
    private Instituicao instituicao;
    private Map<Evento, Perfil> perfis;

    private Usuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws  ExcecaoDeCadastro{
        this.nome = nome;
        this.ultimoNome = ultimoNome;
        this.instituicao = instituicao;
        this.perfis = new HashMap<Evento, Perfil>();      
    }
    
    public Usuario criarUsuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws  ExcecaoDeCadastro{
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

    Perfil obterPerfilDe(Evento evento, Class<? extends Perfil> tipoPerfil){
        Perfil perfil = obterPerfil(evento);

        if(tipoPerfil.isInstance(perfil))
            return perfil;

        return null;
    }

    public Boolean possuiPerfil(Evento evento, Class<? extends Perfil> tipoPerfil){
        return (obterPerfilDe(evento, tipoPerfil) != null);
    }

    private static void validarDados(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws ExcecaoDeCadastro{
        Boolean nomeVazio = (nome == null) || (nome.isEmpty());
        Boolean ultimoNomeVazio = (ultimoNome == null) || (ultimoNome.isEmpty());
        Boolean emailVazio = (email == null) || (email.isEmpty());
        Boolean senhaVazia = (senha == null) || (senha.isEmpty());
        Boolean instituicaoVazia = (instituicao == null);

        if (nomeVazio)
        	throw new ExcecaoDeCadastro("usuario.nome.vazio");
        
        if (ultimoNomeVazio)
        	throw new ExcecaoDeCadastro("usuario.ultimoNome.vazio");
        
        if (emailVazio)
        	throw new ExcecaoDeCadastro("usuario.email.vazio");
        
        if (senhaVazia)
        	throw new ExcecaoDeCadastro("usuario.senha.vazia");
        
        if (instituicaoVazia)
        	throw new ExcecaoDeCadastro("usuario.instituicao.vazia");
    }


    String getNome() {
        return nome;
    }


    String getUltimoNome() {
        return ultimoNome;
    }

    Instituicao getInstituicao() {
        return instituicao;
    }

}