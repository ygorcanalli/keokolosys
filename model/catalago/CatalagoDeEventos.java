package catalago;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import dominio.Evento;
import dominio.Instituicao;
import dominio.Usuario;
import excecao.ExcecaoDeCadastro;

public class CatalagoDeEventos {
	
    private Collection<Evento> eventosAguardandoAprovacao;
    private Collection<Evento> eventosDeferios;
    private Collection<Evento> eventosIndeferidos;
    private Collection<Evento> eventosCancelados;
    private Collection<Evento> eventosFinalizados; 
    
    private static CatalagoDeEventos instancia;
	
	public static CatalagoDeEventos obterInsancia() {
		if (instancia == null)
			instancia =  new CatalagoDeEventos();
		
		return instancia;
	}

    
    private CatalagoDeEventos() {
        this.eventosAguardandoAprovacao = new ArrayList<Evento>();
        this.eventosDeferios = new ArrayList<Evento>();
        this.eventosIndeferidos = new ArrayList<Evento>();
        this.eventosCancelados = new ArrayList<Evento>();
        this.eventosFinalizados = new ArrayList<Evento>();
    }
    
    public void criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
        Evento evento = Evento.criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
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
    
    public Collection<Evento> obterEventos() {
    	Collection<Evento> eventos = new ArrayList<Evento>();
    	eventos.addAll(eventosAguardandoAprovacao);
    	eventos.addAll(eventosDeferios);
    	eventos.addAll(eventosIndeferidos);
    	eventos.addAll(eventosCancelados);
    	eventos.addAll(eventosFinalizados);
		return eventos;
    	
    }
    
    public void atualizarDadosEvento(Evento evento, Instituicao instituicao, String nome, Date dataDeInicio, Date dataDeFim, Date dataMaximaParaSubmissaoDeTrabalhos, Date dataMaximaParaAceitacaoDeTrabalhos) throws ExcecaoDeCadastro{
    	if(evento.getNome().compareTo(nome) != 0)
    		evento.setNone(nome);
    	
    	if(evento.getInstituicao().compareTo(instituicao) != 0)
    		evento.setInstituicao(instituicao);
    	
    	if(evento.getDataDeInicio().compareTo(dataDeInicio) != 0)
    		evento.setDataDeInicio(dataDeInicio);
    	
    	if(evento.getDataDeFim().compareTo(dataDeFim) != 0)
    		evento.setDataDeFim(dataDeFim);
    	
    	if(evento.getDataMaximaParaSubmissaoDeTrabalhos().compareTo(dataMaximaParaSubmissaoDeTrabalhos) != 0)
    		evento.setDataMaximaParaSubmissaoDeTrabalhos(dataMaximaParaSubmissaoDeTrabalhos);
    	
    	if(evento.getDataMaximaParaAceitacaoDeTrabalhos().compareTo(dataMaximaParaAceitacaoDeTrabalhos) != 0)
    		evento.setDataMaximaParaAceitacaoDeTrabalhos(dataMaximaParaAceitacaoDeTrabalhos);
    }
    
    @SuppressWarnings("unused")
	private Boolean pertence(Evento evento) {
    	return obterEventos().contains(evento);
    }
    
    public void deferirEvento(Evento evento) {
        evento.deferir();
        this.eventosAguardandoAprovacao.remove(evento);
        this.eventosDeferios.add(evento);
    }

    public void indeferirEvento(Evento evento) {
        evento.indeferir();
        this.eventosAguardandoAprovacao.remove(evento);
        this.eventosIndeferidos.add(evento);
    }

    public void cancelarEvento(Evento evento) {
        evento.cancelar();
        this.eventosDeferios.remove(evento);
        this.eventosCancelados.add(evento);
    }

    public void finalizarEvento(Evento evento) {
        evento.finalizar();
        this.eventosDeferios.remove(evento);
        this.eventosFinalizados.add(evento);
    }
	
}