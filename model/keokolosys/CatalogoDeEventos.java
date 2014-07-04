package keokolosys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CatalogoDeEventos implements Singleton {
	
    private Collection<Evento> eventosAguardandoAprovacao;
    private Collection<Evento> eventosDeferios;
    private Collection<Evento> eventosIndeferidos;
    private Collection<Evento> eventosCancelados;
    private Collection<Evento> eventosFinalizados; 
    
    private CatalogoDeEventos instancia;
	
	@Override
	public CatalogoDeEventos obterInsancia() {
		if (instancia == null)
			instancia =  new CatalogoDeEventos();
		
		return instancia;
	}

    
    private CatalogoDeEventos() {
        this.eventosAguardandoAprovacao = new ArrayList<Evento>();
        this.eventosDeferios = new ArrayList<Evento>();
        this.eventosIndeferidos = new ArrayList<Evento>();
        this.eventosCancelados = new ArrayList<Evento>();
        this.eventosFinalizados = new ArrayList<Evento>();
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
    
    public Collection<Evento> obterEventos() {
    	Collection<Evento> eventos = new ArrayList<Evento>();
    	eventos.addAll(eventosAguardandoAprovacao);
    	eventos.addAll(eventosDeferios);
    	eventos.addAll(eventosIndeferidos);
    	eventos.addAll(eventosCancelados);
    	eventos.addAll(eventosFinalizados);
		return eventos;
    	
    }
    
    private Boolean pertence(Evento evento) {
    	return obterEventos().contains(evento);
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
