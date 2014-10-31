package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edgar on 07/10/2014.
 */
@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Integer idUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Device> listOfDevices = new ArrayList<Device>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Deuda> listOfDeudas = new ArrayList<Deuda>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idUsuarioContacto")
    private List<Deuda> listOfDeudas2 = new ArrayList<Deuda>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idUsuarioFrom")
    private List<Notificacion> listOfNotificacion = new ArrayList<Notificacion>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idUsuarioTo")
    private List<Notificacion> listOfNotificacionTo = new ArrayList<Notificacion>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idUsuario")
    private List<Contacto> listOfContacts = new ArrayList<Contacto>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContacto")
    private List<Contacto> listOfContacts2 = new ArrayList<Contacto>();

    private String usuario;
    private String pass;
    private String email;
    private Boolean sincronizacion;

    public Usuario(){
        super();
    }

    public Usuario(Integer idUsuario, String usuario, String pass, String email, Boolean sincronizacion){
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.pass = pass;
        this.email = email;
        this.setSincronizacion(sincronizacion);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSincronizacion(){
        return sincronizacion;
    }

    public void setSincronizacion(Boolean sincronizacion){
        this.sincronizacion = sincronizacion;
    }
}
