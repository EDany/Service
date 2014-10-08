package models;

import javax.persistence.*;

/**
 * Created by Edgar on 07/10/2014.
 */
@Entity
@Table(name="notificacion")
public class Notificacion {

    @Id
    @GeneratedValue
    private Integer idNotificacion;

    @ManyToOne
    @JoinColumn(name="idUsuarioFrom")
    private Usuario idUsuarioFrom;

    @ManyToOne
    @JoinColumn(name="idUsuarioTo")
    private Usuario idUsuarioTo;

    private String notificacion;

    public Notificacion(){
        this.setIdNotificacion(null);
        this.setIdUsuarioFrom(null);
        this.setIdUsuarioTo(null);
        this.setNotificacion(null);
    }

    public Notificacion(Integer idNotificacion, Usuario idUsuarioFrom, Usuario idUsuarioTo,
                        String notificacion){
        this.setIdNotificacion(idNotificacion);
        this.setIdUsuarioFrom(idUsuarioFrom);
        this.setIdUsuarioTo(idUsuarioTo);
        this.setNotificacion(notificacion);
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Usuario getIdUsuarioFrom() {
        return idUsuarioFrom;
    }

    public void setIdUsuarioFrom(Usuario idUsuarioFrom) {
        this.idUsuarioFrom = idUsuarioFrom;
    }

    public Usuario getIdUsuarioTo() {
        return idUsuarioTo;
    }

    public void setIdUsuarioTo(Usuario idUsuarioTo) {
        this.idUsuarioTo = idUsuarioTo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
}
