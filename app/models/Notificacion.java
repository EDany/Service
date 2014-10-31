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

    @ManyToOne
    @JoinColumn(name="idDeuda")
    private Deuda idDeuda;

    private String notificacion;

    public Notificacion(){
        super();
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

    public Deuda getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(Deuda idDeuda) {
        this.idDeuda = idDeuda;
    }
}
