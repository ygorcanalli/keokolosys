package dominio;


public abstract class Perfil{
    private Usuario usuario;
    private Evento evento;

    public Perfil(Usuario usuario, Evento evento){
        this.usuario = usuario;
        this.evento = evento;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Evento getEvento(){
        return this.evento;
    }

}
