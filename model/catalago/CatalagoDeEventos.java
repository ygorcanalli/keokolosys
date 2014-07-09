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
	
	public static synchronized CatalagoDeEventos obterInstancia() {
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
    
<<<<<<< HEAD
    public Evento criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
=======
    public void criarEvento(String nome, Instituicao instituicao, Usuario usuarioResponsavel, Date dataMaximaParaSubmissaoDeTrabalho, Date dataMaximaParaAceitacaoDeTrabalho, Date dataDeInicio, Date dataDeFim) throws  ExcecaoDeCadastro{
    	validarNomeDoEventoComoUnico(nome);
>>>>>>> master
        Evento evento = Evento.criarEvento(nome, instituicao, usuarioResponsavel, dataMaximaParaSubmissaoDeTrabalho, dataMaximaParaAceitacaoDeTrabalho, dataDeInicio, dataDeFim);
        eventosAguardandoAprovacao.add(evento);
        return evento;
    }
    
    public Evento obterEventoPorNome(String nome) throws ExcecaoDeCadastro{
    	Evento evento = buscarEventoPorNome(nome);
    	
    	if(evento == null)    	    	
    		throw new ExcecaoDeCadastro("catalago_de_eventos.evento.inexistente");
    	
    	return evento;
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
    
    private void validarNomeDoEventoComoUnico(String nome) throws ExcecaoDeCadastro{
    	Evento evento = buscarEventoPorNome(nome);
    	
    	if(evento != null)
    		throw new ExcecaoDeCadastro("catalago_de_eventos.nome_evento.existente");
    }
    
    private Evento buscarEventoPorNome(String nome){
    	for (Evento evento : eventosAguardandoAprovacao) {
    		if(evento.getNome() == nome)
    			return evento;
    	}
    	
    	for (Evento evento : eventosDeferios) {
    		if(evento.getNome() == nome)
    			return evento;
    	}
    	
    	for (Evento evento : eventosIndeferidos) {
    		if(evento.getNome() == nome)
    			return evento;
    	}
    	
    	for (Evento evento : eventosFinalizados) {
    		if(evento.getNome() == nome)
    			return evento;
    	}
    	
    	for (Evento evento : eventosCancelados) {
    		if(evento.getNome() == nome)
    			return evento;
    	}
    	
    	return null;
    }    
	
}
