package keokolosys;

import java.util.*;

public class ControladorDeCadastro {

    private ControladorDeCadastro instancia = null;

    private Collection<Instituicao> instituicoes;
    private Map<String, Usuario> usuarios;
    private Map<String, Administrador> administradores;
    private Collection<Evento> eventosAguardandoAprovacao;
    private Collection<Evento> eventosDeferios;
    private Collection<Evento> eventosIndeferidos;
    private Collection<Evento> eventosCancelados;

    public ControladorDeCadastro getControladorDeCadastro(){
        if(instancia == null)
            instancia = new ControladorDeCadastro();

        return instancia;
    }

    private ControladorDeCadastro() {
        this.instituicoes = new ArrayList<Instituicao>();
        this.usuarios = new HashMap<String, Usuario>();
        this.administradores = new HashMap<String, Administrador>();
        this.eventosAguardandoAprovacao = new ArrayList<Evento>();
        this.eventosDeferios = new ArrayList<Evento>();
        this.eventosIndeferidos = new ArrayList<Evento>();
        this.eventosCancelados = new ArrayList<Evento>();
    }

    public void criarInstituicao(String nome, String sigla, String localizacao) throws ExcecaoDeCadastro{
        try{
            Instituicao instituicao = new Instituicao(nome, sigla, localizacao);
            instituicoes.add(instituicao);
        }
        catch (ExcecaoDeCadastro ec){
            throw ec;
        }
    }

    public void criarUsuario(String nome, String ultimoNome, String email, String senha, Instituicao instituicao) throws  ExcecaoDeCadastro{
        if(obterUsuario(email) == null){
            Usuario usuario = new Usuario(nome, ultimoNome, email, senha, instituicao);
            usuarios.put(email, usuario);
        }
        else
            throw new ExcecaoDeCadastro("usuario.email.existente");
    }

    public void criarEvento(String nome, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
        Evento evento = new Evento(nome, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
        eventosAguardandoAprovacao.add(evento);
    }

    public Collection<Evento> obterEventosDeferidos() {
        return this.eventosDeferios;
    }

    public Collection<Evento> obterEventosIndeferidos() {
        return this.eventosIndeferidos;
    }

    public Collection<Evento> obterEventosAguardandoAprovacao() {
        return this.eventosAguardandoAprovacao;
    }

    private Usuario obterUsuario(String email){
        return usuarios.get(email);
    }
    
    void deferirEvento(Evento evento) {
        evento.deferir();
        this.eventosAguardandoAprovacao.remove(evento);
        this.eventosDeferios.add(evento);
    }

    void indeferirEvento(Evento evento) {
        evento.indeferir();
        this.eventosAguardandoAprovacao.remove(evento);
        this.eventosIndeferidos.add(evento);
    }

    void cancelarEvento(Evento evento) {
        evento.cancelar();
        this.eventosDeferios.remove(evento);
        this.eventosCancelados.add(evento);
    }

    void finalizarEvento(Evento evento) {
        evento.finalizar();
        this.eventosDeferios.remove(evento);
        this.eventosFinalizados.add(evento);
    }

}
